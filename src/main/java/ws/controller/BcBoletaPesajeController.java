/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.controller;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.projection.BoletaProjection;
import ws.service.BcBoletaPesajeService;
import ws.service.BcUsuariosService;
import ws.util.Roles;
import ws.util.RolesUtil;

@RestController
@RequestMapping("/cafetito/boletapesaje")
public class BcBoletaPesajeController {
    
    @Autowired
    private BcBoletaPesajeService bbpService;
    
    @Autowired
    private BcUsuariosService bcUsuariosService;
    
    @GetMapping("/{idParcialidad}")
    public BoletaProjection getDatosBoleta(
            Authentication authentication,
            @PathVariable Integer idParcialidad){
        String username = authentication.getName();
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if (RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_PESO_CABAL)){
            
            return bbpService.getDatosBoleta(idParcialidad);
            
        }else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado.");
        }
    
    }
    
}
