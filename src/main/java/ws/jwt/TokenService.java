/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    
    private final JwtEncoder encoder;

    public TokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }
    
    
    /**
     * Genera un token JWT para la autenticación especificada.
     *
     * @param authentication La autenticación para la cual se generará el token.
     * @return El token JWT generado.
     */
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(""));
        
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1,ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("roles", roles)
                .build();
        
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
