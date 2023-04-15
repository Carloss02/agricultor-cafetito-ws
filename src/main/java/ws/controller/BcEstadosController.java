/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.cafetito.model.BcEstados;
import ws.service.BcEstadosService;

/**
 *
 * @author carlos
 */
@RestController
@RequestMapping("/cafetito/estados")
public class BcEstadosController {
    
    @Autowired
    private BcEstadosService bcEstadosService;
    
    @GetMapping
    public List<BcEstados> getEstados(){
        return bcEstadosService.getAllEstados();
    }
    
    @GetMapping("/{idEstado}")
    public BcEstados getEstadoById(@PathVariable int idEstado){
        return bcEstadosService.getEstadoById(idEstado);
    }
    
    @GetMapping("/{descripcion}")
    public List<BcEstados> getEstadoByDescripcion(@PathVariable String descripcion){
        return bcEstadosService.GetEstadosByDescripcion(descripcion);
    }
}
