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
import ws.dto.TransportistasAutorizadosDto;
import ws.service.BcTransportistasService;

@RestController
@RequestMapping("/cafetito/transportistas")
public class BcTransportistasController {
    
    @Autowired
    private BcTransportistasService btService;
    
    @Operation(summary = "Obtiene una lista de transportistas autorizados por el Beneficio de Café.")
    @GetMapping("/autorizados")
    public List<TransportistasAutorizadosDto> getAutorizados(){
        return btService.getTransporitistasAutorizados(); 
    }
}
