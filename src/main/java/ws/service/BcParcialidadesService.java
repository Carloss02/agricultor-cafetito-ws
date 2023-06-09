/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.cafetito.model.BcCuentaCorriente;
import ws.cafetito.model.BcParcialidades;
import ws.cafetito.repository.BcCuentaCorrienteRepository;
import ws.cafetito.repository.BcParcialidadesRepository;
import ws.dto.ParcialidadEnviadaDto;
import ws.dto.RespuestaDto;
import ws.projection.ParcialidadProjection;
import ws.util.Estados;

/**
 *
 * @author carlos
 */
@Service
public class BcParcialidadesService {
    @Autowired
    private BcParcialidadesRepository bpRepository;
    
    @Autowired
    private BcBitacoraService bcBitacoraService;
    
    @Autowired
    private BcCuentaCorrienteService bccsService;
    
    @Autowired
    private AgrParcialidadesService apsService;
    
    @Autowired
    private AgrCuentaCorrienteService accsService;
    
    @Autowired
    private BcCuentaCorrienteRepository bccRepository;
    
    @Autowired
    private AgrVehiculosService agrVehiculoService;
    
    @Autowired
    private AgrTransportistasService agrTransportistaService;
    
    //Agregar Servicios. 
    
    public ParcialidadEnviadaDto enviarParcialidad(ParcialidadEnviadaDto parcialidadDto){
        
        //agregar logica para cambiar el estado de los vehiculos y/o transportistas 
        //en el sistema del agricultor. 
        BcParcialidades parcialidad = new BcParcialidades(
                parcialidadDto.getNumeroCuenta(),
                Estados.PAR_EN_RUTA,
                parcialidadDto.getPeso(),
                new Date(),
                null,
                parcialidadDto.getLicenciasTransportistas(),
                parcialidadDto.getPlacaVehiculo(),
                "usurio1",
                new Date()
        );
        
        BcParcialidades parcialidadRegistrada = bpRepository.save(parcialidad);
        
        parcialidadDto.setIdParcialidad(parcialidadRegistrada.getIdParcialidad());
        
        //agregar lógica para enviar mensaje al sistema del beneficio de café. 
        
        return parcialidadDto;
    }
    
    public Boolean agregarParcialidad(BcParcialidades p){
        bpRepository.save(p);
        bcBitacoraService.addRecordBc("bc_parcialidades", p.getIdParcialidad().toString(), 'I', p, p.getUsuarioCreacion());
        return true;
    }
    
    public BcParcialidades replicarParcialidad(ParcialidadEnviadaDto dto, String username){
        
        BcParcialidades bcParcialidad = bpRepository.findByIdParcialidad(dto.getIdParcialidad());
        
        bcParcialidad.setEstadoParcialidad(Estados.PAR_EN_RUTA);
        bcParcialidad.setPlacaVehiculo(dto.getPlacaVehiculo());
        bcParcialidad.setLicenciasTransportistas(dto.getLicenciasTransportistas());
        bcParcialidad.setUsuarioCreacion(username);
        bcParcialidad.setFechaParcialidadEnviada(new Date());
        return bpRepository.save(bcParcialidad);
    }
    
    public RespuestaDto autorizarIngresoVehículo(int idParcialidad){
            Optional<BcParcialidades> bcParcialidad = bpRepository.findById(idParcialidad);
            boolean primeraParcialidad = false;
            if (bcParcialidad.isPresent()) {               
                //valida si, es la primera parcialidad, cambia el estado
                // de la cuenta a Cuenta Abierta. 
                Optional<BcCuentaCorriente> cuenta = bccRepository.findById(bcParcialidad.get().getNumeroCuenta());
                if (cuenta.isPresent() && cuenta.get().getEstadoCuenta() == 2) {
                    List<BcParcialidades> parcialidades = bpRepository.findByNumeroCuenta(bcParcialidad.get().getNumeroCuenta());
                    primeraParcialidad = parcialidades.stream()
                            .filter(p -> p.getEstadoParcialidad() == 12).count() == 0;

                    if (primeraParcialidad) {
                        //actualiza cuenta en el sistema del agricultor. 
                        accsService.actualizarEstadoCuenta(bcParcialidad.get().getNumeroCuenta(), Estados.CUENTA_ABIERTA);
                        //actualiza cuenta en el sistem del beneficio de cafe
                        bccsService.actualizarEstadoCuenta(bcParcialidad.get().getNumeroCuenta(), Estados.CUENTA_ABIERTA);
                    }
                }                
                //cambiando estado en el sistema del beneficio de café. 
                actualizarEstadoParcialidad(idParcialidad, Estados.PAR_PENDIENTE_PESAR);
                //cambiando estado en el sistema agricultor
                apsService.actualizarEstadoParcialidad(idParcialidad, Estados.PAR_PENDIENTE_PESAR);
                
                RespuestaDto res = new RespuestaDto();
                res.setTitulo("Ingreso Autorizado");
                res.setContenido("Puede proceder a ingresar.");
                return res;
            }else{
                return null;
            }
            
    }
    
    public void actualizarEstadoParcialidad(int idParcialidad, int idEstado){
        BcParcialidades parcialidad = bpRepository.findByIdParcialidad(idParcialidad); 
        parcialidad.setEstadoParcialidad(idEstado);
        bpRepository.save(parcialidad);
    }
    
    /*public boolean esPrimeraParcialidad(String numeroCuenta){
        List<BcParcialidades> parcialidades = bpRepository.findByNumeroCuenta(numeroCuenta);
        
        return parcialidades.size() == 1; 
    }*/
    
    public List<ParcialidadProjection> getParcialidadPesar(){
        return bpRepository.getParcialidadesParaPesar();
    }
    
    public List<ParcialidadProjection> getParcialidadesEnRuta(){
        return bpRepository.getParcialidadesEnRuta();
    }
    
    
    public List<ParcialidadProjection> getParcialidadesCuenta(String noCuenta){
        return bpRepository.findByNumeroCuentaBoletaPeso(noCuenta);
    }
    
    public RespuestaDto actualizarEstadoRechazoParcialidadGeneral(int idParcialidad){ 
        
        Optional<BcParcialidades> bcParcialidad = bpRepository.findById(idParcialidad);
            boolean primeraParcialidad = false;
            if (bcParcialidad.isPresent()) { 
                System.out.println("LINEA 160");
                BcCuentaCorriente cuentaBeneficio = bccRepository.findByNumeroCuenta(bcParcialidad.get().getNumeroCuenta());
                List<BcParcialidades> parcialidades = bpRepository.findByNumeroCuenta(bcParcialidad.get().getNumeroCuenta());
                
                Optional<BcParcialidades> parcialidadEnRuta = parcialidades.stream()
                .filter(parcialidad -> parcialidad.getEstadoParcialidad()
                        .equals(Estados.PAR_EN_RUTA) && parcialidad.getIdParcialidad().equals(bcParcialidad.get().getIdParcialidad())).findAny();
                //valida si, es la primera parcialidad, cambia el estado
                // de la cuenta a Cuenta Abierta. 
                Optional<BcCuentaCorriente> cuenta = bccRepository.findById(bcParcialidad.get().getNumeroCuenta());
                if (cuenta.isPresent() && cuenta.get().getEstadoCuenta() == 2) {
                    System.out.println("LINEA 171 valida el estado de la cuenta creada");
                    if (parcialidades.size() > 1) {
                        System.out.println("LINEA 173 las parcialidades mayor que una");
                        primeraParcialidad = parcialidades.stream()
                                .filter(p -> p.getEstadoParcialidad() == 12).count() == 0;
                        
                        //valida si es la ultima pracialidad
                        if (parcialidadEnRuta.isPresent()
                                && cuentaBeneficio.getEstadoCuenta().equals(Estados.PESAJE_INICIADO)
                                && parcialidades.stream()
                                        .filter(parcialidad -> parcialidad.getEstadoParcialidad().equals(Estados.PAR_EN_RUTA))
                                        .count() == 1
                                && parcialidades.stream()
                                        .filter(parcialidad -> parcialidad.getEstadoParcialidad().equals(Estados.PAR_PENDIETE_RECOLECTAR))
                                        .count() == 0) {

                            accsService.actualizarEstadoCuenta(bcParcialidad.get().getNumeroCuenta(), Estados.PESAJE_FINALIZADO);
                            //actualiza cuenta en el sistem del beneficio de cafe
                            bccsService.actualizarEstadoCuenta(bcParcialidad.get().getNumeroCuenta(), Estados.PESAJE_FINALIZADO);
                        }
                        //valida si es la primera parcialidad para cambiar el estado
                        if (primeraParcialidad) {
                            //actualiza cuenta en el sistema del agricultor. 
                            accsService.actualizarEstadoCuenta(bcParcialidad.get().getNumeroCuenta(), Estados.CUENTA_ABIERTA);
                            //actualiza cuenta en el sistem del beneficio de cafe
                            bccsService.actualizarEstadoCuenta(bcParcialidad.get().getNumeroCuenta(), Estados.CUENTA_ABIERTA);
                        }
                        
                    }else{
                        accsService.actualizarEstadoCuenta(bcParcialidad.get().getNumeroCuenta(), Estados.PESAJE_FINALIZADO);
                            //actualiza cuenta en el sistem del beneficio de cafe
                        bccsService.actualizarEstadoCuenta(bcParcialidad.get().getNumeroCuenta(), Estados.PESAJE_FINALIZADO);
                    }
                    
                } 
        this.actualizarEstadoParcialidad(idParcialidad, Estados.PARCIALIDAD_RECHAZADA);
        apsService.actualizarEstadoParcialidad(idParcialidad, Estados.PARCIALIDAD_RECHAZADA);
        
        agrTransportistaService.cambiarEstadoTransportista(bcParcialidad.get().getLicenciasTransportistas(), Estados.TRANSPORTISTA_ASIGNADO_DISPONIBLE);
        agrVehiculoService.cambiarEstadoVehiculo(bcParcialidad.get().getPlacaVehiculo(), Estados.VEHICULO_ASIGNADO_DISPONIBLE);
        
        RespuestaDto res = new RespuestaDto();
                res.setTitulo("Rechazo Aceptado");
                res.setContenido("Se ha denegado el ingreso de la parcialidad.");
                return res;
    }else{
                return null;
          }
    }
    
}
