/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.jwt;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.agricultor.model.AgrUsuarios;
import ws.agricultor.repository.AgrUsuariosRepository;
import ws.cafetito.model.BcUsuarios;
import ws.cafetito.repository.BcUsuariosRepository;
import ws.dto.AutenticacionDto;

@RestController
public class AuthController {
    @Autowired
    private AgrUsuariosRepository agrUsuarioRepository;
    
    @Autowired
    private BcUsuariosRepository bcUsuariosRepository;
    
    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    
    @GetMapping("/login")
    public AutenticacionDto token(Authentication authentication){
        String username = authentication.getName();
        String token = tokenService.generateToken(authentication);
        Optional<AgrUsuarios> agricultor = agrUsuarioRepository.findByIdUsuario(username);
        AutenticacionDto dto = new AutenticacionDto();        
        
        if(agricultor.isPresent()){
            dto.setNombre(agricultor.get().getNombreUsuario());
            dto.setCorreo(agricultor.get().getEmailUsuario());
            dto.setRoles(agricultor.get().getRoles());
            dto.setUsername(agricultor.get().getIdUsuario());
            dto.setToken(token);
            
            return dto;
            
        }else{
            Optional<BcUsuarios> cafetito = bcUsuariosRepository.findByIdUsuario(username);
            if(cafetito.isPresent()){
                dto.setNombre(cafetito.get().getNombreUsuario());
                dto.setCorreo(cafetito.get().getEmailUsuario());
                dto.setRoles(cafetito.get().getRoles());
                dto.setUsername(cafetito.get().getIdUsuario());
                dto.setToken(token);
                return dto;
            }else{
                return null;
            }
            
        }
        
    }
}
