/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.agricultor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ws.agricultor.model.AgrVehiculos;

/**
 *
 * @author carlo
 */
public interface AgrVehiculosRepository extends JpaRepository<AgrVehiculos, String>{
    AgrVehiculos findByPlacaVehiculo(String placaVehiculo);
}
