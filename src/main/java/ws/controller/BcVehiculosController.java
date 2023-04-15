/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.dto.VehiculosAutorizadosDto;
import ws.service.BcVehiculosService;

@RestController
@RequestMapping("/cafetito/vehiculos")
public class BcVehiculosController {
    
    @Autowired
    private BcVehiculosService bvService;
    
    @Operation(summary = "Obtiene una lista de vehículos autorizados por el Beneficio de Café.")
    @GetMapping("/autorizados")
    public List<VehiculosAutorizadosDto> getVehiculosAutorizados(){
        return bvService.getVehiculosAutorizados(); 
    }
    
    
}
