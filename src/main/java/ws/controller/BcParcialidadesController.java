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
import ws.dto.ParcialidadEnviadaDto;
import ws.service.BcParcialidadesService;

@RestController
@RequestMapping("/cafetito/parcialidades")
public class BcParcialidadesController {
    
    @Autowired
    private BcParcialidadesService bpService;
    
    
    @Operation(summary = "Envia una parcialidad. ")
    @PostMapping("/enviar/parcialidad")
    public ParcialidadEnviadaDto enviarParcialidad(@RequestBody ParcialidadEnviadaDto parcialidadDto){
        return bpService.enviarParcialidad(parcialidadDto);
    }
}
