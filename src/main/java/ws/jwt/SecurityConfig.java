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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    private final UserService userService;
    private final RsaKeyProperties rsaKeys;

    public SecurityConfig(UserService userService, RsaKeyProperties rsaKeys) {
        this.userService = userService;
        this.rsaKeys = rsaKeys;
    }
    
    
    
    /*
    @Bean
    public InMemoryUserDetailsManager user() {
        return new InMemoryUserDetailsManager(
                User.withUsername("cpirirm")
                        .password("{noop}password")
                        .authorities("read")
                        .build()
        );
    } 
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth -> auth.anyRequest().authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(withDefaults())
                .build();
    } */
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        // Combina ambos servicios de detalles de usuario en un objeto CompositeUserDetailsService
    //CompositeUserDetailsService userDetailsService = new CompositeUserDetailsService();
    //userDetailsService.addUserDetailsService(ausService);
    //userDetailsService.addUserDetailsService(busService);
        
        return http
                .csrf(csrf -> csrf.disable()) /** se deshabilita la protección CSRF */
                .authorizeRequests(auth -> auth
                        .antMatchers("/swagger-ui-custom.html/**").permitAll()
                        .antMatchers("/agricultor-cafetito-ws/**").permitAll()
                        .anyRequest().authenticated() /** se configura la autorización para que cualquier solicitud requiera autenticación  */
                )
                .userDetailsService(userService) /** Se establece el servicio de detalles de usuario para recuperar la información del usuario autenticado  */
                .headers(headers -> headers.frameOptions().sameOrigin()) /** Se habilita la opción de mismo origen en las opciones de encabezado  */
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt) /** Se configura la seguridad de OAuth2 para que use JWT como mecanismo de autenticación  */
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) /** Se establece la política de creación de sesión como no estable para evitar problemas de gestión de sesiones  */
                .httpBasic(withDefaults()) /** se utiliza la autenticación HTTP básica con los valores por defecto  */
                .build();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
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
    
    
    @Bean
    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(rsaKeys.getPublicKey()).build();
    }
    
    @Bean
    JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey.Builder(rsaKeys.getPublicKey()).privateKey(rsaKeys.getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
}
