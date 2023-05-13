/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.agricultor.model.AgrTransportistas;
import ws.agricultor.repository.AgrTransportistasRepository;
import ws.cafetito.model.BcTransportistas;
import ws.cafetito.repository.BcEstadosRepository;
import ws.cafetito.repository.BcTransportistasRepository;
import ws.dto.RegistrarTransportistaDto;
import ws.dto.TransportistasAutorizadosDto;
import ws.util.Estados;

@Service
public class BcTransportistasService {
    @Autowired
    private BcTransportistasRepository btRepository;
    
    @Autowired
    private BcEstadosRepository beRepository;
    
    @Autowired
    private AgrTransportistasRepository atRepository;
    
    //agregar servicios 
    
    public List<TransportistasAutorizadosDto> getTransporitistasAutorizados(){
        //obtiene un objeto estado, es decir el estado con condigo 22. 
        //BcEstados autorizado = beRepository.findByIdEstado(22);
        
        //busca todos los transportistas con estado Transportista Autorizado
        // y lo asigna a la variable transportistas. 
        List<BcTransportistas> transportistas = btRepository.findByEstadoTransportista(Estados.TRANSPORTISTA_AUTORIZADO);
        
        //Creamos una un objeto DTO de tipo lista. 
        List<TransportistasAutorizadosDto> transportistasDto = new ArrayList<>();
        
        //con un foreach, recorremos la lista de transportistas con estado autorizado
        transportistas.forEach((transportista) -> {
            //por cada transportista de la lista, mapeamos los datos a un objeto DTO. 
            //y lo agregamos a la lista transportistasDto
            transportistasDto.add(transportista.toAutorizadosDto());
        });
        
        return transportistasDto; 
    }
    
    public BcTransportistas registrarTransportista(RegistrarTransportistaDto tDto, String username){
        BcTransportistas transportista = new BcTransportistas(
                tDto.getLicencia(),
                Estados.TRANSPORTISTA_ACTIVO,
                tDto.getTipoLicencia(), 
                tDto.getNombre(), 
                tDto.getTelefono(), 
                tDto.getEmail(), 
                username, 
                new Date()
        );
        return btRepository.save(transportista);
        
    }
    
    public String autorizarTrasportista(String licencia){
        BcTransportistas transportista = btRepository.findByIdLicencia(licencia);
        transportista.setEstadoTransportista(Estados.TRANSPORTISTA_AUTORIZADO);
        btRepository.save(transportista);
        return "Vehículo Autorizado";
    }
    
    public String rechazarTransportista(String licencia){
        BcTransportistas transportista = btRepository.findByIdLicencia(licencia);
        transportista.setEstadoTransportista(Estados.TRANSPORTISTA_RECHAZADO);
        btRepository.save(transportista);
        return "Vehículo Rechazado";
    }
    
    public List<AgrTransportistas> getTrasportistasCreados(){
        return atRepository.findByEstadoTransportista(20);
    }
}
