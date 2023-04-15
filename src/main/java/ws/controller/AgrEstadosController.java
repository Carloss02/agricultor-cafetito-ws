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
import ws.agricultor.model.AgrEstados;
import ws.service.AgrEstadosService;

/**
 *
 * @author carlos
 */
@RestController
@RequestMapping("/agricultor/estados")
public class AgrEstadosController {
    
    @Autowired
    private AgrEstadosService agrEstadosService;
    
    @GetMapping
    public List<AgrEstados> getEstados(){
        return agrEstadosService.getAllEstados();
    }
    
    @GetMapping("/id/{idEstado}")
    public AgrEstados getEstadoById(@PathVariable int idEstado){
        return agrEstadosService.getEstadoById(idEstado);
    }
    
    @GetMapping("/descripcion/{descripcion}")
    public AgrEstados getEstadoByDescripcion(@PathVariable String descripcion){
        return agrEstadosService.getEstadoByDescripcion(descripcion);
    }
}
