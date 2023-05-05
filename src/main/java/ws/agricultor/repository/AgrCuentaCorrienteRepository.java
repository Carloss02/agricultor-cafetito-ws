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

/**
 *
 * @author carlo
 */
public interface AgrCuentaCorrienteRepository extends JpaRepository<AgrCuentaCorriente, Integer>{
    AgrCuentaCorriente findByIdCuentaCorriente(Integer idCuentaCorriente);
    
    List<AgrCuentaCorriente> findByEstadoCuenta(Integer idEstado);
    
    AgrCuentaCorriente findByNumeroCuenta(String numeroCuenta);
    
    @Query(value = "SELECT \n"
            + "	acc.NUMERO_CUENTA,\n"
            + "	acc.PESO_TOTAL,\n"
            + "	acc.CANTIDAD_PARCIALIDADES,\n"
            + "	acc.ID_CUENTA_CORRIENTE,\n"
            + "	acc.ESTADO_CUENTA,\n"
            + "	acc.USUARIO_CREACION,\n"
            + "acc.FECHA_CREACION,\n"
            + "	acc.VEHICULOS_TRANSPORTISTAS_ASIGNADOS\n"
            + "FROM agr_cuenta_corriente acc \n"
            + "INNER JOIN agr_parcialidades ap \n"
            + "ON acc.ID_CUENTA_CORRIENTE = ap.ID_CUENTA_CORRIENTE \n"
            + "WHERE acc.ESTADO_CUENTA in (1,8,9)\n"
            + "ORDER BY ap.ID_CUENTA_CORRIENTE", nativeQuery = true)
    List<AgrCuentaCorriente> getCuentasEstados();
    
}
