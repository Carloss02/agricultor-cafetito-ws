/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.cafetito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ws.cafetito.model.BcBoletaPesaje;
import ws.projection.BoletaProjection;

/**
 *
 * @author carlo
 */
public interface BcBoletaPesajeRepository extends JpaRepository<BcBoletaPesaje, Integer>{
    BcBoletaPesaje findByIdBoleta(Integer idBoleta);
    
    BcBoletaPesaje findByIdParcialidad(Integer idParcialidad);
    
    @Query(value = "select \n"
            + "	bbp.id_boleta as \"idBoleta\",\n"
            + "	bbp.resultado_pesaje as \"resultadoPesaje\",\n"
            + "	bbp.fecha_hora_salida as \"fechaPesaje\",\n"
            + "	bbp.usuario_creacion as \"usuarioPeso\",\n"
            + "	bp.id_parcialidad as \"idParcialidad\",\n"
            + "	bp.licencias_transportistas as \"transportistas\",\n"
            + "	bcc.numero_cuenta  as \"noCuenta\",\n"
            + "	bv.placa_vehiculo as \"placaVehiculo\",\n"
            + "	bv.tipo_vehiculo as \"tipoVehiculo\",\n"
            + "	be.nombre_estado as \"estadoParcialidad\",\n"
            + "	be2.nombre_estado as \"estadoCuenta\"\n"
            + "from db_cafetito.bc_boleta_pesaje bbp \n"
            + "inner join db_cafetito.bc_parcialidades bp \n"
            + "on bp.id_parcialidad = bbp.id_parcialidad \n"
            + "inner join db_cafetito.bc_vehiculos bv \n"
            + "on bv.placa_vehiculo = bp.placa_vehiculo\n"
            + "inner join db_cafetito.bc_estados be \n"
            + "on be.id_estado = bp.estado_parcialidad\n"
            + "inner join db_cafetito.bc_cuenta_corriente bcc \n"
            + "on bcc.numero_cuenta = bp.numero_cuenta \n"
            + "inner join db_cafetito.bc_estados be2 \n"
            + "on be2.id_estado = bcc.estado_cuenta \n"
            + "where bp.id_parcialidad = :id", nativeQuery = true)
    BoletaProjection findDataBoleta(@Param("id") Integer idParcialidad);
}
