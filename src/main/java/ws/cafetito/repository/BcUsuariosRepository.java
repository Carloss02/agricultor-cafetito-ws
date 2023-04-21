/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.cafetito.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ws.cafetito.model.BcUsuarios;

/**
 *
 * @author carlos
 */
public interface BcUsuariosRepository extends JpaRepository<BcUsuarios, String>{
    Optional<BcUsuarios> findByIdUsuario(String idUsuario);
}
