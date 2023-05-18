/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.projection;

import java.math.BigDecimal;
import java.util.Date;

public interface BoletaProjection {
    
    Integer getIdBoleta();
    BigDecimal getResultadoPesaje();
    Date getFechaPesaje();
    String getUsuarioPeso();
    Integer getIdParcialidad();
    String getTransportistas();
    String getNoCuenta();
    String getPlacaVehiculo();
    String getTipoVehiculo();
    String getEstadoParcialidad();
    String getEstadoCuenta();
    
    
}
