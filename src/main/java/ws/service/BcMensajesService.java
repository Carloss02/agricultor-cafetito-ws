/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.cafetito.model.BcMensajes;
import ws.cafetito.repository.BcEstadosRepository;
import ws.cafetito.repository.BcMensajesRepository;
import ws.dto.MensajeDto;
import ws.util.Estados;

/**
 *
 * @author carlos
 */
@Service
public class BcMensajesService {
    
    @Autowired
    private BcMensajesRepository mensajesRepository;
    
    @Autowired
    private BcEstadosRepository bcEstadosRepository;
    
    public MensajeDto getMensajeByIdMensaje(int idMensaje) {
        BcMensajes mensaje = mensajesRepository.findByIdMensaje(idMensaje);
        return mensaje.toMensajeDto();
    }
    
    public List<BcMensajes> getMensajesByCuenta(String numCuenta){
        return mensajesRepository.findByNumeroCuenta(numCuenta);
    }
    
    public MensajeDto postMensaje(BcMensajes mensaje){
        
        //BcEstados estadoMensaje = bcEstadosRepository.findByIdEstado(24);
        mensaje.setEstadoMensaje(Estados.MENSAJE_PENDIENTE); 
        mensajesRepository.save(mensaje);
        
        return mensaje.toMensajeDto();
    }
    
    public BcMensajes putMensaje(BcMensajes mensaje){
        return mensajesRepository.save(mensaje);
    }
    
    public BcMensajes deleteMensaje(BcMensajes mensaje) {
        mensajesRepository.delete(mensaje);
        return mensaje;
    }
}
