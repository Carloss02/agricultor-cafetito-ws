/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ws.agricultor.model.AgrTransportistas;
import ws.agricultor.repository.AgrTransportistasRepository;
import ws.cafetito.model.BcParcialidades;
import ws.cafetito.model.BcTransportistas;
import ws.cafetito.repository.BcEstadosRepository;
import ws.cafetito.repository.BcParcialidadesRepository;
import ws.cafetito.repository.BcTransportistasRepository;
import ws.dto.RegistrarTransportistaDto;
import ws.dto.RespuestaDto;
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
    
    @Autowired
    private BcParcialidadesRepository bpRepository;
    
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
    
    @Transactional(value = "postgresqlTransactionManager")
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
    @Transactional(value = "postgresqlTransactionManager")
    public RespuestaDto autorizarTrasportista(String licencia, String username){
        AgrTransportistas trans = atRepository.findById(licencia).get();
        Optional<BcTransportistas> transportista = btRepository.findById(licencia);
        RespuestaDto res = new RespuestaDto();
        if(!transportista.isPresent()){
            BcTransportistas t = btRepository.save(
                    BcTransportistas.builder()
                            .emailTransportista(trans.getEmailTransportista())
                            .estadoTransportista(Estados.TRANSPORTISTA_AUTORIZADO)
                            .fechaCreacion(new Date())
                            .idLicencia(licencia)
                            .nombreTransportista(trans.getNombreTransportista())
                            .telefonoTransportista(trans.getTelefonoTransportista())
                            .tipoLicencia(trans.getTipoLicencia())
                            .usuarioCreacion(username)
                    .build()
            );
        }else{
            transportista.get().setEstadoTransportista(Estados.TRANSPORTISTA_AUTORIZADO);
            btRepository.save(transportista.get());
        }
        res.setTitulo("Vehículo Autorizado");
        res.setContenido("Se ha autorizado al transportista correctamente ");
        return res;
    }
    
    @Transactional(value = "postgresqlTransactionManager")
    public RespuestaDto rechazarTransportista(String licencia, String username){
        AgrTransportistas trans = atRepository.findById(licencia).get();
        Optional<BcTransportistas> transportista = btRepository.findById(licencia);
        RespuestaDto res = new RespuestaDto();
        if(!transportista.isPresent()){
            BcTransportistas t = btRepository.save(
                    BcTransportistas.builder()
                            .emailTransportista(trans.getEmailTransportista())
                            .estadoTransportista(Estados.TRANSPORTISTA_RECHAZADO)
                            .fechaCreacion(new Date())
                            .idLicencia(licencia)
                            .nombreTransportista(trans.getNombreTransportista())
                            .telefonoTransportista(trans.getTelefonoTransportista())
                            .tipoLicencia(trans.getTipoLicencia())
                            .usuarioCreacion(username)
                    .build()
            );
        }else{
            transportista.get().setEstadoTransportista(Estados.TRANSPORTISTA_RECHAZADO);
            btRepository.save(transportista.get());
        }
        
        res.setTitulo("Vehículo Rechazado");
        res.setContenido("Se ha rechazado el vehículo correctamente ");
        return res;
    }
    
    public List<AgrTransportistas> getTrasportistasCreados(){
        List<AgrTransportistas> todos = atRepository.findAll();
        List<BcTransportistas> agregados = btRepository.findAll();
        
        List<AgrTransportistas> sinAgregar = todos.stream()
                .filter(t -> agregados.stream().noneMatch(a -> a.getIdLicencia().equals(t.getIdLicencia())))
                .collect(Collectors.toList());
        return sinAgregar;
    }
    
    public TransportistasAutorizadosDto validarPermisoTransportista(String licencia){
        BcTransportistas transportista = btRepository.findByIdLicencia(licencia);
        //Optional<BcParcialidades> bcParcialidad = bpRepository.findById(parcialidad);
        TransportistasAutorizadosDto dto = new TransportistasAutorizadosDto();
        //System.out.println("PARCIALIDAD" + bcParcialidad.get().getIdParcialidad());
        //System.out.println("ESTADO TRANSPORTISTA" + transportista.getEstadoTransportista());
        //if(bcParcialidad.isPresent()){
            
            if(transportista.getEstadoTransportista().equals(Estados.TRANSPORTISTA_AUTORIZADO)){
                dto.setEmail(transportista.getEmailTransportista());
                dto.setLicencia(transportista.getIdLicencia());
                dto.setNombre(transportista.getNombreTransportista());
                dto.setTelefono(transportista.getTelefonoTransportista());
                dto.setTipoLicencia(transportista.getTipoLicencia());
                return dto;
            }else{
            return null;
        }
    }
}
