package ws.cafetito.model;


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import ws.dto.MensajeDto;

@Entity
@Table(name="bc_mensajes" , schema="db_cafetito")
public class BcMensajes  implements java.io.Serializable {


     private Integer idMensaje;
     private Integer estadoMensaje;
     private String numeroCuenta;
     private String placaVehiculo;
     private Integer idParcialidad;
     private Integer parcialidades;
     private BigDecimal totalPesaje;
     private String mensaje;
     private Integer aprobado;
     private Integer correccion;
     private String usuarioCreacion;
     private Date fechaCreacion;

    public BcMensajes() {
    }

	
    public BcMensajes(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public BcMensajes(Integer estadoMensaje, String numeroCuenta, String placaVehiculo, Integer idParcialidad, Integer parcialidades, BigDecimal totalPesaje, String mensaje, Integer aprobado, Integer correccion, String usuarioCreacion, Date fechaCreacion) {
       this.estadoMensaje = estadoMensaje;
       this.numeroCuenta = numeroCuenta;
       this.placaVehiculo = placaVehiculo;
       this.idParcialidad = idParcialidad;
       this.parcialidades = parcialidades;
       this.totalPesaje = totalPesaje;
       this.mensaje = mensaje;
       this.aprobado = aprobado;
       this.correccion = correccion;
       this.usuarioCreacion = usuarioCreacion;
       this.fechaCreacion = fechaCreacion;
    }
   
    @Id 
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="ID_MENSAJE", unique=true, nullable=false)
    public Integer getIdMensaje() {
        return this.idMensaje;
    }
    
    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    @Column(name="ESTADO_MENSAJE")
    public Integer getEstadoMensaje() {
        return this.estadoMensaje;
    }
    
    public void setEstadoMensaje(Integer estadoMensaje) {
        this.estadoMensaje = estadoMensaje;
    }

    
    @Column(name="NUMERO_CUENTA", length=20)
    public String getNumeroCuenta() {
        return this.numeroCuenta;
    }
    
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    
    @Column(name="PLACA_VEHICULO", length=10)
    public String getPlacaVehiculo() {
        return this.placaVehiculo;
    }
    
    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    
    @Column(name="ID_PARCIALIDAD")
    public Integer getIdParcialidad() {
        return this.idParcialidad;
    }
    
    public void setIdParcialidad(Integer idParcialidad) {
        this.idParcialidad = idParcialidad;
    }

    
    @Column(name="PARCIALIDADES")
    public Integer getParcialidades() {
        return this.parcialidades;
    }
    
    public void setParcialidades(Integer parcialidades) {
        this.parcialidades = parcialidades;
    }

    
    @Column(name="TOTAL_PESAJE", precision=10)
    public BigDecimal getTotalPesaje() {
        return this.totalPesaje;
    }
    
    public void setTotalPesaje(BigDecimal totalPesaje) {
        this.totalPesaje = totalPesaje;
    }

    
    @Column(name="MENSAJE", length=250)
    public String getMensaje() {
        return this.mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    
    @Column(name="APROBADO")
    public Integer getAprobado() {
        return this.aprobado;
    }
    
    public void setAprobado(Integer aprobado) {
        this.aprobado = aprobado;
    }

    
    @Column(name="CORRECCION")
    public Integer getCorreccion() {
        return this.correccion;
    }
    
    public void setCorreccion(Integer correccion) {
        this.correccion = correccion;
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

    // metodo que permite mapear los datos que necesito devolver como respuesta. 
    public MensajeDto toMensajeDto() {
        MensajeDto mensajeDto = new MensajeDto(
                this.getIdMensaje(),
                this.getNumeroCuenta(),
                this.getPlacaVehiculo(),
                this.getIdParcialidad(),
                this.getParcialidades(),
                this.getTotalPesaje(),
                this.getEstadoMensaje(),
                this.getMensaje(),
                this.getAprobado(),
                this.getCorreccion(),
                this.getUsuarioCreacion(),
                this.getFechaCreacion()
        );
        return mensajeDto;
    }


}


