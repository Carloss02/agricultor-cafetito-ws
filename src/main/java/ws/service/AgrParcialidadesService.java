/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.agricultor.model.AgrEstados;
import ws.agricultor.model.AgrParcialidades;
import ws.agricultor.model.AgrVehiculos;
import ws.agricultor.repository.AgrCuentaCorrienteRepository;
import ws.agricultor.repository.AgrEstadosRepository;
import ws.agricultor.repository.AgrParcialidadesRepository;
import ws.agricultor.repository.AgrVehiculosRepository;
import ws.dto.ValidarVehiculoDto;

@Service
public class AgrParcialidadesService {
    @Autowired
    private AgrParcialidadesRepository apRepository;
    @Autowired
    private AgrEstadosRepository aerRepository;
    @Autowired
    private AgrVehiculosRepository avrRepository;
    @Autowired
    private AgrCuentaCorrienteRepository accCuentaRepository;
        
    //agregar servicios
    
    public ValidarVehiculoDto getVehiculoByPlacaEstado(String placa){
        
        //AgrEstados enRuta = aerRepository.findByIdEstado(11);
        AgrParcialidades parcialidad = apRepository.findByPlacaVehiculoAndEstadoParcialidad(placa, 11);
        String numeroCuenta = accCuentaRepository.findByIdCuentaCorriente(parcialidad.getIdCuentaCorriente()).getNumeroCuenta();
        
        AgrVehiculos vehiculo = avrRepository.findByPlacaVehiculo(parcialidad.getPlacaVehiculo());
        
        ValidarVehiculoDto vehiculoDto = new ValidarVehiculoDto(
                parcialidad.getPlacaVehiculo(),
                vehiculo.getMarcaVehiculo(),
                vehiculo.getModeloVehiculo(),
                vehiculo.getColorVehiculo(),
                vehiculo.getTipoVehiculo(),
                vehiculo.getPesoVehiculo(),
                vehiculo.getEstadoVehiculo(),
                numeroCuenta,
                parcialidad.getIdParcialidad()
        );
        
        return vehiculoDto;
    }
}
