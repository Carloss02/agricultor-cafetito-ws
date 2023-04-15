/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.agricultor.repository;

import ws.agricultor.model.AgrMensajes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author carlos
 */
public interface AgrMensajesRepository extends JpaRepository<AgrMensajes, Integer>{
    
}
