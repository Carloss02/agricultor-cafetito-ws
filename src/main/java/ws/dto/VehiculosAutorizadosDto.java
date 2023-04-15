/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.dto;

import java.math.BigDecimal;

public class VehiculosAutorizadosDto {
    private String placa; 
    private String marca;
    private int modelo;
    private String color;
    private String tipoVehiculo; 
    private BigDecimal peso; 
    private String estado; 

    public VehiculosAutorizadosDto() {
    }

    public VehiculosAutorizadosDto(String placa, String marca, int modelo, String color, String tipoVehiculo, BigDecimal peso, String estado) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.tipoVehiculo = tipoVehiculo;
        this.peso = peso;
        this.estado = estado;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
