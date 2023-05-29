/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.projection;

import java.math.BigDecimal;

/**
 *
 * @author carlo
 */
public interface ReporteProjection {
    String getUsuarioAgricultor();
    String getNombreAgricultor();
    String getTelefonoAgricultor();
    String getEmailAgricultor();
    Integer getCantidadCuentas();
    BigDecimal getPesoTotal();
    Integer getToleranciaMenor();
    Integer getToleranciaCoincide();
    Integer getToleranciaMayor();
}
