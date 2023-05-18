/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ws.dto.CreacionCuentaDto;
import ws.dto.CuentaCreadaDto;
import ws.dto.MensajeDto;
import ws.dto.ParamCuentaDto;
import ws.dto.RespuestaDto;
import ws.projection.CuentaProjection;
import ws.service.BcCuentaCorrienteService;
import ws.service.BcUsuariosService;
import ws.util.Roles;
import ws.util.RolesUtil;

@RestController
@RequestMapping("/cafetito/cuenta")
public class BcCuentaCorrienteController {
    
    @Autowired
    private BcCuentaCorrienteService bccService;
    
    @Autowired 
    private BcUsuariosService bcUsuariosService;
    
    
    @Operation(summary = "Obtiene una cuenta con los detalles respectivos en base al id")
    @GetMapping("/detalle/{id}")
    public CreacionCuentaDto cuentaDetalle(
            Authentication authentication,
            @PathVariable Integer id){
        String username = authentication.getName();
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if (RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_CREAR_CUENTA)){
            
            return bccService.detalleCuenta(id);
            
        }else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado.");
        }
        
    }
    
    
    @Operation(summary = "Obtiene una lista de cuentas con los detalles respectivos en base al estado")
    @GetMapping("/estado/{estado}")
    public List<CreacionCuentaDto> cuentasDetalleEstado(
            Authentication authentication,
            @PathVariable Integer estado){
        String username = authentication.getName();
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if (RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_CREAR_CUENTA)){
            
            return bccService.cuentasDetalles(estado);
            
        }else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado.");
        }
        
    }
    
    @PostMapping("/crear")
    public CuentaCreadaDto crearCuenta(
            Authentication authentication,
            @RequestBody ParamCuentaDto params){
        
        String username = authentication.getName();
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if (RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_CREAR_CUENTA)){
            
            return bccService.crearCuenta(params, username);
            
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado.");
        }
        
    }
    
    @PostMapping("/pesaje/{idParcialidad}/{peso}")
    public MensajeDto pesajeParcialidad(
            Authentication authentication,
            @PathVariable Integer idParcialidad,
            @PathVariable BigDecimal peso ){
        System.out.println("QUE TRAE" + idParcialidad);
        String username = authentication.getName();
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if (RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_PESO_CABAL)){
            
            return bccService.pesaje(idParcialidad, peso, username);
            
        }else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado.");
        }
        
    }
    
    @PostMapping("/tolerancia/{noCuenta}")
    public MensajeDto pesajeParcialidadTolerancia(
            Authentication authentication,
            @PathVariable String noCuenta){
        
        String username = authentication.getName();
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if (RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_CREAR_CUENTA)){
            
            return bccService.calculoTolerancia(noCuenta, username);
        }else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado.");
        }
    }
    
    @PutMapping("/actualizar/{cuenta}/{idEstado}")
    public RespuestaDto actualizarEstadoCuenta(
            Authentication authentication,
            @PathVariable String cuenta,
            @PathVariable Integer idEstado){
        
        String username = authentication.getName();
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if (RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_CREAR_CUENTA)){
            
            return bccService.actualizarEstadoCuenta(cuenta, idEstado);
            
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado.");
        }
        
    }
    
    @GetMapping("/gestionar")
    public List<CuentaProjection> cuentaGestionar(
            Authentication authentication){
        String username = authentication.getName();
        String rolesUsuario = bcUsuariosService.getRolesByUser(username);
        
        if (RolesUtil.isRolValido(rolesUsuario, Roles.ROLE_CREAR_CUENTA)){
            
            return bccService.getCuentasBandejas();
            
        }else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado.");
        }
        
    }
    
}
