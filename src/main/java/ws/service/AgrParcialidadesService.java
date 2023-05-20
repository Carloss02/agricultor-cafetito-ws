/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.agricultor.model.AgrParcialidades;
import ws.agricultor.model.AgrVehiculos;
import ws.agricultor.repository.AgrCuentaCorrienteRepository;
import ws.agricultor.repository.AgrParcialidadesRepository;
import ws.agricultor.repository.AgrVehiculosRepository;
import ws.dto.ParcialidadEnviadaDto;
import ws.dto.ValidarVehiculoDto;
import ws.projection.ParcialidadProjection;
import ws.util.Estados;

@Service
public class AgrParcialidadesService {
    @Autowired
    private AgrParcialidadesRepository apRepository;
    @Autowired
    private AgrVehiculosRepository avrRepository;
    @Autowired
    private AgrCuentaCorrienteRepository accCuentaRepository;
    @Autowired
    private BcParcialidadesService bcParcialidadesService;
    @Autowired
    private AgrVehiculosService avsService;
    @Autowired
    private AgrTransportistasService atsService;
    @Autowired
    private BcMensajesService bcMensajesService;
    
    @Autowired
    private AgrBitacoraService agrBitacoraService;
    
    
    //agregar servicios
    
    public ValidarVehiculoDto getVehiculoByPlacaEstado(String placa){
        
        //AgrEstados enRuta = aerRepository.findByIdEstado(11);
        AgrParcialidades parcialidad = apRepository.findByPlacaVehiculoAndEstadoParcialidad(placa, Estados.PAR_EN_RUTA);
        String numeroCuenta = accCuentaRepository.findByIdCuentaCorriente(parcialidad.getIdCuentaCorriente()).getNumeroCuenta();
        
        AgrVehiculos vehiculo = avrRepository.findByPlacaVehiculo(parcialidad.getPlacaVehiculo());
        
        ValidarVehiculoDto vehiculoDto = new ValidarVehiculoDto(
                parcialidad.getPlacaVehiculo(),
                vehiculo.getMarcaVehiculo(),
                vehiculo.getModeloVehiculo(),
                vehiculo.getColorVehiculo(),
                vehiculo.getTipoVehiculo(),
                vehiculo.getPesoVehiculo(),
                vehiculo.getEstadoVehiculo(),
                numeroCuenta,
                parcialidad.getIdParcialidad()
        );
        
        return vehiculoDto;
    }
    
    public ParcialidadEnviadaDto enviarParcialidad(ParcialidadEnviadaDto dto, String username){
        //actualizando tabla agricultor parcialidades
        AgrParcialidades agrParcialidad = apRepository.findByIdParcialidad(dto.getIdParcialidad());
        
        agrParcialidad.setEstadoParcialidad(Estados.PAR_EN_RUTA);
        agrParcialidad.setPlacaVehiculo(dto.getPlacaVehiculo());
        agrParcialidad.setLicenciasTransportistas(dto.getLicenciasTransportistas());
        agrParcialidad.setUsuarioCreacion(username);
        agrParcialidad.setFechaParcialidadEnviada(new Date());
        
        apRepository.saveAndFlush(agrParcialidad);
        
        //actualizando registro al sistema cafetito
        bcParcialidadesService.replicarParcialidad(dto, username);
         
        //Cambiando estado de vehículo asignado.
        avsService.cambiarEstadoVehiculo(dto.getPlacaVehiculo(), Estados.VEHICULO_ASIGNADO_EN_RUTA);
        //Cambiando estado de transportistas asignados. 
        System.out.println("lic: " + dto.getLicenciasTransportistas());
        atsService.cambiarEstadoTransportista(dto.getLicenciasTransportistas(), Estados.TRANSPORTISTA_ASIGNADO_EN_RUTA);

        //enviando mensaje al beneficio de café con los datos de la parcialidad a Enviar. 
        bcMensajesService.mensajeParcialidadEnviada(dto, username);
        return dto;
    }
    
    public void actualizarEstadoParcialidad(int idParcialida, int idEstado){
        AgrParcialidades parcialidad = apRepository.findByIdParcialidad(idParcialida);
        parcialidad.setEstadoParcialidad(idEstado);
        apRepository.save(parcialidad);
    }
    
    public Boolean agregarParcialidad(AgrParcialidades p){
        apRepository.save(p);
        agrBitacoraService.addRecordAgr("agr_parcialidades", p.getIdParcialidad().toString(), 'I', p, p.getUsuarioCreacion());
        return true;
    }
    
    public List<ParcialidadProjection> getParcialidadesByCuenta(Integer idCuenta){
        return apRepository.getParcialidadesByIdCuenta(idCuenta);
    }
}
