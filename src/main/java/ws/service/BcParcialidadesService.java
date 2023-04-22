/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.cafetito.model.BcParcialidades;
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
    private BcBitacoraService bcBitacoraService;
    
    @Autowired
    private BcCuentaCorrienteService bccsService;
    
    @Autowired
    private AgrParcialidadesService apsService;
    
    @Autowired
    private AgrCuentaCorrienteService accsService;
    
    
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
    
    public BcParcialidades replicarParcialidad(ParcialidadEnviadaDto dto, String username){
        
        BcParcialidades bcParcialidad = new BcParcialidades(
                dto.getIdParcialidad(),
                dto.getNumeroCuenta(),
                Estados.PAR_EN_RUTA,
                dto.getPeso(), 
                new Date(), 
                null, 
                dto.getLicenciasTransportistas(), 
                dto.getPlacaVehiculo(), 
                username, 
                new Date()
        );

        return bpRepository.save(bcParcialidad);
    }
    
    public String autorizarIngresoVehículo(int idParcialidad){
            BcParcialidades bcParcialidad = bpRepository.findByIdParcialidad(idParcialidad);
            //cambiando estado en el sistema del beneficio de café. 
            actualizarEstadoParcialidad(idParcialidad, Estados.PAR_PENDIENTE_PESAR);
            //cambiando estado en el sistema agricultor
            apsService.actualizarEstadoParcialidad(idParcialidad, Estados.PAR_PENDIENTE_PESAR);

            //valida si, es la primera parcialidad, cambia el estado
            // de la cuenta a Cuenta Abierta. 
            if(esPrimeraParcialidad(bcParcialidad.getNumeroCuenta())){
                //actualiza cuenta en el sistema del agricultor. 
                accsService.actualizarEstadoCuenta(bcParcialidad.getNumeroCuenta(), Estados.CUENTA_ABIERTA);
                //actualiza cuenta en el sistem del beneficio de cafe
                bccsService.actualizarEstadoCuenta(bcParcialidad.getNumeroCuenta(), Estados.CUENTA_ABIERTA);
            }
        
        return "Ingreso Autorizado";
    }
    
    public void actualizarEstadoParcialidad(int idParcialidad, int idEstado){
        BcParcialidades parcialidad = bpRepository.findByIdParcialidad(idParcialidad); 
        parcialidad.setEstadoParcialidad(idEstado);
        bpRepository.save(parcialidad);
    }
    
    public boolean esPrimeraParcialidad(String numeroCuenta){
        List<BcParcialidades> parcialidades;
        parcialidades = bpRepository.findByNumeroCuenta(numeroCuenta);
        
        return parcialidades.size() == 1; 
    }
}
