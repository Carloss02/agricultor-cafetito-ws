package ws.cafetito.model;


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Builder;

@Builder
@Entity
@Table(name="bc_cuenta_corriente", schema="db_cafetito")
public class BcCuentaCorriente  implements java.io.Serializable {

     private Integer estadoCuenta;
     private String numeroCuenta;
     private BigDecimal pesoTotal;
     private Integer cantidadParcialidades;
     private String usuarioCreacion;
     private Date fechaCreacion;
     private String vehiculosTransportistasAsignados;
     private String tipoMedida;

    public BcCuentaCorriente() {
    }

	
    public BcCuentaCorriente(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BcCuentaCorriente(Integer estadoCuenta, String numeroCuenta, BigDecimal pesoTotal, Integer cantidadParcialidades, String usuarioCreacion, Date fechaCreacion, String vehiculosTransportistasAsignados, String tipoMedida) {
        this.estadoCuenta = estadoCuenta;
        this.numeroCuenta = numeroCuenta;
        this.pesoTotal = pesoTotal;
        this.cantidadParcialidades = cantidadParcialidades;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.vehiculosTransportistasAsignados = vehiculosTransportistasAsignados;
        this.tipoMedida = tipoMedida;
    }
    
   
    @Column(name="ESTADO_CUENTA")
    public Integer getEstadoCuenta() {
        return this.estadoCuenta;
    }
    
    public void setEstadoCuenta(Integer estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    @Id
    @Column(name="NUMERO_CUENTA", length=20)
    public String getNumeroCuenta() {
        return this.numeroCuenta;
    }
    
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    
    @Column(name="PESO_TOTAL", precision=10)
    public BigDecimal getPesoTotal() {
        return this.pesoTotal;
    }
    
    public void setPesoTotal(BigDecimal pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    
    @Column(name="CANTIDAD_PARCIALIDADES")
    public Integer getCantidadParcialidades() {
        return this.cantidadParcialidades;
    }
    
    public void setCantidadParcialidades(Integer cantidadParcialidades) {
        this.cantidadParcialidades = cantidadParcialidades;
    }

    
    @Column(name="USUARIO_CREACION", length=50)
    public String getUsuarioCreacion() {
        return this.usuarioCreacion;
    }
    
    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FECHA_CREACION", nullable=false, length=19)
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    
    @Column(name="VEHICULOS_TRANSPORTISTAS_ASIGNADOS", length=300)
    public String getVehiculosTransportistasAsignados() {
        return this.vehiculosTransportistasAsignados;
    }
    
    public void setVehiculosTransportistasAsignados(String vehiculosTransportistasAsignados) {
        this.vehiculosTransportistasAsignados = vehiculosTransportistasAsignados;
    }

    @Column(name="TIPO_MEDIDA", length=2)
    public String getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(String tipoMedida) {
        this.tipoMedida = tipoMedida;
    }

    
}