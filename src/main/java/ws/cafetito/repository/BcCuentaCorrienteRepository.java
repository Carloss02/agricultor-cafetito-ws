/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.cafetito.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ws.cafetito.model.BcCuentaCorriente;
import ws.projection.CuentaProjection;

/**
 *
 * @author carlo
 */
public interface BcCuentaCorrienteRepository extends JpaRepository<BcCuentaCorriente,String>{
    BcCuentaCorriente findByNumeroCuenta(String numeroCuenta);
    
    @Query(value = "select \n"
            + "	bcc.numero_cuenta as \"numeroCuenta\",\n"
            + "	bcc.peso_total as \"peso\",\n"
            + "	bcc.cantidad_parcialidades as \"cantidad\",\n"
            + "	bcc.estado_cuenta as \"estado\",\n"
            + "	be.nombre_estado as \"estadoNombre\"\n"
            + "from db_cafetito.bc_cuenta_corriente bcc \n"
            + "inner join db_cafetito.bc_estados be \n"
            + "on be.id_estado = bcc.estado_cuenta  \n"
            + "where bcc.estado_cuenta in (5,6,7)\n"
            + "order by bcc.numero_cuenta asc ", nativeQuery = true)
    List<CuentaProjection> findDataCuentaGestionar();
}
