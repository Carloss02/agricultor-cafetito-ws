/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dto;

import java.math.BigDecimal;
import java.util.Date;


/**
 *
 * @author carlos
 */
public class MensajeDto {
    private int idMensaje; 
    private String numeroCuenta;
    private String placaVehiculo;
    private int idParcialidad;
    private int parcialidades;
    private BigDecimal totalPesaje;
    private String estadoMensaje;
    private int codigoEstado;
    private String mensaje;
    private int aprobado;
    private int correccion;
    private String usuarioCreacion;
    private Date fechaCreacion;
    private int idCuentaCorriente;

    public MensajeDto() {
    }

    public MensajeDto(int idMensaje, String numeroCuenta, String placaVehiculo, int idParcialidad, int parcialidades, BigDecimal totalPesaje, String estadoMensaje, int codigoEstado, String mensaje, int aprobado, int correccion, String usuarioCreacion, Date fechaCreacion) {
        this.idMensaje = idMensaje;
        this.numeroCuenta = numeroCuenta;
        this.placaVehiculo = placaVehiculo;
        this.idParcialidad = idParcialidad;
        this.parcialidades = parcialidades;
        this.totalPesaje = totalPesaje;
        this.estadoMensaje = estadoMensaje;
        this.codigoEstado = codigoEstado;
        this.mensaje = mensaje;
        this.aprobado = aprobado;
        this.correccion = correccion;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public int getIdParcialidad() {
        return idParcialidad;
    }

    public void setIdParcialidad(int idParcialidad) {
        this.idParcialidad = idParcialidad;
    }

    public int getParcialidades() {
        return parcialidades;
    }

    public void setParcialidades(int parcialidades) {
        this.parcialidades = parcialidades;
    }

    public BigDecimal getTotalPesaje() {
        return totalPesaje;
    }

    public void setTotalPesaje(BigDecimal totalPesaje) {
        this.totalPesaje = totalPesaje;
    }

    public String getEstadoMensaje() {
        return estadoMensaje;
    }

    public void setEstadoMensaje(String estadoMensaje) {
        this.estadoMensaje = estadoMensaje;
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

    public int getCorreccion() {
        return correccion;
    }

    public void setCorreccion(int correccion) {
        this.correccion = correccion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(int codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public int getIdCuentaCorriente() {
        return idCuentaCorriente;
    }

    public void setIdCuentaCorriente(int idCuentaCorriente) {
        this.idCuentaCorriente = idCuentaCorriente;
    }
    
    
    
}
