/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.dto;

public class AutenticacionDto {
    
    private String token;
    private String username;
    private String roles;
    private String nombre;
    private String correo;

    public AutenticacionDto() {
    }

    public AutenticacionDto(String token, String username, String roles, String nombre, String correo) {
        this.token = token;
        this.username = username;
        this.roles = roles;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
    
}
