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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.agricultor.model.AgrTransportistas;
import ws.dto.RegistrarTransportistaDto;
import ws.dto.ValidarTransportistaDto;
import ws.service.AgrTransportistasService;
import ws.service.AgrUsuariosService;
import ws.util.Roles;
import ws.util.RolesUtil;

@RestController
@RequestMapping("/agricultor/transportistas")
public class AgrTransportistasController {
    
    @Autowired
    private AgrTransportistasService atService;
    @Autowired
    private AgrUsuariosService agrUsuariosService;
    
    
    
    @Operation(summary = "Obtiene los datos de un transportista para validar en Garita de Beneficio de Café.")
    @GetMapping("/validar/{licencia}")
    public ValidarTransportistaDto getTransportistaByLicencia(@PathVariable String licencia){
        return atService.getTransportistaByLicencia(licencia);
    }
    
    @Operation(summary = "Servicio que registra un transportistas en el sistema del agricultor y Beneficio de café.")
    @PostMapping("/registrar")
    public RegistrarTransportistaDto registrarVehiculo(@RequestBody RegistrarTransportistaDto transportistaDto, Authentication authentication){
        String username = authentication.getName();   
        String rolesUsuario = agrUsuariosService.getRolesByUser(username);
        
        if(RolesUtil.isRolValido(rolesUsuario, Roles.ROL_AGRICULTOR_ADMIN)){
            return atService.registrarTransportista(transportistaDto, username);
            
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
        }   
        
    }
    
    
    @GetMapping("/all")
    public List<AgrTransportistas> getAllTransportistas(Authentication authentication){
        String username = authentication.getName();   
        String rolesUsuario = agrUsuariosService.getRolesByUser(username);
        
        if(RolesUtil.isRolValido(rolesUsuario, Roles.ROL_AGRICULTOR_ADMIN)){
            return atService.getAll();
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
        } 
    }
    
    @PutMapping("/editar")
    public AgrTransportistas registrarVehiculo(@RequestBody AgrTransportistas transportista, Authentication authentication){
        String username = authentication.getName();   
        String rolesUsuario = agrUsuariosService.getRolesByUser(username);
        
        if(RolesUtil.isRolValido(rolesUsuario, Roles.ROL_AGRICULTOR_ADMIN)){
            return atService.editarTransportista(transportista, username);
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
        }   
    }
    
    @GetMapping("/disponible/{idCuenta}")
    public List<AgrTransportistas> getTransportistasDisponibleAutorizados(
            @PathVariable Integer idCuenta,
            Authentication authentication){
        String username = authentication.getName();   
        String rolesUsuario = agrUsuariosService.getRolesByUser(username);
        
        if(RolesUtil.isRolValido(rolesUsuario, Roles.ROL_ENVIOS)){
            return atService.getTrasportistasDisponiblesAutorizados(idCuenta);
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
        } 
    }
}
