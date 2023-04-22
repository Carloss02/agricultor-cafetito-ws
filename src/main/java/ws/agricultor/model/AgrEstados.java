package ws.agricultor.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Builder;

@Builder
@Entity
@Table(name="agr_estados"
    ,catalog="db_agricultor"
)
public class AgrEstados  implements java.io.Serializable {


     private Integer idEstado;
     private String nombreEstado;
     private String descripcion;
     private Character estado;
     private String usuarioCreacion;
     private Date fechaCreacion;

    public AgrEstados() {
    }

    public AgrEstados(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public AgrEstados(Integer idEstado, String nombreEstado, String descripcion, Character estado, String usuarioCreacion, Date fechaCreacion) {
        this.idEstado = idEstado;
        this.nombreEstado = nombreEstado;
        this.descripcion = descripcion;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
    }

     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ID_ESTADO", unique=true, nullable=false)
    public Integer getIdEstado() {
        return this.idEstado;
    }
    
    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    
    @Column(name="NOMBRE_ESTADO", length=50)
    public String getNombreEstado() {
        return this.nombreEstado;
    }
    
    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    
    @Column(name="DESCRIPCION", length=40)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="ESTADO", length=1)
    public Character getEstado() {
        return this.estado;
    }
    
    public void setEstado(Character estado) {
        this.estado = estado;
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

}