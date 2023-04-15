/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.controller;

import io.swagger.v3.oas.annotations.Operation;
import ws.agricultor.model.AgrMensajes;
import ws.service.AgrMensajesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.dto.ConfirmarCuentaDto;
import ws.dto.CuentaCreadaDto;
import ws.dto.MensajeDto;
import ws.dto.SiguienteParcialidadDto;

@RestController
@RequestMapping("/agricultor/mensajes")//inicia la URL
public class AgrMensajesController {
    @Autowired
    private AgrMensajesService mensajeService;
    
    @GetMapping
    public List<MensajeDto> getMensajes(){
        return mensajeService.getMensajes();
    }
    
    @PutMapping("/editar")
    public AgrMensajes putMensajes(@RequestBody AgrMensajes mensaje){ //requestBody traduce Json a java   
        return mensajeService.putMensaje(mensaje);
    }
    
    @PostMapping("/enviar")
    public MensajeDto postMensajes(@RequestBody AgrMensajes mensaje){
            return mensajeService.postMensaje(mensaje);
    }
    
    @DeleteMapping("/eliminar")
    public AgrMensajes deleteMensajes(@RequestBody AgrMensajes mensaje){
        return mensajeService.deleteMensaje(mensaje);
    }
    
    @Operation(summary = "Mensaje que notifica cuenta creada")
    @PostMapping("/cuentaCreada")
    public CuentaCreadaDto notificarCuentaCreada(@RequestBody CuentaCreadaDto mensajeDto){
        return mensajeService.enviarMensajeCuentaCreada(mensajeDto);
    }
    
    @Operation(summary = "Mensaje para Solicitar Siguiente Parcialidad")
    @PostMapping("/solicitar/parcialidad")
    public SiguienteParcialidadDto notificarCuentaCreada(@RequestBody SiguienteParcialidadDto mensajeDto){
        return mensajeService.solicitarSiguienteParcialidad(mensajeDto);
    }
    
    @Operation(summary = "Mensaje para confirmar Cuenta")
    @PostMapping("/confirmar/cuenta")
    public ConfirmarCuentaDto notificarCuentaCreada(@RequestBody ConfirmarCuentaDto mensajeDto){
        return mensajeService.confirmarCuenta(mensajeDto);
    }
}
