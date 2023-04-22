/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.agricultor.model.AgrParcialidades;
import ws.agricultor.model.AgrVehiculos;
import ws.agricultor.repository.AgrCuentaCorrienteRepository;
import ws.agricultor.repository.AgrEstadosRepository;
import ws.agricultor.repository.AgrParcialidadesRepository;
import ws.agricultor.repository.AgrVehiculosRepository;
import ws.dto.ParcialidadEnviadaDto;
import ws.dto.ValidarVehiculoDto;
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
    private AgrTransportistasService atsService;
    @Autowired
    private BcMensajesService bcMensajesService;
    
    
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
        
        //Creando registro al sistema del agricultor
        AgrParcialidades agrParcialidad = new AgrParcialidades(
                dto.getIdCuentaCorriente(), 
                Estados.PAR_EN_RUTA, 
                dto.getPeso(), 
                new Date(), 
                null, 
                dto.getLicenciasTransportistas(), 
                dto.getPlacaVehiculo(), 
                username, 
                new Date()
        );
        
        AgrParcialidades parcialidadRegistrada = apRepository.save(agrParcialidad);
        dto.setIdParcialidad(parcialidadRegistrada.getIdParcialidad());
        
        //replicando registro al sistema cafetito
        bcParcialidadesService.replicarParcialidad(dto, username);
        
        //Cambiando estado de vehículo asignado.
        avsService.cambiarEstadoVehiculo(dto.getPlacaVehiculo(), Estados.VEHICULO_ASIGNADO_EN_RUTA);
        //Cambiando estado de transportistas asignados. 
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
}
