/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.cafetito.model.BcUsuarios;
import ws.cafetito.repository.BcUsuariosRepository;

/**
 *
 * @author carlos
 */
@Service
public class BcUsuariosService {
    @Autowired
    private BcUsuariosRepository bcUsuariosRepository;
    
    public BcUsuarios getUserByUserName(String username){
        return bcUsuariosRepository.findByIdUsuario(username);
    }
    
    public BcUsuarios postUser(BcUsuarios usuario){
        return bcUsuariosRepository.save(usuario);
    }
}
