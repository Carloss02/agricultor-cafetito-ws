/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
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
import ws.agricultor.model.AgrUsuarios;
import ws.agricultor.repository.AgrUsuariosRepository;
import ws.jwt.AgrSecurityUser;

@Service
public class AgrUsuariosService implements UserDetailsService{
    @Autowired
    private AgrUsuariosRepository auRepository;
    
    @Autowired
    private BcUsuariosService bcUsuariosService;
    
    //agregar servicios
    public String getRolesByUser(String username){
        Optional<AgrUsuarios> user = auRepository.findById(username);
        String roles = "";
        if(user.isPresent()){
            roles = user.get().getRoles();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario");
        }
        
        return roles;
    }
    
    /**
     * Metodo para cargar los detalles de un usuario del sistema del agricultor. 
     * @param idUsuario del usuario a cargar
     * @return objeto UserDetails del usuario encontrado
     * @throws UsernameNotFoundException en caso de no encontrar el usuario
     */
    @Override
    public UserDetails loadUserByUsername(String idUsuario) throws UsernameNotFoundException {

        UserDetails userDetails = null;

        try {
            userDetails = auRepository
                    .findByIdUsuario(idUsuario)
                    .map(AgrSecurityUser::new)
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + idUsuario));
        } catch (UsernameNotFoundException e) {
            // cargar usuario en el sistema del beneficio de cafe
            userDetails = bcUsuariosService.loadUserByUsername(idUsuario);
        }

        return userDetails;
    }
    
}
