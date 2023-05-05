/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.agricultor.model.AgrVehiculos;
import ws.dto.RegistrarVehiculoDto;
import ws.service.AgrUsuariosService;
import ws.service.AgrVehiculosService;
import ws.util.RolesUtil;
import ws.util.Roles;

@RestController
@RequestMapping("/agricultor/vehiculos")
public class AgrVehiculosController {
    
    @Autowired
    private AgrVehiculosService avsService;
    
    @Autowired
    private AgrUsuariosService agrUsuariosService;
    
    @Operation(summary = "Servicio que registra un nuevo vehículo en el sistema del agricultor y Beneficio de café.")
    @PostMapping("/registrar")
    public RegistrarVehiculoDto registrarVehiculo(@RequestBody RegistrarVehiculoDto vehiculoDto, Authentication authentication){
        String username = authentication.getName();   
        String rolesUsuario = agrUsuariosService.getRolesByUser(username);
        
        if(RolesUtil.isRolValido(rolesUsuario, Roles.ROL_AGRICULTOR_ADMIN)){
            return avsService.registrarVehiculo(vehiculoDto, username);
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
        }   
    }
    
    @GetMapping("/all")
    public List<AgrVehiculos> getAllTransportistas(Authentication authentication){
        String username = authentication.getName();   
        String rolesUsuario = agrUsuariosService.getRolesByUser(username);
        
        if(RolesUtil.isRolValido(rolesUsuario, Roles.ROL_AGRICULTOR_ADMIN)){
            return avsService.getAll();
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
        } 
    }
    
    @PutMapping("/editar")
    public AgrVehiculos registrarVehiculo(@RequestBody AgrVehiculos vehiculo, Authentication authentication){
        String username = authentication.getName();   
        String rolesUsuario = agrUsuariosService.getRolesByUser(username);
        
        if(RolesUtil.isRolValido(rolesUsuario, Roles.ROL_AGRICULTOR_ADMIN)){
            return avsService.editarVehiculo(vehiculo, username);
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
        }   
    }
    
}
