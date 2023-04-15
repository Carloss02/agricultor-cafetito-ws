/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.cafetito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ws.cafetito.model.BcBitacora;

/**
 *
 * @author carlo
 */
public interface BcBitacoraRepository extends JpaRepository<BcBitacora, Integer>{
    BcBitacora findByIdBitacora(Integer idBitacora);
}
