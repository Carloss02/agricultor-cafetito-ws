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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.dto.ParcialidadEnviadaDto;
import ws.dto.ValidarVehiculoDto;
import ws.projection.ParcialidadProjection;
import ws.service.AgrParcialidadesService;
import ws.service.AgrUsuariosService;
import ws.util.Roles;
import ws.util.RolesUtil;

@RestController
@RequestMapping("/agricultor/parcialidades")
public class AgrParcialidadesController {
    
    @Autowired
    private AgrParcialidadesService apService;
    @Autowired
    private AgrUsuariosService agrUsuariosService;
    
    
    @Operation(summary = "obtiene datos de un vehiculo para validar en garita de beneficio de café.")
    @GetMapping("/validarVehiculo/{placaVehiculo}")
    public ValidarVehiculoDto getVehiculoByPlaca(@PathVariable String placaVehiculo){
        return apService.getVehiculoByPlacaEstado(placaVehiculo);
    }
    
    @PostMapping("/enviar/parcialidad")
    public ParcialidadEnviadaDto enviarParcialidad(@RequestBody ParcialidadEnviadaDto parcialidadDto, Authentication authentication){
        
        String username = authentication.getName(); 
        String rolesUsuario = agrUsuariosService.getRolesByUser(username);
        
        if(RolesUtil.isRolValido(rolesUsuario, Roles.ROL_ENVIOS)){
            return apService.enviarParcialidad(parcialidadDto, username);
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
        } 
    }
    
    @GetMapping("/{idCuenta}")
    public List<ParcialidadProjection> getParcialidades(
            @PathVariable Integer idCuenta, 
            Authentication authentication){
        
        String username = authentication.getName(); 
        String rolesUsuario = agrUsuariosService.getRolesByUser(username);
        
        if(RolesUtil.isRolValido(rolesUsuario, Roles.ROL_ENVIOS)){
            return apService.getParcialidadesByCuenta(idCuenta);
        } else {
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
        } 
    }
    
    
}
