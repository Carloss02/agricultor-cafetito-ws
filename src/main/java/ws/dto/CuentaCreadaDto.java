/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.dto;

public class CuentaCreadaDto {
    
    private int idCuentaCorriente;
    private String numeroCuenta;
    private String mensaje;
    private int aprobado;
    private int correcion;
    private int rechazado;
    

    public CuentaCreadaDto() {
    }

    public CuentaCreadaDto(int idCuentaCorriente, String numeroCuenta, String mensaje, int aprobado, int correcion, int rechazado) {
        this.idCuentaCorriente = idCuentaCorriente;
        this.numeroCuenta = numeroCuenta;
        this.mensaje = mensaje;
        this.aprobado = aprobado;
        this.correcion = correcion;
        this.rechazado = rechazado;
    }

    public int getIdCuentaCorriente() {
        return idCuentaCorriente;
    }

    public void setIdCuentaCorriente(int idCuentaCorriente) {
        this.idCuentaCorriente = idCuentaCorriente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getAprobado() {
        return aprobado;
    }

    public void setAprobado(int aprobado) {
        this.aprobado = aprobado;
    }

    public int getCorrecion() {
        return correcion;
    }

    public void setCorrecion(int correcion) {
        this.correcion = correcion;
    }

    public int getRechazado() {
        return rechazado;
    }

    public void setRechazado(int rechazado) {
        this.rechazado = rechazado;
    }

    
    
    
}
