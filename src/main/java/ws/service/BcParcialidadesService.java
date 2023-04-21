/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.cafetito.model.BcCuentaCorriente;
import ws.cafetito.model.BcEstados;
import ws.cafetito.model.BcParcialidades;
import ws.cafetito.repository.BcCuentaCorrienteRepository;
import ws.cafetito.repository.BcEstadosRepository;
import ws.cafetito.repository.BcParcialidadesRepository;
import ws.dto.ParcialidadEnviadaDto;

/**
 *
 * @author carlos
 */
@Service
public class BcParcialidadesService {
    @Autowired
    private BcParcialidadesRepository bpRepository;
    
    @Autowired
    private BcEstadosRepository berRepository;
    
    @Autowired
    private BcCuentaCorrienteRepository bccrRepository;
    
    
    
    //Agregar Servicios. 
    
    public ParcialidadEnviadaDto enviarParcialidad(ParcialidadEnviadaDto parcialidadDto){
        
        //agregar logica para cambiar el estado de los vehiculos y/o transportistas 
        //en el sistema del agricultor. 
        BcParcialidades parcialidad = new BcParcialidades(
                parcialidadDto.getNumeroCuenta(),
                11, //parcialidad en 
                parcialidadDto.getPeso(),
                new Date(),
                null,
                parcialidadDto.getLicenciasTransportistas(),
                parcialidadDto.getPlacaVehiculo(),
                "usurio1",
                new Date()
        );
        
        BcParcialidades parcialidadRegistrada = bpRepository.save(parcialidad);
        
        parcialidadDto.setIdParcialidad(parcialidadRegistrada.getIdParcialidad());
        
        //agregar lógica para enviar mensaje al sistema del beneficio de café. 
        
        return parcialidadDto;
    }
}
