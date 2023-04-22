/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import com.google.gson.Gson;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.agricultor.model.AgrBitacora;
import ws.agricultor.repository.AgrBitacoraRepository;

@Service
public class AgrBitacoraService {
    @Autowired
    private AgrBitacoraRepository abRepository;
    
    public void addRecordAgr(String tableName, String id, char typeOperation, Object data, String usuario){
        
        abRepository.save(
                AgrBitacora.builder()
                .nombreTabla(tableName)
                .idRegistro(id)
                .tipoOperacion(typeOperation)
                .datos(new Gson().toJson(data))
                .fechaCreacion(new Date())
                .usuarioCreacion(usuario)
                .build()
        );
    }
}
