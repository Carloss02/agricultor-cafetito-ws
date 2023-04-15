/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.service.BcCuentaCorrienteService;

@RestController
@RequestMapping("/cafetito/cuenta")
public class BcCuentaCorrienteController {
    
    @Autowired
    private BcCuentaCorrienteService bccService;
    
}
