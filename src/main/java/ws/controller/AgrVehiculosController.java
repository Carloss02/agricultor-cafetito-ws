/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.dto.RegistrarVehiculoDto;
import ws.service.AgrVehiculosService;

@RestController
@RequestMapping("/agricultor/vehiculos")
public class AgrVehiculosController {
    
    @Autowired
    private AgrVehiculosService avsService;
    
    @Operation(summary = "Servicio que registra un nuevo vehículo en el sistema del agricultor y Beneficio de café.")
    @PostMapping("/registrar")
    public RegistrarVehiculoDto registrarVehiculo(@RequestBody RegistrarVehiculoDto vehiculoDto){
        return avsService.registrarVehiculo(vehiculoDto);
    }
    
}
