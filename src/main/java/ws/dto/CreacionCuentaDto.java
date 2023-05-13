/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreacionCuentaDto {
    private BigDecimal peso;
    private Integer cantidad;
    private String agricultor;
    private String estadoNombre;
    private Integer estado;
    private Integer idCuentaCorriente;
    private List<VehiculosAsigDto> vehiculos;
   
    
}
