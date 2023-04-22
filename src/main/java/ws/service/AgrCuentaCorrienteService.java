/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import com.google.gson.Gson;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.agricultor.model.AgrCuentaCorriente;
import ws.agricultor.repository.AgrCuentaCorrienteRepository;
import ws.dto.CreacionCuentaDto;
import ws.util.Estados;

@Service
public class AgrCuentaCorrienteService {
    @Autowired
    private AgrCuentaCorrienteRepository accRepository;
    
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
}
