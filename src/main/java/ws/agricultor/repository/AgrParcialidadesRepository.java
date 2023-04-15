/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.agricultor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ws.agricultor.model.AgrEstados;
import ws.agricultor.model.AgrParcialidades;

/**
 *
 * @author carlo
 */
public interface AgrParcialidadesRepository extends JpaRepository<AgrParcialidades, Integer>{
    AgrParcialidades findByIdParcialidad(Integer idParcialidad);
    AgrParcialidades findByPlacaVehiculoAndAgrEstados(String placa, AgrEstados estado);
}
