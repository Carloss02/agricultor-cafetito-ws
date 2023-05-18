/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.cafetito.model.BcMensajes;
import ws.cafetito.repository.BcMensajesRepository;
import ws.dto.MensajeDto;
import ws.dto.ParcialidadEnviadaDto;
import ws.util.Estados;

/**
 *
 * @author carlos
 */
@Service
public class BcMensajesService {
    
    @Autowired
    private BcMensajesRepository mensajesRepository;
    
    
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
    
    public void mensajeParcialidadEnviada(ParcialidadEnviadaDto dto, String username){
        BcMensajes mensaje = new BcMensajes(
                Estados.MENSAJE_PENDIENTE, 
                dto.getNumeroCuenta(), 
                dto.getPlacaVehiculo(), 
                dto.getIdParcialidad(), 
                1, //parcialidades
                dto.getPeso(),
                dto.getMensaje(), 
                0, 
                0, 
                username, 
                new Date()
        );
        
        mensajesRepository.save(mensaje);
    }
    
    public BcMensajes getMensajesByCuentaTolerancia(String numCuenta){
        return mensajesRepository.findByNumeroCuentaAndAprobado(numCuenta, 51);
    }
}
