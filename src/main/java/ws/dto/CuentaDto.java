/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author carlo
 */
public class CuentaDto {
    private int idCuentaCorriente;
    private BigDecimal pesoTotal;
    private int parcialidades;
    private List<VehiculosAsignadosDto> vehiculos;

    public CuentaDto() {
    }

    public CuentaDto(int idCuentaCorriente, BigDecimal pesoTotal, int parcialidades, List<VehiculosAsignadosDto> vehiculos) {
        this.idCuentaCorriente = idCuentaCorriente;
        this.pesoTotal = pesoTotal;
        this.parcialidades = parcialidades;
        this.vehiculos = vehiculos;
    }

    public int getIdCuentaCorriente() {
        return idCuentaCorriente;
    }

    public void setIdCuentaCorriente(int idCuentaCorriente) {
        this.idCuentaCorriente = idCuentaCorriente;
    }

    public BigDecimal getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(BigDecimal pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public int getParcialidades() {
        return parcialidades;
    }

    public void setParcialidades(int parcialidades) {
        this.parcialidades = parcialidades;
    }

    public List<VehiculosAsignadosDto> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<VehiculosAsignadosDto> vehiculos) {
        this.vehiculos = vehiculos;
    }
    
    
}
