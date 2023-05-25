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
import ws.cafetito.model.BcParcialidades;
import ws.projection.ParcialidadProjection;

/**
 *
 * @author carlo
 */
public interface BcParcialidadesRepository extends JpaRepository<BcParcialidades, Integer>{
    BcParcialidades findByIdParcialidad(Integer idParcialidad);
    
    List<BcParcialidades> findByNumeroCuenta(String numeroCuenta);
    
    
    @Query(value = "select \n"
            + "	bp.id_parcialidad as \"idParcialidad\",\n"
            + "	bp.estado_parcialidad as \"estado\",\n"
            + "	bp.licencias_transportistas as \"licencias\",\n"
            + "	bp.peso_parcialidad as \"peso\",\n"
            + "	bcc.numero_cuenta  as \"numeroCuenta\",\n"
            + "	bcc.tipo_medida  as \"tipoMedida\",\n"
            + "	bp.placa_vehiculo as \"placa\",\n"
            + "	be.nombre_estado as \"nombreEstado\",\n"
            + "	bp.fecha_parcialidad_enviada as \"fechaEnvio\"\n"
            + "from  db_cafetito.bc_parcialidades bp \n"
            + "inner join db_cafetito.bc_cuenta_corriente bcc \n"
            + "on bcc.numero_cuenta = bp.numero_cuenta \n"
            + "inner join db_cafetito.bc_estados be \n"
            + "on be.id_estado = bp.estado_parcialidad \n"
            + "where bcc.estado_cuenta in (3,4) and bp.estado_parcialidad = 12\n"
            + "order by bp.fecha_parcialidad_enviada asc ", nativeQuery = true)
    List<ParcialidadProjection> getParcialidadesParaPesar();
    
    @Query(value = "select \n"
            + "	bp.id_parcialidad as \"idParcialidad\",\n"
            + "	bp.estado_parcialidad as \"estado\",\n"
            + "	bp.licencias_transportistas as \"licencias\",\n"
            + "	bp.peso_parcialidad as \"peso\",\n"
            + "	bcc.numero_cuenta  as \"numeroCuenta\",\n"
            + "	bp.placa_vehiculo as \"placa\",\n"
            + "	be.nombre_estado as \"nombreEstado\",\n"
            + "	bp.fecha_parcialidad_enviada as \"fechaEnvio\"\n"
            + "from  db_cafetito.bc_parcialidades bp \n"
            + "inner join db_cafetito.bc_cuenta_corriente bcc \n"
            + "on bcc.numero_cuenta = bp.numero_cuenta \n"
            + "inner join db_cafetito.bc_estados be \n"
            + "on be.id_estado = bp.estado_parcialidad \n"
            + "where bcc.estado_cuenta in (2,3,4) and bp.estado_parcialidad = 11\n"
            + "order by bp.fecha_parcialidad_enviada asc ",nativeQuery = true)
    List<ParcialidadProjection> getParcialidadesEnRuta();
    
    @Query(value = "select \n"
            + "	bp.id_parcialidad as \"idParcialidad\",\n"
            + "	bp.estado_parcialidad as \"estado\",\n"
            + "	bp.numero_cuenta as \"numeroCuenta\",\n"
            + "	bbp.resultado_pesaje as \"peso\",\n"
            + "	be.nombre_estado as \"nombreEstado\",\n"
            + "	bp.licencias_transportistas as \"licencias\",\n"
            + "	bp.placa_vehiculo as \"placa\",\n"
            + "	bp.fecha_parcialidad_enviada as \"fechaEnvio\",\n"
            + "	bp.fecha_parcialidad_entregada as \"fechaEntrega\"\n"
            + "from  db_cafetito.bc_parcialidades bp \n"
            + "inner join db_cafetito.bc_boleta_pesaje bbp \n"
            + "on bbp.id_parcialidad = bp.id_parcialidad \n"
            + "inner join db_cafetito.bc_estados be \n"
            + "on be.id_estado = bp.estado_parcialidad \n"
            + "where bp.numero_cuenta = :noCuenta\n"
            + "order by bp.id_parcialidad  asc",nativeQuery = true)
    List<ParcialidadProjection> findByNumeroCuentaBoletaPeso(@Param("noCuenta")String numeroCuenta);
}
