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
import ws.agricultor.model.AgrParcialidades;
import ws.projection.ParcialidadProjection;

/**
 *
 * @author carlo
 */
public interface AgrParcialidadesRepository extends JpaRepository<AgrParcialidades, Integer>{
    AgrParcialidades findByIdParcialidad(Integer idParcialidad);
    
    AgrParcialidades findByPlacaVehiculoAndEstadoParcialidad(String placa, Integer estado);
    
    @Query(value = "SELECT \n"
            + "	ap.ID_PARCIALIDAD as \"idParcialidad\",\n"
            + "	ap.PESO_PARCIALIDAD as \"peso\",\n"
            + "	ap.FECHA_PARCIALIDAD_ENVIADA as \"fechaEnvio\",\n"
            + "	ap.FECHA_PARCIALIDAD_ENTREGADA as \"fechaEntrega\",\n"
            + "	ap.ESTADO_PARCIALIDAD as \"estado\",\n"
            + "	ae.NOMBRE_ESTADO as \"nombreEstado\",\n"
            + "	ap.LICENCIAS_TRANSPORTISTAS as \"licencias\",\n"
            + "	ap.ID_CUENTA_CORRIENTE as \"idCuentaCorriente\",\n"
            + "	ap.PLACA_VEHICULO as \"placa\",\n"
            + "	acc.NUMERO_CUENTA as \"numeroCuenta\"\n"
            + "FROM agr_parcialidades ap \n"
            + "INNER JOIN agr_cuenta_corriente acc \n"
            + "ON acc.ID_CUENTA_CORRIENTE = ap.ID_CUENTA_CORRIENTE \n"
            + "INNER JOIN agr_estados ae \n"
            + "ON ae.ID_ESTADO = ap.ESTADO_PARCIALIDAD\n"
            + "WHERE ap.ID_CUENTA_CORRIENTE  = :id\n"
            + "ORDER BY ap.ID_PARCIALIDAD ASC", nativeQuery = true)
    List<ParcialidadProjection> getParcialidadesByIdCuenta(@Param("id") Integer idCuenta);
    
}
