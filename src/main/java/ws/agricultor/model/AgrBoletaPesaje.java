package ws.agricultor.model;


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
import lombok.Builder;

@Builder
@Entity
@Table(name="agr_boleta_pesaje"
    ,catalog="db_agricultor"
)
public class AgrBoletaPesaje  implements java.io.Serializable {


     private Integer idBoleta;
     private Integer idParcialidad;
     private BigDecimal resultadoPesaje;
     private Date fechaHoraIngreso;
     private Date fechaHoraSalida;
     private String usuarioCreacion;
     private Date fechaCreacion;

    public AgrBoletaPesaje() {
    }

    public AgrBoletaPesaje(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public AgrBoletaPesaje(Integer idBoleta, Integer idParcialidad, BigDecimal resultadoPesaje, Date fechaHoraIngreso, Date fechaHoraSalida, String usuarioCreacion, Date fechaCreacion) {
        this.idBoleta = idBoleta;
        this.idParcialidad = idParcialidad;
        this.resultadoPesaje = resultadoPesaje;
        this.fechaHoraIngreso = fechaHoraIngreso;
        this.fechaHoraSalida = fechaHoraSalida;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
    }

     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ID_BOLETA", unique=true, nullable=false)
    public Integer getIdBoleta() {
        return this.idBoleta;
    }
    
    public void setIdBoleta(Integer idBoleta) {
        this.idBoleta = idBoleta;
    }

    @Column(name="ID_PARCIALIDAD")
    public Integer getIdParcialidad() {
        return this.idParcialidad;
    }
    
    public void setIdParcialidad(Integer idParcialidad) {
        this.idParcialidad = idParcialidad;
    }

    
    @Column(name="RESULTADO_PESAJE", precision=10)
    public BigDecimal getResultadoPesaje() {
        return this.resultadoPesaje;
    }
    
    public void setResultadoPesaje(BigDecimal resultadoPesaje) {
        this.resultadoPesaje = resultadoPesaje;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FECHA_HORA_INGRESO", length=19)
    public Date getFechaHoraIngreso() {
        return this.fechaHoraIngreso;
    }
    
    public void setFechaHoraIngreso(Date fechaHoraIngreso) {
        this.fechaHoraIngreso = fechaHoraIngreso;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FECHA_HORA_SALIDA", length=19)
    public Date getFechaHoraSalida() {
        return this.fechaHoraSalida;
    }
    
    public void setFechaHoraSalida(Date fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
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


