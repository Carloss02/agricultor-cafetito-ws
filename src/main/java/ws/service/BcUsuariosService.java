/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ws.cafetito.model.BcUsuarios;
import ws.cafetito.repository.BcUsuariosRepository;
import ws.jwt.BcSecurityUser;

/**
 *
 * @author carlos
 */
@Service
public class BcUsuariosService implements UserDetailsService {
    @Autowired
    private BcUsuariosRepository bcUsuariosRepository;
    
    public BcUsuarios postUser(BcUsuarios usuario){
        return bcUsuariosRepository.save(usuario);
    }
    
    public String getRolesByUser(String username){
        Optional<BcUsuarios> user = bcUsuariosRepository.findById(username);
        String roles = "";
        if(user.isPresent()){
            roles = user.get().getRoles();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario");
        }
        
        return roles;
    }
    
    /**
     * Metodo que carga un usuario del sistema del Beneficio de Cafe. 
     * @param idUsuario del usuario a cargar
     * @return objeto UserDetails del usuario encontrado
     * @throws UsernameNotFoundException 
     */
    @Override
    public UserDetails loadUserByUsername(String idUsuario) throws UsernameNotFoundException {
        return bcUsuariosRepository
                .findByIdUsuario(idUsuario)
                .map(BcSecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + idUsuario));
    }
}
