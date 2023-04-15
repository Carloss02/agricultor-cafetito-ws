/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.agricultor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ws.agricultor.model.AgrBoletaPesaje;

/**
 *
 * @author carlo
 */
public interface AgrBoletaPesajeRepository extends JpaRepository<AgrBoletaPesaje, Integer>{
    AgrBoletaPesaje findByIdBoleta(Integer idBoleta);
}
