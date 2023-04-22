/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import java.util.Arrays;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    
    
    public RegistrarTransportistaDto registrarTransportista(RegistrarTransportistaDto tDto, String username){
        

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
        
        atRepository.save(transportista);
        
        //agregar logica para registrar al transportista en el sistema del beneficio de café
        bcTransportistasService.registrarTransportista(tDto, username);
        
        return tDto; 
    }
    
    public void cambiarEstadoTransportista(String licencias, int idEstado){
        Arrays.stream(licencias.split(","))
                .forEach(licencia -> {
                    AgrTransportistas transportista = atRepository
                            .findByIdLicencia(licencia.trim());
                    
                    transportista.setEstadoTransportista(idEstado);
                    atRepository.save(transportista);
                });   
    }
    
}
