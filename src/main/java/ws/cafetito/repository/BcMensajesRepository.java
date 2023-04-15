/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.cafetito.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ws.cafetito.model.BcMensajes;

/**
 *
 * @author carlos
 */
public interface BcMensajesRepository extends JpaRepository<BcMensajes, Integer>{
    BcMensajes findByIdMensaje(Integer idMensaje);
    List<BcMensajes> findByNumeroCuenta(String numeroCuenta);
}
