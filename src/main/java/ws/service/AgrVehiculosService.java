/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ws.agricultor.model.AgrVehiculos;
import ws.agricultor.repository.AgrVehiculosRepository;
import ws.dto.RegistrarVehiculoDto;
import ws.util.Estados;

@Service
public class AgrVehiculosService {
    @Autowired
    private AgrVehiculosRepository avRepository;
    @Autowired 
    private BcVehiculosService bcVehiculosService;
    
    @Autowired
    private AgrBitacoraService bitacoraService;

    //agregar Servicios.
    @Transactional(value = "mysqlTransactionManager")
    public RegistrarVehiculoDto registrarVehiculo(RegistrarVehiculoDto vehiculoDto, String username){
        AgrVehiculos exist = avRepository.findByPlacaVehiculo(vehiculoDto.getPlaca());
        
        if(exist == null){        
        //Agregar logica para obtener usuario quien registra el vehiculo. 
        
        //Registrando nuevo vehículo en el sistema del Agricultor
        //AgrEstados estado = aerRepository.findByIdEstado(14); // estado vehiculo activo
        AgrVehiculos vehiculo = new AgrVehiculos(
                vehiculoDto.getPlaca(),
                Estados.VEHICULO_ACTIVO,
                vehiculoDto.getMarca(), 
                vehiculoDto.getModelo(), 
                vehiculoDto.getColor(), 
                vehiculoDto.getTipo(), 
                vehiculoDto.getPeso(),
                username, 
                new Date()
        );
                
        AgrVehiculos v = avRepository.save(vehiculo);
        bitacoraService.addRecordAgr("agr_vehiculos", v.getPlacaVehiculo(), 'I', v, username);
        //Agregar logica para registrar vehiculo en el sistema de Beneficio de Café. 
        bcVehiculosService.registrarVehiculo(vehiculoDto, username);
        
        return vehiculoDto;
        }else{
            return null;
        }
    }
    
    @Transactional(value = "mysqlTransactionManager")
    public void cambiarEstadoVehiculo(String placa, int idEstado){
        AgrVehiculos vehiculo = avRepository.findByPlacaVehiculo(placa);
        vehiculo.setEstadoVehiculo(idEstado);
        avRepository.save(vehiculo);
    }
    
    public List<AgrVehiculos> getAll(){
        return avRepository.findAll();
    }
    
    @Transactional(value = "mysqlTransactionManager")
    public AgrVehiculos editarVehiculo(AgrVehiculos vehiculo, String username){
        AgrVehiculos exist = avRepository.findByPlacaVehiculo(vehiculo.getPlacaVehiculo());
        
        if(exist != null){
            AgrVehiculos v = avRepository.save(
                    AgrVehiculos.builder()
                            .estadoVehiculo(14)
                            .colorVehiculo(vehiculo.getColorVehiculo())
                            .marcaVehiculo(vehiculo.getMarcaVehiculo())
                            .modeloVehiculo(vehiculo.getModeloVehiculo())
                            .pesoVehiculo(vehiculo.getPesoVehiculo())
                            .tipoVehiculo(vehiculo.getTipoVehiculo())
                    .build()
            );
            bitacoraService.addRecordAgr("agr_vehiculos", v.getPlacaVehiculo(), 'U', v, username);
            return v;
        }else{
            return null;
        }
    }
}
