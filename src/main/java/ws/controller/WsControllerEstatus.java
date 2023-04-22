/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.Date;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.dto.WsActiveDto;

@RestController
@RequestMapping("/isWebServiceActive")
public class WsControllerEstatus {
    
    @Operation(summary = "Método de prueba, para verificar que el servicio web esté activo. ")
    @GetMapping
    public WsActiveDto isWebServiceActive(){
        WsActiveDto respuesta = new WsActiveDto(
                "Web Service Running",
                true,
                new Date()
            );
        return respuesta;
    }
}