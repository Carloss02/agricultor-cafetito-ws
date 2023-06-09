/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.cafetito.model.BcUsuarios;
import ws.service.BcUsuariosService;
import ws.util.Roles;
import ws.util.RolesUtil;

/**
 *
 * @author carlo
 */
@RestController
@RequestMapping("/cafetito/usuarios")
public class BcUsuariosController {
    @Autowired
    private BcUsuariosService bcUsuariosService;
    
    @PostMapping("/crearUsuario")
    public BcUsuarios addUsuario(@RequestBody BcUsuarios usuario){
        return bcUsuariosService.postUser(usuario);
    }
    
    @GetMapping("/rol/{usuario}")
    public String getRolesByUsuario(
            @PathVariable String usuario,
            Authentication authentication){
        String username = authentication.getName();
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if (RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_AUTORIZAR_INGRESO)
                || RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_CREAR_CUENTA) 
                || RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_PESO_CABAL)
                || RolesUtil.isRolValido(rolesUsuario, Roles.ROL_CAFETITO_ADMIN)){
            return bcUsuariosService.getRolesByUser(usuario);
        }else{
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado.");
        
        }
        
    }
}
