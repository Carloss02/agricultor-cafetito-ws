/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.math.BigDecimal;
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
import ws.agricultor.model.AgrVehiculos;
import ws.dto.RespuestaDto;
import ws.dto.VehiculosAutorizadosDto;
import ws.service.AgrUsuariosService;
import ws.service.AgrVehiculosService;
import ws.service.BcUsuariosService;
import ws.service.BcVehiculosService;
import ws.util.RolesUtil;
import ws.util.Roles;

@RestController
@RequestMapping("/cafetito/vehiculos")
public class BcVehiculosController {
    
    @Autowired 
    private BcVehiculosService bvService;
    
    @Autowired 
    private BcUsuariosService bcUsuariosService;
    
    @Autowired
    private AgrVehiculosService agrVehiculoService;
    
    @Autowired
    private AgrUsuariosService agrUsuariosService;
    
    
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
    
    
    @PostMapping("/autorizar/{placa}")
    public RespuestaDto autorizarVehiculo(@PathVariable String placa, Authentication authentication){
        String username = authentication.getName();
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if (RolesUtil.isRolValido(rolesUsuario, Roles.ROL_CAFETITO_ADMIN)) {  
            return bvService.autorizarVehiculo(placa, username);
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado.");
        }
    }
    
    @PostMapping("/rechazar/{placa}")
    public RespuestaDto rechazarVehiculo(@PathVariable String placa, Authentication authentication){
        String username = authentication.getName();
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if (RolesUtil.isRolValido(rolesUsuario, Roles.ROL_CAFETITO_ADMIN)) {  
            return bvService.rechazarVehículo(placa, username);
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado.");
        }
    }
    
    @GetMapping("/{placa}")
    public AgrVehiculos getVehiculosAutorizadosDisponibles(
            @PathVariable String placa,
            Authentication authentication){
        
        String username = authentication.getName();   
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if(RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_PESO_CABAL) || RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_AUTORIZAR_INGRESO)){
            return agrVehiculoService.getVehiculoByPlaca(placa);
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
        } 
    }
    
    @GetMapping("/creados")
    public List<AgrVehiculos> getVehiculosCreados(
            Authentication authentication){
        
        String username = authentication.getName();   
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if(RolesUtil.isRolValido(rolesUsuario, Roles.ROL_CAFETITO_ADMIN)){
            return bvService.getVehiculosCreados();
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
        } 
    }
    
    @PostMapping("/pesaje/{placa}/{peso}")
    public RespuestaDto pesajeVehiculo(
            @PathVariable String placa,
            @PathVariable BigDecimal peso,
            Authentication authentication){
        String username = authentication.getName();
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if (RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_PESO_CABAL)) {  
            return bvService.pesajeVehiculo(placa, peso,username);
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado.");
        }
    }
    
}
