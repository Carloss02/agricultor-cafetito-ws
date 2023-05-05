/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ws.agricultor.model.AgrTransportistas;
import ws.agricultor.repository.AgrTransportistasRepository;
import ws.dto.RegistrarTransportistaDto;
import ws.dto.ValidarTransportistaDto;
import ws.util.Estados;

@Service
public class AgrTransportistasService {
    @Autowired
    private AgrTransportistasRepository atRepository;
    @Autowired
    private BcTransportistasService bcTransportistasService;
    
    @Autowired
    private AgrBitacoraService bitacoraService;
    
    //agregar servicios
    
    public ValidarTransportistaDto getTransportistaByLicencia(String licencia){
        
        AgrTransportistas transportista = atRepository.findByIdLicencia(licencia);
        
        ValidarTransportistaDto transportistaDto = new ValidarTransportistaDto(
                transportista.getIdLicencia(),
                transportista.getTipoLicencia(),
                transportista.getNombreTransportista(),
                transportista.getTelefonoTransportista(),
                transportista.getEmailTransportista(),
                transportista.getEstadoTransportista()
        );
        
        return transportistaDto;
    }
    
    @Transactional(value = "mysqlTransactionManager")
    public RegistrarTransportistaDto registrarTransportista(RegistrarTransportistaDto tDto, String username){
        
        AgrTransportistas exist = atRepository.findByIdLicencia(tDto.getLicencia());
        
        if(exist == null){
        //registrando transportista en el sistema del Agricultor
        //AgrEstados estado = aerRepository.findByIdEstado(20); //estado transportista activo
        AgrTransportistas transportista = new AgrTransportistas(
                tDto.getLicencia(),
                Estados.TRANSPORTISTA_ACTIVO,
                tDto.getTipoLicencia(), 
                tDto.getNombre(), 
                tDto.getTelefono(), 
                tDto.getEmail(), 
                username, 
                new Date()
        );
        
        AgrTransportistas t = atRepository.save(transportista);
        bitacoraService.addRecordAgr("agr_trasnportistas", t.getIdLicencia(), 'I', t, username);
        //agregar logica para registrar al transportista en el sistema del beneficio de café
        bcTransportistasService.registrarTransportista(tDto, username);
        
        return tDto;
        }else{
            return null;
        }
    }
    
    @Transactional(value = "mysqlTransactionManager")
    public void cambiarEstadoTransportista(String licencias, int idEstado){
        Arrays.stream(licencias.split(","))
                .forEach(licencia -> {
                    AgrTransportistas transportista = atRepository
                            .findByIdLicencia(licencia.trim());
                    
                    transportista.setEstadoTransportista(idEstado);
                    atRepository.save(transportista);
                });   
    }
    
    public List<AgrTransportistas> getAll(){
        return atRepository.findAll();
    }
    
    @Transactional(value = "mysqlTransactionManager")
    public AgrTransportistas editarVehiculo(AgrTransportistas transportista, String username){
        AgrTransportistas exist = atRepository.findByIdLicencia(transportista.getIdLicencia());
        
        if(exist != null){
            AgrTransportistas t = atRepository.save(
                    AgrTransportistas.builder()
                            .estadoTransportista(20)
                            .emailTransportista(transportista.getEmailTransportista())
                            .nombreTransportista(transportista.getNombreTransportista())
                            .telefonoTransportista(transportista.getTelefonoTransportista())
                            .tipoLicencia(transportista.getTipoLicencia())
                    .build()
            );
            bitacoraService.addRecordAgr("agr_trasnportistas", t.getIdLicencia(), 'U', t, username);
            return t;
        }else{
            return null;
        }
    }
}
