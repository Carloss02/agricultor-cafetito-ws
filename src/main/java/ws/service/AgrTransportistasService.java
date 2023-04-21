/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.agricultor.model.AgrTransportistas;
import ws.agricultor.repository.AgrEstadosRepository;
import ws.agricultor.repository.AgrTransportistasRepository;
import ws.dto.RegistrarTransportistaDto;
import ws.dto.ValidarTransportistaDto;
import ws.util.Estados;

@Service
public class AgrTransportistasService {
    @Autowired
    private AgrTransportistasRepository atRepository;
    @Autowired
    private AgrEstadosRepository aerRepository;
    
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
    
    
    public RegistrarTransportistaDto registrarTransportista(RegistrarTransportistaDto tDto){
        
        //agregar logica para obtener el usuario quien registra al transportista
        
        
        //registrando transportista en el sistema del Agricultor
        //AgrEstados estado = aerRepository.findByIdEstado(20); //estado transportista activo
        AgrTransportistas transportista = new AgrTransportistas(
                tDto.getLicencia(),
                Estados.TRANSPORTISTA_ACTIVO,
                tDto.getTipoLicencia(), 
                tDto.getNombre(), 
                tDto.getTelefono(), 
                tDto.getEmail(), 
                "User3", 
                new Date()
        );
        
        atRepository.save(transportista);
        
        //agregar logica para registrar al transportista en el sistema del beneficio de café
        
        return tDto; 
    }
    
    
}
