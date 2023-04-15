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
public class VehiculosAsignadosDto {
    private String placaVehiculo; 
    private String marcaVehiculo;
    private int modeloVehiculo; 
    private String colorVehiculo;
    private String tipoVehiculo;
    private BigDecimal pesoVehiculo;
    private List<TransportistasDto> transportistas;

    public VehiculosAsignadosDto() {
    }

    public VehiculosAsignadosDto(String placaVehiculo, String marcaVehiculo, int modeloVehiculo, String colorVehiculo, String tipoVehiculo, BigDecimal pesoVehiculo, List<TransportistasDto> transportistas) {
        this.placaVehiculo = placaVehiculo;
        this.marcaVehiculo = marcaVehiculo;
        this.modeloVehiculo = modeloVehiculo;
        this.colorVehiculo = colorVehiculo;
        this.tipoVehiculo = tipoVehiculo;
        this.pesoVehiculo = pesoVehiculo;
        this.transportistas = transportistas;
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

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public BigDecimal getPesoVehiculo() {
        return pesoVehiculo;
    }

    public void setPesoVehiculo(BigDecimal pesoVehiculo) {
        this.pesoVehiculo = pesoVehiculo;
    }

    public List<TransportistasDto> getTransportistas() {
        return transportistas;
    }

    public void setTransportistas(List<TransportistasDto> transportistas) {
        this.transportistas = transportistas;
    }
    
    
    
}
