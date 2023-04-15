/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.dto.RegistrarTransportistaDto;
import ws.dto.ValidarTransportistaDto;
import ws.service.AgrTransportistasService;

@RestController
@RequestMapping("/agricultor/transportistas")
public class AgrTransportistasController {
    
    @Autowired
    private AgrTransportistasService atService;
    
    
    @Operation(summary = "Obtiene los datos de un transportista para validar en Garita de Beneficio de Café.")
    @GetMapping("/validar/{licencia}")
    public ValidarTransportistaDto getTransportistaByLicencia(@PathVariable String licencia){
        return atService.getTransportistaByLicencia(licencia);
    }
    
    @Operation(summary = "Servicio que registra un transportistas en el sistema del agricultor y Beneficio de café.")
    @PostMapping("/registrar")
    public RegistrarTransportistaDto registrarVehiculo(@RequestBody RegistrarTransportistaDto transportistaDto){
        return atService.registrarTransportista(transportistaDto);
    }
}
