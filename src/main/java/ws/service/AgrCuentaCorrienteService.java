/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.agricultor.model.AgrCuentaCorriente;
import ws.agricultor.repository.AgrCuentaCorrienteRepository;
import ws.dto.CreacionCuentaDto;
import ws.dto.CuentaCreadaDto;
import ws.dto.VehiculosAsigDto;
import ws.util.Estados;

@Service
public class AgrCuentaCorrienteService {
    @Autowired
    private AgrCuentaCorrienteRepository accRepository;
    
    @Autowired
    private BcCuentaCorrienteService bcCuentaService;
    
    public Boolean agregarCuenta(CreacionCuentaDto dto, String userName){
        
        System.out.println("PASA POR AQUÍ");
        accRepository.save(
                AgrCuentaCorriente.builder()
                        .pesoTotal(dto.getPeso())
                        .cantidadParcialidades(dto.getCantidad())
                        .estadoCuenta(Estados.CUENTA_CORRIENTE)
                        .fechaCreacion(new Date())
                        .usuarioCreacion(userName)
                        .vehiculosTransportistasAsignados(new Gson().toJson(dto.getVehiculos()))
                .build()
        );
        
        return true;
    }
    
    public void actualizarEstadoCuenta(String numeroCuenta, int idEstado){
        AgrCuentaCorriente cuenta = accRepository.findByNumeroCuenta(numeroCuenta);
        cuenta.setEstadoCuenta(idEstado);
        accRepository.save(cuenta);
    }
    
    public List<CreacionCuentaDto> getCuentasPorAprobarCorreccion(){
        List<AgrCuentaCorriente> cuentas = accRepository.getCuentasEstados();
        
        List<CreacionCuentaDto> lista = cuentas.stream().map(k ->
                bcCuentaService.detalleCuenta(k.getIdCuentaCorriente())
        ).collect(Collectors.toList());
        
        return lista;
    }
}
