/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.cafetito.model.BcEstados;
import ws.cafetito.model.BcVehiculos;
import ws.cafetito.repository.BcEstadosRepository;
import ws.cafetito.repository.BcVehiculosRepository;
import ws.dto.VehiculosAutorizadosDto;

@Service
public class BcVehiculosService {
    @Autowired
    private BcVehiculosRepository bvRepository;
    @Autowired
    private BcEstadosRepository berRepository;
    
    public List<VehiculosAutorizadosDto> getVehiculosAutorizados(){
        
        BcEstados autorizado = berRepository.findByIdEstado(16);
        
        List<BcVehiculos> vehiculos = bvRepository.findByBcEstados(autorizado);
        List<VehiculosAutorizadosDto> mensajesDto = new ArrayList<>();
        vehiculos.forEach((vehiculo) -> {
            mensajesDto.add(vehiculo.tovehiculosAutorizadosDto());
        });
        
        return mensajesDto;
    }
    
}
