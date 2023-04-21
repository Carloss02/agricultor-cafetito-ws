/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ws.dto.VehiculosAutorizadosDto;
import ws.service.AgrUsuariosService;
import ws.service.BcVehiculosService;
import ws.util.RolesUtil;
import ws.util.Roles;

@RestController
@RequestMapping("/cafetito/vehiculos")
public class BcVehiculosController {
    
    @Autowired private BcVehiculosService bvService;
    @Autowired private AgrUsuariosService agrUsuariosService;
    
    
    @Operation(summary = "Obtiene una lista de vehículos autorizados por el Beneficio de Café.")
    @GetMapping("/autorizados")
    public List<VehiculosAutorizadosDto> getVehiculosAutorizados(Authentication authentication) {
        String username = authentication.getName();
        String rolesUsuario = agrUsuariosService.getRolesByUser(username);
        
        if (RolesUtil.isRolValido(rolesUsuario, Roles.ROL_VENTAS)) {     
            List<VehiculosAutorizadosDto> vehiculosAutorizados = bvService.getVehiculosAutorizados();
            if (vehiculosAutorizados == null || vehiculosAutorizados.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay vehiculos autorizados disponibles");
            }
            return vehiculosAutorizados;
            
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado.");
        }
    }
    
    
    
}
