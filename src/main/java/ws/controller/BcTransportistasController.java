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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ws.dto.TransportistasAutorizadosDto;
import ws.service.AgrUsuariosService;
import ws.service.BcTransportistasService;
import ws.service.BcUsuariosService;
import ws.util.Roles;
import ws.util.RolesUtil;

@RestController
@RequestMapping("/cafetito/transportistas")
public class BcTransportistasController {
    
    @Autowired
    private BcTransportistasService btService;
    
    @Autowired 
    private BcUsuariosService bcUsuariosService;
    
    @Autowired 
    private AgrUsuariosService agrUsuariosService;
    
    @Operation(summary = "Obtiene una lista de transportistas autorizados por el Beneficio de Café.")
    @GetMapping("/autorizados")
    public List<TransportistasAutorizadosDto> getAutorizados(Authentication authentication){
        String username = authentication.getName();
        List<TransportistasAutorizadosDto> transportistas;
        
        //obteniendo roles de usuario agricultor. 
        String rolesUsuario = agrUsuariosService.getRolesByUser(username);
        
        if (RolesUtil.isRolValido(rolesUsuario, Roles.ROL_VENTAS)) {
            transportistas = btService.getTransporitistasAutorizados();
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
        }
       
        if(transportistas.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay vehiculos autorizados disponibles");
        }
        
        return transportistas;
    }
    
    @PostMapping("/autorizar/{licencia}")
    public String autorizarVehiculo(@PathVariable String licencia, Authentication authentication){
        String username = authentication.getName();
        String rolesUsuario = agrUsuariosService.getRolesByUser(username);
        
        if (RolesUtil.isRolValido(rolesUsuario, Roles.ROL_CAFETITO_ADMIN)) {  
            return btService.autorizarTrasportista(licencia);
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado.");
        }
    }
    
    @PostMapping("/rechazar/{licencia}")
    public String rechazarVehiculo(@PathVariable String licencia, Authentication authentication){
        String username = authentication.getName();
        String rolesUsuario = agrUsuariosService.getRolesByUser(username);
        
        if (RolesUtil.isRolValido(rolesUsuario, Roles.ROL_CAFETITO_ADMIN)) {  
            return btService.rechazarTransportista(licencia);
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado.");
        }
    }
}
