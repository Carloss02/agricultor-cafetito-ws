/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.cafetito.model.BcVehiculos;
import ws.cafetito.repository.BcVehiculosRepository;
import ws.dto.VehiculosAutorizadosDto;
import ws.util.Estados;

@Service
public class BcVehiculosService {
    @Autowired
    private BcVehiculosRepository bvRepository;
    
    public List<VehiculosAutorizadosDto> getVehiculosAutorizados(){
        
        //List<BcVehiculos> vehiculos = bvRepository.findByEstadoVehiculo(16);
        
        List<BcVehiculos> vehiculos = bvRepository.findByEstadoVehiculo(Estados.VEHICULO_AUTORIZADO);
        List<VehiculosAutorizadosDto> mensajesDto = new ArrayList<>();
        vehiculos.forEach((vehiculo) -> {
            mensajesDto.add(vehiculo.tovehiculosAutorizadosDto());
        });
        
        return mensajesDto;
    }
    
}
