/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.agricultor.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ws.agricultor.model.AgrUsuarios;

/**
 *
 * @author carlo
 */
public interface AgrUsuariosRepository extends JpaRepository<AgrUsuarios, String>{
    Optional<AgrUsuarios> findByIdUsuario(String idUsuario);
}
