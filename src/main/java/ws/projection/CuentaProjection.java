/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.projection;

import java.math.BigDecimal;

public interface CuentaProjection {
    
    Integer getIdCuentaCorriente();
    String getNumeroCuenta();
    BigDecimal getPeso();
    Integer getCantidad();
    String getAgricultor();
    String getEstadoNombre();
    Integer getEstado();
    String getTipoMedida();
}
