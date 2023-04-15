/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.agricultor.model.AgrEstados;
import ws.agricultor.repository.AgrEstadosRepository;

/**
 *
 * @author carlos
 */
@Service
public class AgrEstadosService {
    
    @Autowired
    private AgrEstadosRepository estadosRepository;
    
    public List<AgrEstados> getAllEstados(){
        return estadosRepository.findAll();
    }
    
    public AgrEstados getEstadoById(int idEstado){
        return estadosRepository.findByIdEstado(idEstado);
    }
    
    public AgrEstados getEstadoByDescripcion(String descripcion){
        return estadosRepository.findByDescripcion(descripcion);
    }
    
    
}
