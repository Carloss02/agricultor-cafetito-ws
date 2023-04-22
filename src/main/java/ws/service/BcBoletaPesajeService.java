/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.agricultor.model.AgrBoletaPesaje;
import ws.agricultor.model.AgrParcialidades;
import ws.agricultor.repository.AgrBoletaPesajeRepository;
import ws.agricultor.repository.AgrCuentaCorrienteRepository;
import ws.agricultor.repository.AgrParcialidadesRepository;
import ws.cafetito.model.BcBoletaPesaje;
import ws.cafetito.model.BcParcialidades;
import ws.cafetito.repository.BcBoletaPesajeRepository;
import ws.cafetito.repository.BcParcialidadesRepository;
import ws.cafetito.repository.BcVehiculosRepository;
import ws.util.Estados;

@Service
public class BcBoletaPesajeService {
    @Autowired
    private BcBoletaPesajeRepository bcBoletaPesajeRepository;

    @Autowired
    private BcParcialidadesRepository bcParcialidadesRepository;
    
    @Autowired
    private AgrBoletaPesajeRepository agrBoletaPesajeRepository;
    
    @Autowired
    private AgrParcialidadesRepository agrParcialidadesRepository;
    
    @Autowired
    private AgrCuentaCorrienteRepository agrCuentaCorrienteRepository;
    
    @Autowired
    private BcVehiculosRepository bvRepository;
    
    @Autowired
    private BcBitacoraService bcBitacoraService;
    
    @Autowired
    private AgrBitacoraService argBitacoraService;

    //agregar servicios
    public Map<String, Object> crearBoleta(BcParcialidades parcialidad, BigDecimal pesaje, String userName) {
        System.out.println("PASA POR CREAR BOLETA");
        parcialidad.setEstadoParcialidad(Estados.PAR_ENTREGADA);
        parcialidad.setFechaParcialidadEntregada(new Date());
        
        BcParcialidades parcialidadModificada = bcParcialidadesRepository.save(parcialidad);
        bcBitacoraService.addRecordBc("bc_parcialidades", parcialidadModificada.getIdParcialidad().toString(), 'U', parcialidadModificada, userName);
        
        BigDecimal pesajeCafe = pesaje.subtract(bvRepository.findByPlacaVehiculo(parcialidad.getPlacaVehiculo()).getPesoVehiculo());
        
        BcBoletaPesaje boletaBeneficio = bcBoletaPesajeRepository.save(BcBoletaPesaje.builder()
                .idParcialidad(parcialidadModificada.getIdParcialidad())
                .fechaCreacion(new Date())
                .resultadoPesaje(pesajeCafe)
                .fechaHoraIngreso(new Date())
                .fechaHoraSalida(new Date())
                .build());
        bcBitacoraService.addRecordBc("bc_boleta_pesaje", boletaBeneficio.getIdBoleta().toString(), 'I', boletaBeneficio, userName);
        
        AgrParcialidades parcialidadAgricultor = agrParcialidadesRepository.findByIdParcialidadBeneficio(parcialidad.getIdParcialidad());
        
        if(parcialidadAgricultor == null){
            System.out.println("Truena");
            
        }
        
        AgrBoletaPesaje boletaPesajeAgricultor = agrBoletaPesajeRepository.save(AgrBoletaPesaje.builder()
                .idParcialidad(parcialidadAgricultor.getIdParcialidad())
                .fechaCreacion(new Date())
                .resultadoPesaje(pesajeCafe)
                .fechaHoraIngreso(new Date())
                .fechaHoraSalida(new Date())
                .build());
        argBitacoraService.addRecordAgr("agr_boleta_pesaje", boletaPesajeAgricultor.getIdBoleta().toString(), 'I', boletaPesajeAgricultor, userName);
        Map<String, Object> boletas = new HashMap();
        boletas.put("boletaBeneficio", boletaBeneficio);
        boletas.put("boletaAgricultor", boletaPesajeAgricultor);
        
        return boletas;
    }

}
