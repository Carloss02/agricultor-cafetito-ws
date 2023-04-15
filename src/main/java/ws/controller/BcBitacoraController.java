/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.service.BcBitacoraService;

@RestController
@RequestMapping("/cafetito/bitacora")
public class BcBitacoraController {
    
    @Autowired
    private BcBitacoraService bbService;
    
    /*
    usar las siguientes anotaciones para los servicios REST
    @GetMapping
    @PostMapping
    @PutMapping
    @DeleteMapping
    */
    
}
