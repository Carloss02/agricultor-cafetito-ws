/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.agricultor.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ws.agricultor.model.AgrCuentaCorriente;
import ws.projection.CuentaProjection;
import ws.projection.ReporteProjection;

/**
 *
 * @author carlo
 */
public interface AgrCuentaCorrienteRepository extends JpaRepository<AgrCuentaCorriente, Integer>{
    AgrCuentaCorriente findByIdCuentaCorriente(Integer idCuentaCorriente);
    
    List<AgrCuentaCorriente> findByEstadoCuenta(Integer idEstado);
    
    AgrCuentaCorriente findByNumeroCuenta(String numeroCuenta);
    
    @Query(value = "SELECT \n"
            + "	acc.PESO_TOTAL as \"peso\",\n"
            + "	acc.TIPO_MEDIDA as \"tipoMedida\",\n"
            + "	acc.NUMERO_CUENTA as \"numeroCuenta\",\n"
            + "	acc.CANTIDAD_PARCIALIDADES as \"cantidad\",\n"
            + "	acc.ID_CUENTA_CORRIENTE as \"idCuentaCorriente\",\n"
            + "	acc.ESTADO_CUENTA as \"estado\",\n"
            + "	au.NOMBRE_USUARIO as \"agricultor\",\n"
            + "	ae.NOMBRE_ESTADO as \"estadoNombre\"\n"
            + "FROM agr_cuenta_corriente acc \n"
            + "INNER JOIN agr_estados ae \n"
            + "ON ae.ID_ESTADO = acc.ESTADO_CUENTA \n"
            + "INNER JOIN agr_usuarios au \n"
            + "ON au.ID_USUARIO = acc.USUARIO_CREACION \n"
            + "WHERE acc.ESTADO_CUENTA in (:estados)\n"
            + "ORDER BY acc.ID_CUENTA_CORRIENTE", nativeQuery = true)
    List<CuentaProjection> getCuentasEstados(@Param("estados") List<Integer> estados);
    
    @Query(value = "SELECT \n"
            + "	acc.PESO_TOTAL as \"peso\",\n"
            + "	acc.CANTIDAD_PARCIALIDADES as \"cantidad\",\n"
            + "	acc.ID_CUENTA_CORRIENTE as \"idCuentaCorriente\",\n"
            + "	acc.ESTADO_CUENTA as \"estado\",\n"
            + "	au.NOMBRE_USUARIO as \"agricultor\",\n"
            + "	ae.NOMBRE_ESTADO as \"estadoNombre\",\n"
            + "	acc.TIPO_MEDIDA as \"tipoMedida\"\n"
            + "FROM agr_cuenta_corriente acc \n"
            + "INNER JOIN agr_estados ae \n"
            + "ON ae.ID_ESTADO = acc.ESTADO_CUENTA \n"
            + "INNER JOIN agr_usuarios au \n"
            + "ON au.ID_USUARIO = acc.USUARIO_CREACION \n"
            + "WHERE acc.ID_CUENTA_CORRIENTE = :id", nativeQuery = true)
    CuentaProjection getCuentaById(@Param("id") Integer idCuenta);
    
    @Query(value = "SELECT \n"
            + "    u.ID_USUARIO as \"usuarioAgricultor\",\n"
            + "    u.NOMBRE_USUARIO as \"nombreAgricultor\",\n"
            + "    u.TELEFONO_USUARIO as \"telefonoAgricultor\",\n"
            + "    u.EMAIL_USUARIO as \"emailAgricultor\",\n"
            + "    COUNT(c.ID_CUENTA_CORRIENTE) as \"cantidadCuentas\",\n"
            + "    SUM(c.PESO_TOTAL) as \"pesoTotal\",\n"
            + "    SUM(CASE WHEN c.TOLERANCIA = 1 THEN 1 ELSE 0 END) AS \"toleranciaMenor\",\n"
            + "    SUM(CASE WHEN c.TOLERANCIA = 2 THEN 1 ELSE 0 END) AS \"toleranciaCoincide\",\n"
            + "    SUM(CASE WHEN c.TOLERANCIA = 3 THEN 1 ELSE 0 END) AS \"toleranciaMayor\" \n"
            + "FROM \n"
            + "    agr_cuenta_corriente c\n"
            + "    INNER JOIN agr_usuarios u ON c.USUARIO_CREACION = u.ID_USUARIO\n"
            + "WHERE \n"
            + "    c.ESTADO_CUENTA = 7\n"
            + "    AND c.FECHA_CREACION BETWEEN :fechaInicio AND :fechaFin\n"
            + "    \n"
            + "GROUP BY \n"
            + "    u.ID_USUARIO ORDER BY pesoTotal desc", nativeQuery = true)
    List<ReporteProjection> getReporteAgricultores(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);

}
