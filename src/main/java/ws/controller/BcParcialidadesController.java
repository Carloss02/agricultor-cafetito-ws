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
import ws.dto.ParcialidadEnviadaDto;
import ws.dto.RespuestaDto;
import ws.projection.ParcialidadProjection;
import ws.service.BcParcialidadesService;
import ws.service.BcUsuariosService;
import ws.util.Roles;
import ws.util.RolesUtil;

@RestController
@RequestMapping("/cafetito/parcialidades")
public class BcParcialidadesController {
    
    @Autowired
    private BcParcialidadesService bpService;
    @Autowired
    private BcUsuariosService bcUsuariosService;
    
    @Operation(summary = "Autorizar Ingreso Vehículo y transportista al Beneficio de Café")
    @PostMapping("/autorizarIngreso/{idParcialidad}")
    public RespuestaDto autorizarIngreso(
            @PathVariable int idParcialidad, 
            Authentication authentication
    )
    {
        String username = authentication.getName(); 
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if(RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_AUTORIZAR_INGRESO)){
            return bpService.autorizarIngresoVehículo(idParcialidad);
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
        }  
    }
    
    @Operation(summary = "Envia una parcialidad. ")
    @PostMapping("/enviar/parcialidad")
    public ParcialidadEnviadaDto enviarParcialidad(@RequestBody ParcialidadEnviadaDto parcialidadDto){
        return bpService.enviarParcialidad(parcialidadDto);
    }
    
    @GetMapping("/pesaje")
    public List<ParcialidadProjection> parcialidadPesaje( 
            Authentication authentication){
        String username = authentication.getName(); 
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if(RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_PESO_CABAL)){
            return bpService.getParcialidadPesar();
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
        }  
    }
    
    @GetMapping("/ruta")
    public List<ParcialidadProjection> parcialidadEnRuta( 
            Authentication authentication){
        String username = authentication.getName(); 
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if(RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_AUTORIZAR_INGRESO)){
            return bpService.getParcialidadesEnRuta();
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
        }  
    }
    
    @GetMapping("/gestionar/{noCuenta}")
    public List<ParcialidadProjection> parcialidadEnRuta( 
            Authentication authentication,
            @PathVariable String noCuenta){
        String username = authentication.getName(); 
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if(RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_CREAR_CUENTA)){
            return bpService.getParcialidadesCuenta(noCuenta);
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
        }  
    }
    
    @PutMapping("/garita/rechazo/{idParcialidad}")
    public RespuestaDto parcialidadRechazoGarita( 
            Authentication authentication,
            @PathVariable Integer idParcialidad){
        String username = authentication.getName(); 
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if(RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_AUTORIZAR_INGRESO)){
            return bpService.actualizarEstadoRechazoParcialidadGeneral(idParcialidad);
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
        }  
    }
}
