/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023 
 */
package ws.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.cafetito.model.BcBoletaPesaje;
import ws.agricultor.model.AgrCuentaCorriente;
import ws.agricultor.model.AgrParcialidades;
import ws.agricultor.model.AgrTransportistas;
import ws.agricultor.model.AgrVehiculos;
import ws.agricultor.repository.AgrCuentaCorrienteRepository;
import ws.agricultor.repository.AgrEstadosRepository;
import ws.agricultor.repository.AgrTransportistasRepository;
import ws.agricultor.repository.AgrVehiculosRepository;
import ws.cafetito.model.BcCuentaCorriente;
import ws.cafetito.model.BcMensajes;
import ws.cafetito.model.BcParcialidades;
import ws.cafetito.repository.BcBoletaPesajeRepository;
import ws.cafetito.repository.BcCuentaCorrienteRepository;
import ws.cafetito.repository.BcEstadosRepository;
import ws.cafetito.repository.BcParcialidadesRepository;
import ws.dto.CreacionCuentaDto;
import ws.dto.MensajeDto;
import ws.dto.CuentaCreadaDto;
import ws.dto.ParamCuentaDto;
import ws.dto.RespuestaDto;
import ws.dto.VehiculosAsigDto;
import ws.projection.CuentaProjection;
import ws.util.Estados;

/**
 *
 * @author carlos
 */
@Service
public class BcCuentaCorrienteService {
    @Autowired
    private BcCuentaCorrienteRepository bccRepository;
    
    @Autowired
    private AgrCuentaCorrienteRepository agrCuentaRepository;
    
    @Autowired
    private BcParcialidadesRepository bcParcialidadesRepository;
    
    @Autowired
    private AgrVehiculosRepository agrVehiculoRepository;
    
    @Autowired
    private AgrTransportistasRepository agrTransportistaRepository;
    
    @Autowired
    private BcBitacoraService bcBitacoraService;
    
    @Autowired
    private BcBoletaPesajeService bcBoletaPesajeService;
    
    @Autowired
    private AgrBitacoraService argBitacoraService;
    
    @Autowired
    private BcBoletaPesajeRepository bcBoletaPesajeRepository;
    
    @Autowired
    private BcEstadosRepository berRepository;
    
    @Autowired
    private AgrEstadosRepository agrEstadoRepository;
    
    @Autowired
    private BcMensajesService bcMensajesService; 
    
    @Autowired
    private BcParcialidadesService bcParcialidadService;
    
    @Autowired
    private AgrParcialidadesService agrParcialidadService;
    
    @Autowired
    private AgrCuentaCorrienteService accsService;
    
    public CreacionCuentaDto detalleCuenta(Integer id) {

        AgrCuentaCorriente cuentaCorriente = agrCuentaRepository.findById(id).orElse(null);
        CuentaProjection cuentaDetalle = agrCuentaRepository.getCuentaById(id);
        
        Type tipoListaVehiculos = new TypeToken<List<VehiculosAsigDto>>() {}.getType();
        List<VehiculosAsigDto> datos = new Gson().fromJson(cuentaCorriente.getVehiculosTransportistasAsignados(), tipoListaVehiculos);

        datos.stream().map(c ->{
            c.setDetalleVehiculo(agrVehiculoRepository.findById(c.getPlaca()).orElse(null));
            c.setTransportistas(agrTransportistaRepository.findAllById(c.getLicencias()));
            return c;
        }).collect(Collectors.toList());
        
        CreacionCuentaDto retorno = new CreacionCuentaDto();
        retorno.setPeso(cuentaCorriente.getPesoTotal());
        retorno.setCantidad(cuentaCorriente.getCantidadParcialidades());
        retorno.setVehiculos(datos);
        retorno.setAgricultor(cuentaDetalle.getAgricultor());
        retorno.setEstadoNombre(cuentaDetalle.getEstadoNombre());
        retorno.setEstado(cuentaDetalle.getEstado());
        retorno.setIdCuentaCorriente(cuentaCorriente.getIdCuentaCorriente());
        return retorno;
    }
    
    public List<CreacionCuentaDto> cuentasDetalles(Integer estado){
        List<Integer> estados = new ArrayList();
        estados.add(estado);
        List<AgrCuentaCorriente> cuentas = agrCuentaRepository.findByEstadoCuenta(estado);
        List<CreacionCuentaDto> lista = cuentas.stream().map(k ->
                this.detalleCuenta(k.getIdCuentaCorriente())
        ).collect(Collectors.toList());
        
        return lista;
    }
    

    public CuentaCreadaDto crearCuenta(ParamCuentaDto param, String username){
        
        AgrCuentaCorriente cuenta = agrCuentaRepository.findById(param.getIdVenta()).get();
        CuentaCreadaDto res = new CuentaCreadaDto();
        
        if(!cuenta.equals(null)) {
            AgrCuentaCorriente agrCreada = new AgrCuentaCorriente();
            //1 -> Aprobar cuenta, 2-> Corregir y 3 -> Rechazar
            switch (param.getOperacion()) {
                case 1:
                    LocalDateTime fechaHoraActual = LocalDateTime.now();
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
                    String noCuenta = fechaHoraActual.format(formato);

                    BcCuentaCorriente creadaC = bccRepository.save(
                            BcCuentaCorriente.builder()
                                    .cantidadParcialidades(cuenta.getCantidadParcialidades())
                                    .fechaCreacion(new Date())
                                    .estadoCuenta(Estados.CUENTA_CREADA)
                                    .numeroCuenta(noCuenta)
                                    .pesoTotal(cuenta.getPesoTotal())
                                    .usuarioCreacion(username)
                                    .vehiculosTransportistasAsignados(cuenta.getVehiculosTransportistasAsignados())
                                    .build()
                    );
                    bcBitacoraService.addRecordBc("bc_cuenta_corriente", creadaC.getNumeroCuenta(), 'I', creadaC, username);

                    for (int i = 0; i < creadaC.getCantidadParcialidades(); i++) {
                        BcParcialidades parcialidad = new BcParcialidades();
                        AgrParcialidades agrParcialidad = new AgrParcialidades();

                        parcialidad.setNumeroCuenta(creadaC.getNumeroCuenta());
                        parcialidad.setEstadoParcialidad(Estados.PAR_PENDIETE_RECOLECTAR);
                        parcialidad.setFechaCreacion(new Date());
                        parcialidad.setPesoParcialidad(creadaC.getPesoTotal().divide(new BigDecimal(creadaC.getCantidadParcialidades())));
                        parcialidad.setUsuarioCreacion(username);
                        bcParcialidadService.agregarParcialidad(parcialidad);
                        
                        agrParcialidad.setIdCuentaCorriente(cuenta.getIdCuentaCorriente());
                        agrParcialidad.setEstadoParcialidad(Estados.PAR_PENDIETE_RECOLECTAR);
                        agrParcialidad.setFechaCreacion(new Date());
                        agrParcialidad.setPesoParcialidad(creadaC.getPesoTotal().divide(new BigDecimal(creadaC.getCantidadParcialidades())));
                        agrParcialidad.setUsuarioCreacion(username);                        
                        agrParcialidadService.agregarParcialidad(agrParcialidad);
                        
                    }
                    
                    cuenta.setNumeroCuenta(noCuenta);
                    cuenta.setEstadoCuenta(Estados.CUENTA_CREADA);
                    agrCreada = agrCuentaRepository.save(cuenta);
                    argBitacoraService.addRecordAgr("agr_cuenta_corriente", agrCreada.getNumeroCuenta(), 'U', agrCreada, username);

                    res.setIdCuentaCorriente(cuenta.getIdCuentaCorriente());
                    res.setAprobado(Estados.CUENTA_CREADA);
                    res.setCorrecion(0);
                    res.setMensaje("Cuenta creada, por favor enviar la primera parcialidad.");
                    res.setRechazado(0);
                    res.setNumeroCuenta(creadaC.getNumeroCuenta());

                    break;
                case 2:

                    cuenta.setEstadoCuenta(Estados.CUENTA_CORREGIR);
                    agrCreada = agrCuentaRepository.save(cuenta);
                    argBitacoraService.addRecordAgr("agr_cuenta_corriente", agrCreada.getNumeroCuenta(), 'U', agrCreada, username);

                    res.setAprobado(0);
                    res.setCorrecion(Estados.CUENTA_CORREGIR);
                    res.setIdCuentaCorriente(cuenta.getIdCuentaCorriente());
                    res.setMensaje(param.getMensaje());
                    res.setRechazado(0);
                    break;
                case 3:
                    cuenta.setEstadoCuenta(Estados.CUENTA_RECHAZADA);
                    agrCreada = agrCuentaRepository.save(cuenta);
                    argBitacoraService.addRecordAgr("agr_cuenta_corriente", agrCreada.getNumeroCuenta(), 'U', agrCreada, username);

                    res.setAprobado(0);
                    res.setCorrecion(0);
                    res.setIdCuentaCorriente(cuenta.getIdCuentaCorriente());
                    res.setMensaje(param.getMensaje());
                    res.setRechazado(Estados.CUENTA_RECHAZADA);
                    break;
                default:
                    System.out.println("ERROR");
                    
            }
            return res;
        }else{
            return null;
        }
    }
    
    public MensajeDto pesaje(Integer idParcialidad, BigDecimal pesaje, String userName){
        
        System.out.println("ENTRA AL SERVICIO");
        final BcParcialidades parcialidadBeneficio = bcParcialidadesRepository.findByIdParcialidad(idParcialidad);
        BcBoletaPesaje boleta = new BcBoletaPesaje();
        
        if(parcialidadBeneficio == null){
            System.out.println("No existe la parcialidad");
            return null;
        }
        
        BcCuentaCorriente cuentaBeneficio = bccRepository.findByNumeroCuenta(parcialidadBeneficio.getNumeroCuenta());
        
        if(cuentaBeneficio == null){
            System.out.println("No existe la cuenta");
            return null;
        }
        
        List<BcParcialidades> parcialidadesBeneficio = bcParcialidadesRepository.findByNumeroCuenta(cuentaBeneficio.getNumeroCuenta());
        
        if(parcialidadesBeneficio == null || parcialidadesBeneficio.isEmpty()){
            System.out.println("No existen las parcialidades");
            return null;
        }
        
        Optional<BcParcialidades> parcialidadPendientePesaje = parcialidadesBeneficio.stream()
                .filter(parcialidad -> parcialidad.getEstadoParcialidad()
                        .equals(Estados.PAR_PENDIENTE_PESAR) && parcialidad.getIdParcialidad().equals(parcialidadBeneficio.getIdParcialidad())).findAny();
        System.out.println("ENTRA A LAS VALIDACIONES");   
        
        
        //Condicion cuando es el ultimo pesaje
        if(parcialidadPendientePesaje.isPresent()
                && cuentaBeneficio.getEstadoCuenta().equals(Estados.PESAJE_INICIADO)
                && parcialidadesBeneficio.stream()
                        .filter(parcialidad -> parcialidad.getEstadoParcialidad().equals(Estados.PAR_PENDIENTE_PESAR))
                        .count() == 1
                && parcialidadesBeneficio.stream()
                        .filter(parcialidad -> parcialidad.getEstadoParcialidad().equals(Estados.PAR_EN_RUTA) || 
                                parcialidad.getEstadoParcialidad().equals(Estados.PAR_PENDIETE_RECOLECTAR))
                        .count() == 0){
            System.out.println("PASA ULTIMO");
            cuentaBeneficio.setEstadoCuenta(Estados.PESAJE_FINALIZADO);
            bccRepository.save(cuentaBeneficio);
            accsService.actualizarEstadoCuenta(cuentaBeneficio.getNumeroCuenta(), Estados.PESAJE_FINALIZADO);
            boleta = (BcBoletaPesaje) bcBoletaPesajeService.crearBoleta(parcialidadPendientePesaje.get(), pesaje, userName).get("boletaBeneficio");
        }
        
        //Condicion cuando es el primer pesaje
        if(parcialidadPendientePesaje.isPresent()
                && cuentaBeneficio.getEstadoCuenta().equals(Estados.CUENTA_ABIERTA)){
            System.out.println("PASA PRIMER");
            cuentaBeneficio.setEstadoCuenta(Estados.PESAJE_INICIADO);
            bccRepository.save(cuentaBeneficio);
            accsService.actualizarEstadoCuenta(cuentaBeneficio.getNumeroCuenta(), Estados.PESAJE_INICIADO);
            
            boleta = (BcBoletaPesaje) bcBoletaPesajeService.crearBoleta(parcialidadPendientePesaje.get(), pesaje, userName).get("boletaBeneficio");
            
        }
        
        //Condicion cuando es alguna parcialidad intermedia
        if(parcialidadPendientePesaje.isPresent() && cuentaBeneficio.getEstadoCuenta().equals(Estados.PESAJE_INICIADO) &&
                parcialidadesBeneficio.stream()
                        .filter(parcialidad -> parcialidad.getEstadoParcialidad().equals(Estados.PAR_PENDIENTE_PESAR))
                        .count() >= 1) {
            
            boleta = (BcBoletaPesaje) bcBoletaPesajeService.crearBoleta(parcialidadPendientePesaje.get(), pesaje, userName).get("boletaBeneficio");
            System.out.println("PASA INTERMEDIA");
        }
        AgrVehiculos vehiculo = agrVehiculoRepository.findById(parcialidadPendientePesaje.get().getPlacaVehiculo()).get();
        vehiculo.setEstadoVehiculo(Estados.VEHICULO_ASIGNADO_DISPONIBLE);
        agrVehiculoRepository.save(vehiculo);
        
        String[] placas = parcialidadPendientePesaje.get().getLicenciasTransportistas().split(",");
        List<AgrTransportistas> transportistas = agrTransportistaRepository.findAllById(Arrays.asList(placas));
        transportistas.stream().forEach(t -> t.setEstadoTransportista(Estados.TRANSPORTISTA_ASIGNADO_DISPONIBLE));
        agrTransportistaRepository.saveAll(transportistas);
        
        return parcialidadPendientePesaje.isPresent() ? bcMensajesService.postMensaje(BcMensajes.builder()
                        .mensaje("Parcionalidad No. "+String.valueOf(idParcialidad)+" recibida,"
                                + " favor de enviar la siguiente parcialidad.")
                        .numeroCuenta(cuentaBeneficio.getNumeroCuenta())
                        .idParcialidad(idParcialidad)
                        .fechaCreacion(new Date())
                        .parcialidades(0)
                .aprobado(0)
                .correccion(0)
                .totalPesaje(boleta.getResultadoPesaje())
                        .build())
                : null;
        
        
    }
    
    public MensajeDto calculoTolerancia(String numeroCuenta, String userName){
        MensajeDto mensaje = new MensajeDto();
        List<BcBoletaPesaje> boletasBeneficio = bcParcialidadesRepository.findByNumeroCuenta(numeroCuenta).stream().map(parcialidad -> {
        return bcBoletaPesajeRepository.findByIdParcialidad(parcialidad.getIdParcialidad());
        }).collect(Collectors.toList());
        
        //primer map: funciones spret sintax java(Double Colon Expression, operador para llamada de metodo), agrupa los bigdecimal del resultadoPesaje.
        //reduce: 
        BigDecimal totalPesoBoletaParcialidades = boletasBeneficio.stream().map(BcBoletaPesaje::getResultadoPesaje).reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BcCuentaCorriente cuentaCorriente = bccRepository.findByNumeroCuenta(numeroCuenta);
        
        if(cuentaCorriente == null){
            System.out.println("Truena");
        }
        
        BigDecimal porcentajePesoCuentaCorriente = cuentaCorriente.getPesoTotal().multiply(new BigDecimal(0.05));
        
        //La diferencia es igual a el total del peso de la cuenta corriente menos el porcentaje de tolerancia
        BigDecimal diferenciaPeso = cuentaCorriente.getPesoTotal().subtract(porcentajePesoCuentaCorriente);
        
        //Condicion si la diferencia de peso es menor a el total del peso de las boletas
        //ejemplo: 950 contra 1500
        System.out.println("PESO TOTAL" + cuentaCorriente.getPesoTotal() + "MENOS PORCENTAJE: " + diferenciaPeso + "PESO SEGUN BOLETAS: " + totalPesoBoletaParcialidades);
        if(diferenciaPeso.compareTo(totalPesoBoletaParcialidades) == -1){
            System.out.println("PASA POR EL PRIMER IF");
            //se encarga de comparar y retorna 1 para mayor y -1 para menor
            switch(totalPesoBoletaParcialidades.compareTo(cuentaCorriente.getPesoTotal())){                
                //Condicion si el peso total de las boletas es mayor al peso total del beneficio
                //Oseigase la tolerancia excede el 5 por ciento
                //1500 contra 1000; ejemplo;
                case 1:
                    System.out.println("PASA POR EL CASO 1");
                    mensaje = bcMensajesService.postMensaje(BcMensajes.builder()
                        .mensaje("El pesaje total de granos de cafe tiene una tolerancia mayor al 5%,"
                                + " cuenta confirmada")
                        .numeroCuenta(numeroCuenta)
                        .totalPesaje(totalPesoBoletaParcialidades)
                        .fechaCreacion(new Date())
                        .parcialidades(0)
                        .aprobado(51)
                        .correccion(0)
                            .idParcialidad(0)
                        .build());
                    break;
                
                //Oseigase la tolerancia coincide con lo que se acordo
                //985 contra 1000; ejemplo;    
                case -1:
                    System.out.println("PASA POR EL -1");
                    mensaje = bcMensajesService.postMensaje(BcMensajes.builder()
                        .mensaje("El pesaje total de granos de cafe coincide con lo acordado,"
                                + " cuenta confirmada")
                        .numeroCuenta(numeroCuenta)
                        .totalPesaje(totalPesoBoletaParcialidades)
                         .fechaCreacion(new Date())
                            .parcialidades(0)
                        .aprobado(51)
                        .correccion(0)
                            .idParcialidad(0)
                        .build());
                    break;    
            }    
        }
        
        // 1000 contra 500 
        if(diferenciaPeso.compareTo(totalPesoBoletaParcialidades) == 1){
            System.out.println("PASA POR SEGUNDO IF");
            mensaje = bcMensajesService.postMensaje(BcMensajes.builder()
                        .mensaje("El pesaje total de granos de cafe recibido, tiene una tolerancia menor al 5%,"
                                + " cuenta confirmada")
                        .numeroCuenta(numeroCuenta)
                        .totalPesaje(totalPesoBoletaParcialidades)
                        .fechaCreacion(new Date())
                        .parcialidades(0)
                        .aprobado(51)
                        .correccion(0)
                        .idParcialidad(0)
                        .build());
        }
        
        
        return mensaje;
    }
    
    public RespuestaDto actualizarEstadoCuenta(String numeroCuenta, int idEstado){
        BcCuentaCorriente cuenta = bccRepository.findByNumeroCuenta(numeroCuenta);
        cuenta.setEstadoCuenta(idEstado);
        bccRepository.save(cuenta);
        
        RespuestaDto res = new RespuestaDto();
        res.setTitulo("Cuenta Actualizada");
        res.setContenido("Se cambió el estado de la cuenta correctamente");
        return res;
    } 
    
    public List<CuentaProjection> getCuentasBandejas(){       
        
        return bccRepository.findDataCuentaGestionar();
    }
}
