/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.cafetito.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ws.cafetito.model.BcEstados;
import ws.cafetito.model.BcTransportistas;

/**
 *
 * @author carlo
 */
public interface BcTransportistasRepository extends JpaRepository<BcTransportistas, String>{
    BcTransportistas findByIdLicencia(String idLicencia);
    List<BcTransportistas> findByBcEstados(BcEstados estado);
}
