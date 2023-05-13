/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.projection;

import java.math.BigDecimal;
import java.util.Date;

public interface ParcialidadProjection {
    
    Integer getIdParcialidad();
    Integer getEstado();
    BigDecimal getPeso();
    Date getFechaEnvio();
    Date getFechaEntrega();
    String getNombreEstado();
    Integer getIdCuentaCorriente();
    String getNumeroCuenta();
    String getAutorizo();
    String getPlaca();
    String getLicencias();
    
}
