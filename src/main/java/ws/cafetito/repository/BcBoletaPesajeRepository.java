/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.cafetito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ws.cafetito.model.BcBoletaPesaje;

/**
 *
 * @author carlo
 */
public interface BcBoletaPesajeRepository extends JpaRepository<BcBoletaPesaje, Integer>{
    BcBoletaPesaje findByIdBoleta(Integer idBoleta);
    
    BcBoletaPesaje findByIdParcialidad(Integer idParcialidad);
}
