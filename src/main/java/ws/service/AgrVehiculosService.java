/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    //agregar Servicios.
    
    public RegistrarVehiculoDto registrarVehiculo(RegistrarVehiculoDto vehiculoDto, String username){
        
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
                
        avRepository.save(vehiculo);
        
        //Agregar logica para registrar vehiculo en el sistema de Beneficio de Café. 
        bcVehiculosService.registrarVehiculo(vehiculoDto, username);
        
        return vehiculoDto;
    }
    
    public void cambiarEstadoVehiculo(String placa, int idEstado){
        AgrVehiculos vehiculo = avRepository.findByPlacaVehiculo(placa);
        vehiculo.setEstadoVehiculo(idEstado);
        avRepository.save(vehiculo);
    }
}
