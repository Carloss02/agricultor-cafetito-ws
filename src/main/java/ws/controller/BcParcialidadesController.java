/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.dto.ParcialidadEnviadaDto;
import ws.service.BcParcialidadesService;
import ws.service.BcUsuariosService;
import ws.util.Roles;
import ws.util.RolesUtil;

@RestController
@RequestMapping("/cafetito/parcialidades")
public class BcParcialidadesController {
    
    @Autowired
    private BcParcialidadesService bpService;
    @Autowired
    private BcUsuariosService bcUsuariosService;
    
    @Operation(summary = "Autorizar Ingreso Vehículo y transportista al Beneficio de Café")
    @PostMapping("/autorizarIngreso/{idParcialidad}")
    public String autorizarIngreso(
            @PathVariable int idParcialidad, 
            Authentication authentication
    )
    {
        String username = authentication.getName(); 
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if(RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_AUTORIZAR_INGRESO)){
            return bpService.autorizarIngresoVehículo(idParcialidad);
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
        }  
    }
    
    @Operation(summary = "Envia una parcialidad. ")
    @PostMapping("/enviar/parcialidad")
    public ParcialidadEnviadaDto enviarParcialidad(@RequestBody ParcialidadEnviadaDto parcialidadDto){
        return bpService.enviarParcialidad(parcialidadDto);
    }
}
