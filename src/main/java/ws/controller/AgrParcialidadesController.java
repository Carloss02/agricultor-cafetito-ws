/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.dto.ValidarVehiculoDto;
import ws.service.AgrParcialidadesService;

@RestController
@RequestMapping("/agricultor/parcialidades")
public class AgrParcialidadesController {
    
    @Autowired
    private AgrParcialidadesService apService;
    
    @Operation(summary = "obtiene datos de un vehiculo para validar en garita de beneficio de café.")
    @GetMapping("/validarVehiculo/{placaVehiculo}")
    public ValidarVehiculoDto getVehiculoByPlaca(@PathVariable String placaVehiculo){
        return apService.getVehiculoByPlacaEstado(placaVehiculo);
    }
}
