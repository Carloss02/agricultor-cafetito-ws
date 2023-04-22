/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.dto;

import java.math.BigDecimal;

public class ParcialidadEnviadaDto {
    
    int idCuentaCorriente; 
    private String numeroCuenta;
    private String placaVehiculo;
    private int idParcialidad;
    private BigDecimal peso;
    private String mensaje;
    private String licenciasTransportistas;

    public ParcialidadEnviadaDto() {
    }

    public ParcialidadEnviadaDto(int idCuentaCorriente, String numeroCuenta, String placaVehiculo, int idParcialidad, BigDecimal peso, String mensaje, String licenciasTransportistas) {
        this.idCuentaCorriente = idCuentaCorriente; 
        this.numeroCuenta = numeroCuenta;
        this.placaVehiculo = placaVehiculo;
        this.idParcialidad = idParcialidad;
        this.peso = peso;
        this.mensaje = mensaje;
        this.licenciasTransportistas = licenciasTransportistas;
    }
    
    public int getIdCuentaCorriente() {
        return idCuentaCorriente;
    }

    public void setIdCuentaCorriente(int idCuentaCorriente) {
        this.idCuentaCorriente = idCuentaCorriente;
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

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getLicenciasTransportistas() {
        return licenciasTransportistas;
    }

    public void setLicenciasTransportistas(String licenciasTransportistas) {
        this.licenciasTransportistas = licenciasTransportistas;
    }
    
    
    
}
