/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.agricultor.model.AgrVehiculos;
import ws.agricultor.repository.AgrVehiculosRepository;
import ws.cafetito.model.BcVehiculos;
import ws.cafetito.repository.BcVehiculosRepository;
import ws.dto.RegistrarVehiculoDto;
import ws.dto.VehiculosAutorizadosDto;
import ws.util.Estados;

@Service
public class BcVehiculosService {
    @Autowired
    private BcVehiculosRepository bvRepository;
    
    @Autowired
    private AgrVehiculosRepository avRepository;
    
    public List<VehiculosAutorizadosDto> getVehiculosAutorizados(){
        
        //List<BcVehiculos> vehiculos = bvRepository.findByEstadoVehiculo(16);
        
        List<BcVehiculos> vehiculos = bvRepository.findByEstadoVehiculo(Estados.VEHICULO_AUTORIZADO);
        List<VehiculosAutorizadosDto> mensajesDto = new ArrayList<>();
        vehiculos.forEach((vehiculo) -> {
            mensajesDto.add(vehiculo.tovehiculosAutorizadosDto());
        });
        
        return mensajesDto;
    }
    
    public BcVehiculos registrarVehiculo(RegistrarVehiculoDto vehiculoDto, String username){
        BcVehiculos vehiculo = new BcVehiculos(
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
        
        return bvRepository.save(vehiculo);
    }
    
    public String autorizarVehiculo(String placa){
        BcVehiculos vehiculo = bvRepository.findByPlacaVehiculo(placa);
        vehiculo.setEstadoVehiculo(Estados.VEHICULO_AUTORIZADO);
        bvRepository.save(vehiculo);
        return "Vehículo Autorizado";
    }
    
    public String rechazarVehículo(String placa){
        BcVehiculos vehiculo = bvRepository.findByPlacaVehiculo(placa);
        vehiculo.setEstadoVehiculo(Estados.VEHICULO_RECHAZADO);
        bvRepository.save(vehiculo);
        return "Vehículo Rechazado";
    }
    
    public List<AgrVehiculos> getVehiculosCreados(){
        return avRepository.findByEstadoVehiculo(14);
    }
    
}
