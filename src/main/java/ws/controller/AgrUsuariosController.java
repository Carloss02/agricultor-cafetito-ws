/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.service.AgrUsuariosService;
import ws.util.Roles;
import ws.util.RolesUtil;

@RestController
@RequestMapping("/agricultor/usuarios")
public class AgrUsuariosController {
    
    @Autowired
    private AgrUsuariosService ausService;
    
    @GetMapping("/rol/{usuario}")
    public String getRolesByUsuario(
            @PathVariable String usuario,
            Authentication authentication){
        String username = authentication.getName();
        String rolesUsuario = ausService.getRolesByUser(username);
        
        if (RolesUtil.isRolValido(rolesUsuario, Roles.ROL_VENTAS)|| RolesUtil.isRolValido(rolesUsuario, Roles.ROL_ENVIOS) || RolesUtil.isRolValido(rolesUsuario, Roles.ROL_AGRICULTOR_ADMIN)){
            return ausService.getRolesByUser(usuario);
        }else{
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado.");
        
        }
        
    }
}
