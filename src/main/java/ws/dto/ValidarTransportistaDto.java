/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.dto;

public class ValidarTransportistaDto {
    private String numeroLicencia; 
    private String tipoLicencia; 
    private String nombre;
    private String telefono; 
    private String email;
    private int estado; 

    public ValidarTransportistaDto() {
    }
    
    public ValidarTransportistaDto(String numeroLicencia, String tipoLicencia, String nombre, String telefono, String email, int estado) {
        this.numeroLicencia = numeroLicencia;
        this.tipoLicencia = tipoLicencia;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
}
