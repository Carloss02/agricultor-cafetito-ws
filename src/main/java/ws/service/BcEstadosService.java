/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.cafetito.model.BcEstados;
import ws.cafetito.repository.BcEstadosRepository;

/**
 *
 * @author carlos
 */
@Service
public class BcEstadosService {
    
    @Autowired
    private BcEstadosRepository estadosRepository; 
    
    public List<BcEstados> getAllEstados(){
        return estadosRepository.findAll();
    }
    
    public BcEstados getEstadoById(int idEstado){
        return estadosRepository.findByIdEstado(idEstado);
    }
    
    public List<BcEstados> GetEstadosByDescripcion(String descripcion){
        return estadosRepository.findByDescripcion(descripcion);
    }
    
}
