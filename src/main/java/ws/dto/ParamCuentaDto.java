/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.dto;
public class ParamCuentaDto {
    
    private Integer idVenta;
    private int operacion;
    private String mensaje;

    public ParamCuentaDto() {
    }

    public ParamCuentaDto(Integer idVenta, int operacion) {
        this.idVenta = idVenta;
        this.operacion = operacion;
    }

    public ParamCuentaDto(Integer idVenta, int operacion, String mensaje) {
        this.idVenta = idVenta;
        this.operacion = operacion;
        this.mensaje = mensaje;
    }
 
    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public int getOperacion() {
        return operacion;
    }

    public void setOperación(int operación) {
        this.operacion = operación;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
}
