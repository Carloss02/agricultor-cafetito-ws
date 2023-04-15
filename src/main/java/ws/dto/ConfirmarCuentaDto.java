/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.dto;

import java.math.BigDecimal;


public class ConfirmarCuentaDto {
    
    private int idMensaje;
    private String numeroCuenta; 
    private int totalParcialidades;
    private BigDecimal totalPesaje; 
    private String mensaje; 

    public ConfirmarCuentaDto() {
    }

    public ConfirmarCuentaDto(int idMensaje, String numeroCuenta, int totalParcialidades, BigDecimal totalPesaje, String mensaje) {
        this.idMensaje = idMensaje;
        this.numeroCuenta = numeroCuenta;
        this.totalParcialidades = totalParcialidades;
        this.totalPesaje = totalPesaje;
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

    public int getTotalParcialidades() {
        return totalParcialidades;
    }

    public void setTotalParcialidades(int totalParcialidades) {
        this.totalParcialidades = totalParcialidades;
    }

    public BigDecimal getTotalPesaje() {
        return totalPesaje;
    }

    public void setTotalPesaje(BigDecimal totalPesaje) {
        this.totalPesaje = totalPesaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
