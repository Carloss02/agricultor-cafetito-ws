/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ws.agricultor.model.AgrTransportistas;
import ws.agricultor.model.AgrVehiculos;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehiculosAsigDto {
    private String placa;
    private List<String> licencias;
    
    //private BcVehiculos detalleVehiculo;
    //private List<BcTransportistas> transportistas;
    private AgrVehiculos detalleVehiculo;
    private List<AgrTransportistas> transportistas;
    
}
