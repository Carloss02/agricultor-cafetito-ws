/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.dto;

public class SiguienteParcialidadDto {
    private int idMensaje;
    private String numeroCuenta;
    private String placaVehiculo;
    private int idParcialidad;
    private String mensaje; 

    public SiguienteParcialidadDto() {
    }

    public SiguienteParcialidadDto(int idMensaje, String numeroCuenta, String placaVehiculo, int idParcialidad, String mensaje) {
        this.idMensaje = idMensaje;
        this.numeroCuenta = numeroCuenta;
        this.placaVehiculo = placaVehiculo;
        this.idParcialidad = idParcialidad;
        this.mensaje = mensaje;
    }

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public int getIdParcialidad() {
        return idParcialidad;
    }

    public void setIdParcialidad(int idParcialidad) {
        this.idParcialidad = idParcialidad;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
