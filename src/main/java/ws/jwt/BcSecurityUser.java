/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.jwt;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ws.cafetito.model.BcUsuarios;

public class BcSecurityUser implements UserDetails{
    private final BcUsuarios usuario;

    /**
     * Crea un nuevo objeto BcSecurityUser para el usuario especificado.
     * @param usuario El usuario para el cual se creará el objeto.
     */
    public BcSecurityUser(BcUsuarios usuario) {
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
        return Arrays.stream(usuario
                .getRoles()
                .split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
