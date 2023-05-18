/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.cafetito.model.BcCuentaCorriente;
import ws.cafetito.model.BcParcialidades;
import ws.cafetito.repository.BcCuentaCorrienteRepository;
import ws.cafetito.repository.BcParcialidadesRepository;
import ws.dto.ParcialidadEnviadaDto;
import ws.dto.RespuestaDto;
import ws.projection.ParcialidadProjection;
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
    
    @Autowired
    private BcCuentaCorrienteRepository bccRepository;
    
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
        
        BcParcialidades bcParcialidad = bpRepository.findByIdParcialidad(dto.getIdParcialidad());
        
        bcParcialidad.setEstadoParcialidad(Estados.PAR_EN_RUTA);
        bcParcialidad.setPlacaVehiculo(dto.getPlacaVehiculo());
        bcParcialidad.setLicenciasTransportistas(dto.getLicenciasTransportistas());
        bcParcialidad.setUsuarioCreacion(username);

        return bpRepository.save(bcParcialidad);
    }
    
    public RespuestaDto autorizarIngresoVehículo(int idParcialidad){
            Optional<BcParcialidades> bcParcialidad = bpRepository.findById(idParcialidad);
            boolean primeraParcialidad = false;
            if (bcParcialidad.isPresent()) {               
                //valida si, es la primera parcialidad, cambia el estado
                // de la cuenta a Cuenta Abierta. 
                Optional<BcCuentaCorriente> cuenta = bccRepository.findById(bcParcialidad.get().getNumeroCuenta());
                if (cuenta.isPresent() && cuenta.get().getEstadoCuenta() == 2) {
                    List<BcParcialidades> parcialidades = bpRepository.findByNumeroCuenta(bcParcialidad.get().getNumeroCuenta());
                    primeraParcialidad = parcialidades.stream()
                            .filter(p -> p.getEstadoParcialidad() == 12).count() == 0;

                    if (primeraParcialidad) {
                        //actualiza cuenta en el sistema del agricultor. 
                        accsService.actualizarEstadoCuenta(bcParcialidad.get().getNumeroCuenta(), Estados.CUENTA_ABIERTA);
                        //actualiza cuenta en el sistem del beneficio de cafe
                        bccsService.actualizarEstadoCuenta(bcParcialidad.get().getNumeroCuenta(), Estados.CUENTA_ABIERTA);
                    }
                }                
                //cambiando estado en el sistema del beneficio de café. 
                actualizarEstadoParcialidad(idParcialidad, Estados.PAR_PENDIENTE_PESAR);
                //cambiando estado en el sistema agricultor
                apsService.actualizarEstadoParcialidad(idParcialidad, Estados.PAR_PENDIENTE_PESAR);
                
                RespuestaDto res = new RespuestaDto();
                res.setTitulo("Ingreso Autorizado");
                res.setContenido("Puede proceder a ingresar.");
                return res;
            }else{
                return null;
            }
            
    }
    
    public void actualizarEstadoParcialidad(int idParcialidad, int idEstado){
        BcParcialidades parcialidad = bpRepository.findByIdParcialidad(idParcialidad); 
        parcialidad.setEstadoParcialidad(idEstado);
        bpRepository.save(parcialidad);
    }
    
    /*public boolean esPrimeraParcialidad(String numeroCuenta){
        List<BcParcialidades> parcialidades = bpRepository.findByNumeroCuenta(numeroCuenta);
        
        return parcialidades.size() == 1; 
    }*/
    
    public List<ParcialidadProjection> getParcialidadPesar(){
        return bpRepository.getParcialidadesParaPesar();
    }
    
    public List<ParcialidadProjection> getParcialidadesEnRuta(){
        return bpRepository.getParcialidadesEnRuta();
    }
    
    
    public List<ParcialidadProjection> getParcialidadesCuenta(String noCuenta){
        return bpRepository.findByNumeroCuentaBoletaPeso(noCuenta);
    }
    
}
