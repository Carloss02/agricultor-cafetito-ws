/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    
    @PostMapping("/login")
    public String token(Authentication authentication){
        String token = tokenService.generateToken(authentication);
        return token;
    }
}
