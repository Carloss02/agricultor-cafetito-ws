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

@Service
public class UserService implements UserDetailsService{
    private final AgrUsuariosService agrUsuariosService;
    
    public UserService(AgrUsuariosService agrUsuariosService) {
        this.agrUsuariosService = agrUsuariosService;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = agrUsuariosService.loadUserByUsername(username);
        //if (userDetails == null) {
            //userDetails = bcUsuariosService.loadUserByUsername(username);
        //}
        return userDetails;
    }
}
