/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.agricultor.model.AgrVehiculos;
import ws.agricultor.repository.AgrVehiculosRepository;
import ws.cafetito.model.BcCuentaCorriente;
import ws.cafetito.model.BcVehiculos;
import ws.cafetito.repository.BcVehiculosRepository;
import ws.dto.RegistrarVehiculoDto;
import ws.dto.RespuestaDto;
import ws.dto.VehiculosAutorizadosDto;
import ws.util.Estados;

@Service
public class BcVehiculosService {
    @Autowired
    private BcVehiculosRepository bvRepository;
    
    @Autowired
    private AgrVehiculosRepository avRepository;
    
    public List<VehiculosAutorizadosDto> getVehiculosAutorizados(){
        
        //List<BcVehiculos> vehiculos = bvRepository.findByEstadoVehiculo(16);
        
        List<BcVehiculos> vehiculos = bvRepository.findByEstadoVehiculo(Estados.VEHICULO_AUTORIZADO);
        List<VehiculosAutorizadosDto> mensajesDto = new ArrayList<>();
        vehiculos.forEach((vehiculo) -> {
            mensajesDto.add(vehiculo.tovehiculosAutorizadosDto());
        });
        
        return mensajesDto;
    }
    
    public BcVehiculos registrarVehiculo(RegistrarVehiculoDto vehiculoDto, String username){
        BcVehiculos vehiculo = new BcVehiculos(
                vehiculoDto.getPlaca(),
                Estados.VEHICULO_ACTIVO,
                vehiculoDto.getMarca(), 
                vehiculoDto.getModelo(), 
                vehiculoDto.getColor(), 
                vehiculoDto.getTipo(), 
                vehiculoDto.getPeso(),
                username, 
                new Date()
        );
        
        return bvRepository.save(vehiculo);
    }
    
    public RespuestaDto autorizarVehiculo(String placa, String username){
        AgrVehiculos esvehiculoAutorizado = avRepository.findByPlacaVehiculo(placa);
        RespuestaDto res = new RespuestaDto();
        Optional<BcVehiculos> vehiculo = bvRepository.findById(placa);
        
        if(!vehiculo.isPresent()){
            BcVehiculos v = bvRepository.save(
                    BcVehiculos.builder()
                            .placaVehiculo(esvehiculoAutorizado.getPlacaVehiculo())
                            .colorVehiculo(esvehiculoAutorizado.getColorVehiculo())
                            .estadoVehiculo(Estados.VEHICULO_AUTORIZADO)
                            .fechaCreacion(new Date())
                            .marcaVehiculo(esvehiculoAutorizado.getMarcaVehiculo())
                            .modeloVehiculo(esvehiculoAutorizado.getModeloVehiculo())
                            .pesoVehiculo(esvehiculoAutorizado.getPesoVehiculo())
                            .tipoVehiculo(esvehiculoAutorizado.getTipoVehiculo())
                            .usuarioCreacion(username)                            
                    .build()
            );
            
            
            res.setTitulo("Vehículo Autorizado");
            res.setContenido("Se ha autorizado el vehículo correctamente ");
            return res;
            
        }else{
            vehiculo.get().setEstadoVehiculo(Estados.VEHICULO_AUTORIZADO);
            bvRepository.save(vehiculo.get());
            res.setTitulo("Vehículo Autorizado");
            res.setContenido("Se ha autorizado el vehículo correctamente ");
            return res;
        }
        
    }
    
    public RespuestaDto rechazarVehículo(String placa, String username){
        AgrVehiculos esvehiculoAutorizado = avRepository.findByPlacaVehiculo(placa);
        RespuestaDto res = new RespuestaDto();
        Optional<BcVehiculos> vehiculo = bvRepository.findById(placa);
        
        if(!vehiculo.isPresent()){
            BcVehiculos v = bvRepository.save(
                    BcVehiculos.builder()
                            .placaVehiculo(esvehiculoAutorizado.getPlacaVehiculo())
                            .colorVehiculo(esvehiculoAutorizado.getColorVehiculo())
                            .estadoVehiculo(Estados.VEHICULO_RECHAZADO)
                            .fechaCreacion(new Date())
                            .marcaVehiculo(esvehiculoAutorizado.getMarcaVehiculo())
                            .modeloVehiculo(esvehiculoAutorizado.getModeloVehiculo())
                            .pesoVehiculo(esvehiculoAutorizado.getPesoVehiculo())
                            .tipoVehiculo(esvehiculoAutorizado.getTipoVehiculo())
                            .usuarioCreacion(username)                            
                    .build()
            );
            
            res.setTitulo("Vehículo Rechazado");
            res.setContenido("Se ha rechazado el vehículo correctamente ");
            return res;
            
        }else{
            vehiculo.get().setEstadoVehiculo(Estados.VEHICULO_RECHAZADO);
            bvRepository.save(vehiculo.get());
            
            res.setTitulo("Vehículo Rechazado");
            res.setContenido("Se ha rechazado el vehículo correctamente ");
            return res;
        }
        
    }
    
    public List<AgrVehiculos> getVehiculosCreados(){
        List<BcVehiculos> agregados = bvRepository.findAll();
        List<AgrVehiculos> vienen = avRepository.findAll();
        
        List<AgrVehiculos> sinAgregar = vienen.stream()
                .filter(t -> agregados.stream().noneMatch(a -> a.getPlacaVehiculo().equals(t.getPlacaVehiculo())))
                .collect(Collectors.toList());
        
        return sinAgregar;
    }
    
    public RespuestaDto pesajeVehiculo(String placa, BigDecimal peso, String username){
        BcVehiculos vehiculo = bvRepository.findByPlacaVehiculo(placa);
        AgrVehiculos esvehiculoAutorizado = avRepository.findByPlacaVehiculo(placa);
        System.out.println("QUE TRAE LA VALIDACION" + !(esvehiculoAutorizado.getPesoVehiculo().compareTo(peso) == 0) + " VALORES agricultor" + esvehiculoAutorizado.getPesoVehiculo() + " PESAJE" + peso);
        if(!(esvehiculoAutorizado.getPesoVehiculo().compareTo(peso) == 0)){
            BcVehiculos v = bvRepository.save(
                    BcVehiculos.builder()
                            .placaVehiculo(esvehiculoAutorizado.getPlacaVehiculo())
                            .colorVehiculo(esvehiculoAutorizado.getColorVehiculo())
                            .estadoVehiculo(Estados.VEHICULO_RECHAZADO)
                            .fechaCreacion(new Date())
                            .marcaVehiculo(esvehiculoAutorizado.getMarcaVehiculo())
                            .modeloVehiculo(esvehiculoAutorizado.getModeloVehiculo())
                            .pesoVehiculo(peso)
                            .tipoVehiculo(esvehiculoAutorizado.getTipoVehiculo())
                            .usuarioCreacion(username)                            
                    .build()
            );
        }
        
        RespuestaDto res = new RespuestaDto();
        res.setTitulo("Pesaje Exitoso");
        res.setContenido("Se ha pesado el vehiculo correctamente");
        return res;
    } 
    
}
