/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.service.BcBoletaPesajeService;

@RestController
@RequestMapping("/cafetito/boletapesaje")
public class BcBoletaPesajeController {
    
    @Autowired
    private BcBoletaPesajeService bbpService;
    
}
