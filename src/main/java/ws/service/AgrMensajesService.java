/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.service;

import java.util.ArrayList;
import java.util.Date;
import ws.agricultor.model.AgrMensajes;
import ws.agricultor.repository.AgrMensajesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ws.agricultor.repository.AgrEstadosRepository;
import ws.dto.ConfirmarCuentaDto;
import ws.dto.CuentaCreadaDto;
import ws.dto.MensajeDto;
import ws.dto.SiguienteParcialidadDto;
import ws.util.Estados;

/**
 *
 * @author melan
 */
@Service

public class AgrMensajesService {
    @Autowired
    private AgrMensajesRepository mensajesRepository;
    @Autowired
    private AgrEstadosRepository agrEstadosRepository;
    
    public List<MensajeDto> getMensajes(){
        List<AgrMensajes> mensajes = mensajesRepository.findAll();
        
        List<MensajeDto> mensajesDto = new ArrayList<>();
        mensajes.forEach((mensaje) -> {
            mensajesDto.add(mensaje.toMensajeDto());
        });
        
        return mensajesDto;
    }
    
    @Transactional(value = "mysqlTransactionManager")
    public AgrMensajes putMensaje(AgrMensajes mensaje){
        return mensajesRepository.save(mensaje);
    }
    
    @Transactional(value = "msyqlTransactionManager")
    public MensajeDto postMensaje(AgrMensajes mensaje){

        mensaje.setEstadoMensaje(Estados.MENSAJE_PENDIENTE); // Mensaje Pendiente
        //guarda mensaje en la tabla
        AgrMensajes mensajeEnviado = mensajesRepository.save(mensaje);
        //devuelve los datos según metodo de mapeo toMensajeDto()
        return mensajeEnviado.toMensajeDto();
    }
    
    public AgrMensajes deleteMensaje(AgrMensajes mensaje){
        mensajesRepository.delete(mensaje);
        return mensaje;
    }
    
    public CuentaCreadaDto enviarMensajeCuentaCreada(CuentaCreadaDto mensajeDto){
        
        //agregar lógica para guardar numero de cuenta 
        //y cambiar estado a Cuenta Creada en ambos sistemas. 
        //usando: mensajeDto.getNumeroCuenta();
        
        
        //En caso de solicitar corrección, 
        //agregar lógica para cambiar el estado a Cuenta a Corregir
        
        
        //En caso de ser rechazada la venta
        //agregar lógica para cambiar el estado a Cuenta Rechazada 
        
        
        //lógica para enviar el mensaje al sistema del agricultor.        
        AgrMensajes mensaje = new AgrMensajes();
    
        mensaje.setEstadoMensaje(Estados.MENSAJE_PENDIENTE);
        mensaje.setIdCuentaCorriente(mensajeDto.getIdCuentaCorriente());
        mensaje.setNumeroCuenta(mensajeDto.getNumeroCuenta());
        mensaje.setAprobado(mensajeDto.getAprobado());//true/false
        mensaje.setCorreccion(mensajeDto.getCorrecion());//true/false
        mensaje.setMensaje(mensajeDto.getMensaje());
        mensaje.setFechaCreacion(new Date());
        
        mensajesRepository.save(mensaje);
        
        return mensajeDto;
        
    }
    
    public SiguienteParcialidadDto solicitarSiguienteParcialidad(SiguienteParcialidadDto parcialidadEntregada){
        
        //Agregar logica para cambiar el estado de la parcialidad entregada
        
        //Agregar logica para cambiar el estado los vehiculos y transportistas. 
        
        //agregar logica para guardar los datos del pesaje en la tabla de boleta de pesaje. 
        
        
        //logica para enviar mensaje. 
        AgrMensajes mensaje = new AgrMensajes();
        
        mensaje.setNumeroCuenta(parcialidadEntregada.getNumeroCuenta());
        mensaje.setPlacaVehiculo(parcialidadEntregada.getPlacaVehiculo());
        mensaje.setIdParcialidad(parcialidadEntregada.getIdParcialidad());
        mensaje.setMensaje(parcialidadEntregada.getMensaje());
        mensaje.setFechaCreacion(new Date());
        mensaje.setEstadoMensaje(Estados.MENSAJE_PENDIENTE);
        
        AgrMensajes agrMensajes = mensajesRepository.save(mensaje);
        
        parcialidadEntregada.setIdMensaje(agrMensajes.getIdMensaje());
        
        return parcialidadEntregada; 
        
    }
    
    public ConfirmarCuentaDto confirmarCuenta(ConfirmarCuentaDto mensajeDto){
        
        //agregar logica para cambiar estados de la cuenta
        
        //agregar logica para cambiar estado de la ultima parcialidad pesada
        
        //agregar logica para guardar los datos del pesaje en la tabla BoletaPesaje. 
        
        // agregar logica para cambiar los estados de vehiculos y transportistas
        // que entregaron la ultima parcialidad. 
        
        
        
        //logica para enviar mensaje. 
        //AgrEstados estado = agrEstadosRepository.findByIdEstado(26); //mensaje pendiente
        AgrMensajes mensaje = new AgrMensajes();
        
        mensaje.setNumeroCuenta(mensajeDto.getNumeroCuenta());
        mensaje.setParcialidades(mensajeDto.getTotalParcialidades());
        mensaje.setTotalPesaje(mensajeDto.getTotalPesaje());
        mensaje.setMensaje(mensajeDto.getMensaje());
        mensaje.setFechaCreacion(new Date());
        mensaje.setEstadoMensaje(Estados.MENSAJE_PENDIENTE);
        
        AgrMensajes agrMensajes = mensajesRepository.save(mensaje);
        
        mensajeDto.setIdMensaje(agrMensajes.getIdMensaje());
        
        return mensajeDto;
    }
}
