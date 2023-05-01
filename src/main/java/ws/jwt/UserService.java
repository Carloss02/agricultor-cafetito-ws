/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ws.service.AgrUsuariosService;

/**
 * Clase que represante a un usuario que implementa 
 * la interface UserDetailsService de Spring Security. 
 */
@Service
public class UserService implements UserDetailsService{
    private final AgrUsuariosService agrUsuariosService;
    /**
     * Constructor 
     * @param agrUsuariosService para cargar los detalles de un usuario.  
     */
    public UserService(AgrUsuariosService agrUsuariosService) {
        this.agrUsuariosService = agrUsuariosService;
    }
    
    /**
     * Metodo que carga los detalles de un usuario
     * @param username del usuario a cargar. 
     * @return un objeto UserDetails con los detalles del usuario. 
     * @throws UsernameNotFoundException si no se encuentra el usuario. 
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = agrUsuariosService.loadUserByUsername(username);
        return userDetails;
    }
}
