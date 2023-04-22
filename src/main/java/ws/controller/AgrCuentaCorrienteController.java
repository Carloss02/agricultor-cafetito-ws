/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.dto.CreacionCuentaDto;
import ws.dto.CuentaDto;
import ws.dto.TransportistasDto;
import ws.dto.VehiculosAsignadosDto;
import ws.service.AgrCuentaCorrienteService;
import ws.service.AgrUsuariosService;
import ws.util.Roles;
import ws.util.RolesUtil;

@RestController
@RequestMapping("/agricultor/cuenta")
public class AgrCuentaCorrienteController {
    
    @Autowired
    private AgrCuentaCorrienteService accService; 
    
    @Autowired 
    private AgrUsuariosService agrUsuariosService;
    
    @Operation(summary = "Simulando datos de una cuenta usando dtos")
    @GetMapping()
    public CuentaDto getDetalleCuenta(){
        
        List<TransportistasDto> transportistas1 = new ArrayList<>();
        List<TransportistasDto> transportistas2 = new ArrayList<>();
        List<VehiculosAsignadosDto> vehiculos = new ArrayList<>();
        
        TransportistasDto t1 = new TransportistasDto(
                "123456", "A", "Carlos Pirir", "123-4555", "Carlos@gmail.com"
        );
        TransportistasDto t2 = new TransportistasDto(
                "678657", "C", "Enrique Pirir", "5677-4555", "enrique@gmail.com"
        );
        
        TransportistasDto t3 = new TransportistasDto(
                "746456", "C", "Jose Pirir", "6788-4555", "jose@gmail.com"
        );
        
        transportistas1.add(t1);
        transportistas1.add(t2);
        
        transportistas2.add(t3);
        
        VehiculosAsignadosDto v1 = new VehiculosAsignadosDto(
                "123JNJ", "TOYOTA", 2020, "ROJO", "CAMION", BigDecimal.ZERO, transportistas1
        );
        VehiculosAsignadosDto v2 = new VehiculosAsignadosDto(
                "466UJH", "FORD", 2013, "VERDE", "CAMION", BigDecimal.ZERO, transportistas2
        );
        
        vehiculos.add(v1);
        vehiculos.add(v2);
        
        CuentaDto cuenta = new CuentaDto(
                1,
                new BigDecimal(233.4),
                5,
                vehiculos
        );
        
        
        return cuenta;
    }
    
    @Operation(summary = "Obtiene una cuenta con los detalles respectivos en base al id")
    @PostMapping("/crear")
    public Boolean agregarCuenta(
            Authentication authentication,
            @RequestBody CreacionCuentaDto dto){
        
        String username = authentication.getName();
        String rolesUsuario = agrUsuariosService.getRolesByUser(username);
        
        if (RolesUtil.isRolValido(rolesUsuario, Roles.ROL_VENTAS)){
            return accService.agregarCuenta(dto, username);
        }else{
            throw new AccessDeniedException("403 Forbidden. Access Denied. No Roles.");
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acceso denegado.");
        
        }
        
        
    }
}
