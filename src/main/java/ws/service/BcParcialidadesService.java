/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.cafetito.model.BcParcialidades;
import ws.cafetito.repository.BcCuentaCorrienteRepository;
import ws.cafetito.repository.BcEstadosRepository;
import ws.cafetito.repository.BcParcialidadesRepository;
import ws.dto.ParcialidadEnviadaDto;
import ws.util.Estados;

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
    
    @Autowired
    private BcBitacoraService bcBitacoraService;
    
    
    
    //Agregar Servicios. 
    
    public ParcialidadEnviadaDto enviarParcialidad(ParcialidadEnviadaDto parcialidadDto){
        
        //agregar logica para cambiar el estado de los vehiculos y/o transportistas 
        //en el sistema del agricultor. 
        BcParcialidades parcialidad = new BcParcialidades(
                parcialidadDto.getNumeroCuenta(),
                Estados.PAR_EN_RUTA,
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
    
    public Boolean agregarParcialidad(BcParcialidades p){
        bpRepository.save(p);
        bcBitacoraService.addRecordBc("bc_parcialidades", p.getIdParcialidad().toString(), 'I', p, p.getUsuarioCreacion());
        return true;
    }
}
