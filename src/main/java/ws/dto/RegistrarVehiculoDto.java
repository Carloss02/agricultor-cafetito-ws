/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.dto;

import java.math.BigDecimal;

public class RegistrarVehiculoDto {
    private String placa;
    private String marca;
    private int modelo;
    private String color;
    private String tipo;
    private BigDecimal peso;

    public RegistrarVehiculoDto() {
    }

    public RegistrarVehiculoDto(String placa, String marca, int modelo, String color, String tipo, BigDecimal peso) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.tipo = tipo;
        this.peso = peso;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }
    
    
    
}
