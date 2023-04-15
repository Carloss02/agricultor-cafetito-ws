/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.cafetito.model.BcMensajes;
import ws.dto.MensajeDto;
import ws.service.BcMensajesService;

@RestController
@RequestMapping("/cafetito/mensajes")
public class BcMensajesController {
    
    @Autowired
    private BcMensajesService bcMensajeService;
    
    @GetMapping("/id/{idMensaje}")
    public MensajeDto getMensajeById(@PathVariable int idMensaje){
        return bcMensajeService.getMensajeByIdMensaje(idMensaje);
    }
    
    @GetMapping("/cuenta/{numeroCuenta}")
    public List<BcMensajes> getMensajeById(@PathVariable String numeroCuenta){
        return bcMensajeService.getMensajesByCuenta(numeroCuenta);
    }
    
    @PostMapping("/enviar")
    public MensajeDto sendMensaje(@RequestBody BcMensajes mensaje){
        return bcMensajeService.postMensaje(mensaje);
    }
    
    @PutMapping("/editar")
    public BcMensajes editMensaje(@RequestBody BcMensajes mensaje){
        return bcMensajeService.putMensaje(mensaje);
    }
    
    @DeleteMapping("/delete")
    public BcMensajes deleteMensaje(@RequestBody BcMensajes mensaje){
        return bcMensajeService.deleteMensaje(mensaje);
    }
}
