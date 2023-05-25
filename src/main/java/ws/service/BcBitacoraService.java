package ws.service;

import com.google.gson.Gson;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ws.cafetito.model.BcBitacora;
import ws.cafetito.repository.BcBitacoraRepository;


@Service
public class BcBitacoraService {
    @Autowired
    private BcBitacoraRepository bcBitacoraRepository;
    
    @Transactional(value = "postgresqlTransactionManager")
    public void addRecordBc(String tableName, String id, char typeOperation, Object data, String usuario){
        
        bcBitacoraRepository.save(
                BcBitacora.builder()
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
