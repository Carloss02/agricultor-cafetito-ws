/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.dto;

import java.math.BigDecimal;

public class ValidarVehiculoDto {
    private String placaVehiculo;
    private String marcaVehiculo; 
    private int modeloVehiculo; 
    private String colorVehiculo; 
    private String tipovehiculo; 
    private BigDecimal pesoVehiculo; 
    private int estadoVehiculo;
    private String numeroCuenta; 
    private int idParcialidad; 

    public ValidarVehiculoDto() {
    }

    public ValidarVehiculoDto(String placaVehiculo, String marcaVehiculo, int modeloVehiculo, String colorVehiculo, String tipovehiculo, BigDecimal pesoVehiculo, int estadoVehiculo, String numeroCuenta, int idParcialidad) {
        this.placaVehiculo = placaVehiculo;
        this.marcaVehiculo = marcaVehiculo;
        this.modeloVehiculo = modeloVehiculo;
        this.colorVehiculo = colorVehiculo;
        this.tipovehiculo = tipovehiculo;
        this.pesoVehiculo = pesoVehiculo;
        this.estadoVehiculo = estadoVehiculo;
        this.numeroCuenta = numeroCuenta;
        this.idParcialidad = idParcialidad;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public int getModeloVehiculo() {
        return modeloVehiculo;
    }

    public void setModeloVehiculo(int modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    public String getColorVehiculo() {
        return colorVehiculo;
    }

    public void setColorVehiculo(String colorVehiculo) {
        this.colorVehiculo = colorVehiculo;
    }

    public String getTipovehiculo() {
        return tipovehiculo;
    }

    public void setTipovehiculo(String tipovehiculo) {
        this.tipovehiculo = tipovehiculo;
    }

    public BigDecimal getPesoVehiculo() {
        return pesoVehiculo;
    }

    public void setPesoVehiculo(BigDecimal pesoVehiculo) {
        this.pesoVehiculo = pesoVehiculo;
    }

    public int getEstadoVehiculo() {
        return estadoVehiculo;
    }

    public void setEstadoVehiculo(int estadoVehiculo) {
        this.estadoVehiculo = estadoVehiculo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public int getIdParcialidad() {
        return idParcialidad;
    }

    public void setIdParcialidad(int idParcialidad) {
        this.idParcialidad = idParcialidad;
    }
    
    
}
