/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.service;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ws.agricultor.model.AgrCuentaCorriente;
import ws.agricultor.repository.AgrCuentaCorrienteRepository;
import ws.dto.CreacionCuentaDto;
import ws.projection.CuentaProjection;
import ws.projection.ReporteProjection;
import ws.util.Estados;

@Service
@Slf4j
@Transactional(value = "mysqlTransactionManager")
public class AgrCuentaCorrienteService {
    @Autowired
    private AgrCuentaCorrienteRepository accRepository;
    
    @Autowired
    private BcCuentaCorrienteService bcCuentaService;
    
    public Boolean agregarCuenta(CreacionCuentaDto dto, String userName){
       accRepository.save(
                AgrCuentaCorriente.builder()
                        .pesoTotal(dto.getPeso())
                        .cantidadParcialidades(dto.getCantidad())
                        .estadoCuenta(Estados.CUENTA_CORRIENTE)
                        .fechaCreacion(new Date())
                        .usuarioCreacion(userName)
                        .tipoMedida(dto.getTipoMedida())
                        .vehiculosTransportistasAsignados(new Gson().toJson(dto.getVehiculos()))
                .build()
        );
        
        return true;
    }
    
    public void actualizarEstadoCuenta(String numeroCuenta, int idEstado){
        AgrCuentaCorriente cuenta = accRepository.findByNumeroCuenta(numeroCuenta);
        cuenta.setEstadoCuenta(idEstado);
        accRepository.save(cuenta);
    }
    
    public List<CreacionCuentaDto> getCuentasByEstado(){
        List<Integer> estados = new ArrayList();
        estados.add(1);
        estados.add(8);
        estados.add(9);
        List<CuentaProjection> cuentas = accRepository.getCuentasEstados(estados);
        
        List<CreacionCuentaDto> lista = cuentas.stream().map(k ->
                bcCuentaService.detalleCuenta(k.getIdCuentaCorriente())
        ).collect(Collectors.toList());
        
        return lista;
    }
    
    public List<AgrCuentaCorriente> getCuentasEstado(Integer estado){ 
        
        return accRepository.findByEstadoCuenta(estado);
    }
    
    public List<CuentaProjection> getCuentasGeneral(){
        List<Integer> estado = new ArrayList();
        estado.add(2);
        estado.add(3);
        estado.add(4);
        estado.add(5);
        estado.add(6);
        estado.add(7);
        return accRepository.getCuentasEstados(estado);
    }
    
    public void actualizarTolerancia(String noCuenta, Integer tolerancia){
        AgrCuentaCorriente cuenta = accRepository.findByNumeroCuenta(noCuenta);
        cuenta.setTolerancia(tolerancia);
        accRepository.save(cuenta);
    }
    
    public List<ReporteProjection> getReporteAgricultores(Date fecha1, Date fecha2){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaInicio = dateFormat.format(fecha1);
        String fechaFin = dateFormat.format(fecha2);
        
        System.out.println("Fecha inicio " + fechaInicio);
        System.out.println("Fecha Fin " + fechaFin);
        
        return accRepository.getReporteAgricultores(fechaInicio, fechaFin);
    }
}
