/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.jwt;

import ws.agricultor.model.AgrUsuarios;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;


public class AgrSecurityUser implements UserDetails{
    private final AgrUsuarios usuario;

    /**
     * Crea un nuevo objeto AgrSecurityUser para el usuario especificado.
     * @param usuario El usuario para el cual se creará el objeto.
     */
    public AgrSecurityUser(AgrUsuarios usuario) {
        this.usuario = usuario;
    }

    @Override
    public String getUsername() {
        return usuario.getIdUsuario();
    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * @return Los roles del usuario como una colección de GrantedAuthority.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
}
