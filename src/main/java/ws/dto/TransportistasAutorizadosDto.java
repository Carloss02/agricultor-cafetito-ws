/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.dto;

public class TransportistasAutorizadosDto {
    private String licencia; 
    private String tipoLicencia;
    private String nombre;
    private String telefono;
    private String email;
    private String estado; 

    public TransportistasAutorizadosDto() {
    }

    public TransportistasAutorizadosDto(String licencia, String tipoLicencia, String nombre, String telefono, String email, String estado) {
        this.licencia = licencia;
        this.tipoLicencia = tipoLicencia;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
