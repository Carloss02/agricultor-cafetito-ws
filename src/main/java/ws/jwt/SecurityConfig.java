/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.jwt;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import java.util.Arrays;
import static org.apache.coyote.http11.Constants.a;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import static org.springframework.security.config.Customizer.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Clase de Configuracion de seguridad para la aplicacion
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    private final UserService userService;
    private final RsaKeyProperties rsaKeys;

    /**
     * Constructor de la clase SecurityConfig
     * @param userService Servicio de Usuario
     * @param rsaKeys  Clave RSA
     */
    public SecurityConfig(UserService userService, RsaKeyProperties rsaKeys) {
        this.userService = userService;
        this.rsaKeys = rsaKeys;
    } 
    
    /**
     * Metodo que configura el filtro de seguridad
     * @param http Configuracion de seguridad
     * @return El filtro de seguridad
     * @throws Exception En caso de error al crear el filtro. 
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .authorizeRequests(auth -> auth  // urls sin seguridad. 
                        .antMatchers("/swagger-ui-custom.html/**").permitAll()
                        .antMatchers("/agricultor-cafetito-ws/**").permitAll()
                        .anyRequest().authenticated()
                )
                .userDetailsService(userService)
                .headers(headers -> headers.frameOptions().sameOrigin())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(withDefaults()) 
                .build();
    }
    
    /**
     * Metodo que configura la fuente de configuracion CORS.
     * @return La fuente de configuracon CORS.
     */
    @Bean 
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","https://cafetito-agricultor-app.azurewebsites.net"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization","content-type"));
       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       source.registerCorsConfiguration("/**", configuration);
       return source;
    }
    
    
    /**
     * Configuracion del Bean que decodifica tokens JWT utilizando la
     * implementacion NimbusJwtDecoder de Spring Security. 
     * 
     * @return El objeto JwtDecoder que utiliza la clave publica RSA almacenada
     * en la clase RsaKeyProperties para decodificar tokens JWT. 
     */
    @Bean
    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(rsaKeys.getPublicKey()).build();
    }
    
    /**
     * Configuracion del Bean que codifica tokens JWT utilizando la implementacion
     * NimbusJwtEncoder de Spring Security
     * 
     * @return El objeto JwtEncoder que utiliza la clave publica y privada RSA 
     * almacenadas en la clase RsaKeyProperties para codificar tokens JWT
     */
    @Bean
    JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey.Builder(rsaKeys.getPublicKey()).privateKey(rsaKeys.getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
    
    /**
     * Configuracion del Bean de encriptacion de contraseñas utilizando la
     * implementacion de BCryptPasswordEncoder de Spring Security.
     * 
     * @return El objeto PasswordEncoder que utiliza la implementacion
     * BcryptPasswordEncoder para encriptar contraseñas
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * Ejemplo para encriptar contraseñas
    /*
    public static void main(String[] args){
        System.out.println("pas: " + new BCryptPasswordEncoder().encode("password1"));
    } 
    */
    /*
    public static void main(String[] args){
        System.out.println("pas: " + new BCryptPasswordEncoder().encode("admin"));
    } 
    */
}
