/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ws.agricultor.model.AgrCuentaCorriente;
import ws.agricultor.model.AgrTransportistas;
import ws.agricultor.repository.AgrCuentaCorrienteRepository;
import ws.agricultor.repository.AgrTransportistasRepository;
import ws.dto.RegistrarTransportistaDto;
import ws.dto.ValidarTransportistaDto;
import ws.dto.VehiculosAsigDto;
import ws.util.Estados;

@Service
public class AgrTransportistasService {
    @Autowired
    private AgrTransportistasRepository atRepository;
    @Autowired
    private BcTransportistasService bcTransportistasService;
    
    @Autowired
    private AgrBitacoraService bitacoraService;
    
    @Autowired
    private AgrCuentaCorrienteRepository accRepository;
    
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
    public AgrTransportistas editarTransportista(AgrTransportistas transportista, String username){
        AgrTransportistas exist = atRepository.findByIdLicencia(transportista.getIdLicencia());
        
        if(exist != null){
            AgrTransportistas t = atRepository.save(
                    AgrTransportistas.builder()
                            .idLicencia(exist.getIdLicencia())
                            .estadoTransportista(20)
                            .emailTransportista(transportista.getEmailTransportista())
                            .nombreTransportista(transportista.getNombreTransportista())
                            .telefonoTransportista(transportista.getTelefonoTransportista())
                            .tipoLicencia(transportista.getTipoLicencia())
                            .fechaCreacion(exist.getFechaCreacion())
                    .build()
            );
            bitacoraService.addRecordAgr("agr_trasnportistas", t.getIdLicencia(), 'U', t, username);
            return t;
        }else{
            return null;
        }
    }
    
    public List<AgrTransportistas> getTrasportistasDisponiblesAutorizados(Integer idCuenta){
        AgrCuentaCorriente cuenta = accRepository.findByIdCuentaCorriente(idCuenta);
        
        Type tipoListaVehiculos = new TypeToken<List<VehiculosAsigDto>>() {}.getType();
        List<VehiculosAsigDto> autorizados = new Gson().fromJson(cuenta.getVehiculosTransportistasAsignados(), tipoListaVehiculos);
        List<String> allLicenciasAutorizadas = autorizados.stream()
        .flatMap(v -> v.getLicencias().stream())
        .collect(Collectors.toList());
        
        List<AgrTransportistas> disponibles = atRepository.findByEstadoTransportista(24);
        
        List<AgrTransportistas> filtro = disponibles.stream()
                .filter(d -> allLicenciasAutorizadas.stream().anyMatch(a -> a.equals(d.getIdLicencia())))
                .collect(Collectors.toList());
        
        return filtro;
    }
}
