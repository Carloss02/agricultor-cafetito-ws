/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.cafetito.model.BcUsuarios;
import ws.service.BcUsuariosService;

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
}
