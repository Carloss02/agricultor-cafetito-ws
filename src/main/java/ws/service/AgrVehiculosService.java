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
import org.springframework.transaction.annotation.Transactional;
import ws.agricultor.model.AgrCuentaCorriente;
import ws.agricultor.model.AgrVehiculos;
import ws.agricultor.repository.AgrCuentaCorrienteRepository;
import ws.agricultor.repository.AgrVehiculosRepository;
import ws.dto.RegistrarVehiculoDto;
import ws.dto.VehiculosAsigDto;

@Service
public class AgrVehiculosService {
    @Autowired
    private AgrVehiculosRepository avRepository;
    @Autowired 
    private BcVehiculosService bcVehiculosService;
    
    @Autowired
    private AgrBitacoraService bitacoraService;
    
    @Autowired
    private AgrCuentaCorrienteRepository accRepository;

    //agregar Servicios.
    @Transactional(value = "mysqlTransactionManager")
    public RegistrarVehiculoDto registrarVehiculo(RegistrarVehiculoDto vehiculoDto, String username){
        AgrVehiculos exist = avRepository.findByPlacaVehiculo(vehiculoDto.getPlaca());
        
        if(exist == null){        
        //Agregar logica para obtener usuario quien registra el vehiculo. 
        
        //Registrando nuevo vehículo en el sistema del Agricultor
        //AgrEstados estado = aerRepository.findByIdEstado(14); // estado vehiculo activo
        AgrVehiculos vehiculo = avRepository.save(
                    AgrVehiculos.builder()
                            .placaVehiculo(vehiculoDto.getPlaca())
                            .estadoVehiculo(14)
                            .colorVehiculo(vehiculoDto.getColor())
                            .marcaVehiculo(vehiculoDto.getMarca())
                            .modeloVehiculo(vehiculoDto.getModelo())
                            .pesoVehiculo(vehiculoDto.getPeso())
                            .tipoVehiculo(vehiculoDto.getTipo())
                            .fechaCreacion(new Date())
                            .usuarioCreacion(username)
                    .build()
            );
                
        AgrVehiculos v = avRepository.save(vehiculo);
        bitacoraService.addRecordAgr("agr_vehiculos", v.getPlacaVehiculo(), 'I', v, username);
        //Agregar logica para registrar vehiculo en el sistema de Beneficio de Café. 
       // bcVehiculosService.registrarVehiculo(vehiculoDto, username);
        
        return vehiculoDto;
        }else{
            return null;
        }
    }
    
    @Transactional(value = "mysqlTransactionManager")
    public void cambiarEstadoVehiculo(String placa, int idEstado){
        AgrVehiculos vehiculo = avRepository.findByPlacaVehiculo(placa);
        vehiculo.setEstadoVehiculo(idEstado);
        avRepository.save(vehiculo);
    }
    
    public List<AgrVehiculos> getAll(){
        return avRepository.findAll();
    }
    
    @Transactional(value = "mysqlTransactionManager")
    public AgrVehiculos editarVehiculo(AgrVehiculos vehiculo, String username){
        AgrVehiculos exist = avRepository.findByPlacaVehiculo(vehiculo.getPlacaVehiculo());
        
        if(exist != null){
            AgrVehiculos v = avRepository.save(
                    AgrVehiculos.builder()
                            .placaVehiculo(exist.getPlacaVehiculo())
                            .estadoVehiculo(14)
                            .colorVehiculo(vehiculo.getColorVehiculo())
                            .marcaVehiculo(vehiculo.getMarcaVehiculo())
                            .modeloVehiculo(vehiculo.getModeloVehiculo())
                            .pesoVehiculo(vehiculo.getPesoVehiculo())
                            .tipoVehiculo(vehiculo.getTipoVehiculo())
                            .fechaCreacion(exist.getFechaCreacion())
                    .build()
            );
            bitacoraService.addRecordAgr("agr_vehiculos", v.getPlacaVehiculo(), 'U', v, username);
            return v;
        }else{
            return null;
        }
    }
    
    public List<AgrVehiculos> getVehiculosCuentaDisponiblesAutorizados(Integer idCuenta){
        AgrCuentaCorriente cuenta = accRepository.findByIdCuentaCorriente(idCuenta);
        
        Type tipoListaVehiculos = new TypeToken<List<VehiculosAsigDto>>() {}.getType();
        List<VehiculosAsigDto> autorizados = new Gson().fromJson(cuenta.getVehiculosTransportistasAsignados(), tipoListaVehiculos);
        
        List<AgrVehiculos> disponibles = avRepository.findByEstadoVehiculo(18);
        
        List<AgrVehiculos> filtro = disponibles.stream()
                .filter(d -> autorizados.stream().anyMatch(a -> a.getPlaca().equals(d.getPlacaVehiculo())))
                .collect(Collectors.toList());
        
        return filtro;
    }
    
    public AgrVehiculos getVehiculoByPlaca(String placa){
        return avRepository.findById(placa).orElse(null);
    }
}
