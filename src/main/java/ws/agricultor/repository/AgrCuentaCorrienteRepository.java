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
    
}
