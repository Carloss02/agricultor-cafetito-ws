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
@Table(name="agr_parcialidades"
    ,catalog="db_agricultor"
)
public class AgrParcialidades  implements java.io.Serializable {


     private Integer idParcialidad;
     private Integer idCuentaCorriente;
     private Integer estadoParcialidad;
     private BigDecimal pesoParcialidad;
     private Date fechaParcialidadEnviada;
     private Date fechaParcialidadEntregada;
     private String licenciasTransportistas;
     private String placaVehiculo;
     private String usuarioCreacion;
     private Date fechaCreacion;
     private Integer idParcialidadBeneficio;

    public AgrParcialidades() {
    }

    public AgrParcialidades(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public AgrParcialidades(Integer estadoParcialidad) {
        this.estadoParcialidad = estadoParcialidad;
    }

    public AgrParcialidades(Integer idParcialidad, Integer idCuentaCorriente, Integer estadoParcialidad, BigDecimal pesoParcialidad, Date fechaParcialidadEnviada, Date fechaParcialidadEntregada, String licenciasTransportistas, String placaVehiculo, String usuarioCreacion, Date fechaCreacion, Integer idParcialidadBeneficio) {
        this.idParcialidad = idParcialidad;
        this.idCuentaCorriente = idCuentaCorriente;
        this.estadoParcialidad = estadoParcialidad;
        this.pesoParcialidad = pesoParcialidad;
        this.fechaParcialidadEnviada = fechaParcialidadEnviada;
        this.fechaParcialidadEntregada = fechaParcialidadEntregada;
        this.licenciasTransportistas = licenciasTransportistas;
        this.placaVehiculo = placaVehiculo;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.idParcialidadBeneficio = idParcialidadBeneficio;
    }

     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ID_PARCIALIDAD", unique=true, nullable=false)
    public Integer getIdParcialidad() {
        return this.idParcialidad;
    }
    
    public void setIdParcialidad(Integer idParcialidad) {
        this.idParcialidad = idParcialidad;
    }

    @Column(name="ID_CUENTA_CORRIENTE")
    public Integer getIdCuentaCorriente() {
        return this.idCuentaCorriente;
    }
    
    public void setIdCuentaCorriente(Integer idCuentaCorriente) {
        this.idCuentaCorriente = idCuentaCorriente;
    }

    @Column(name="ESTADO_PARCIALIDAD")
    public Integer getEstadoParcialidad() {
        return this.estadoParcialidad;
    }
    
    public void setEstadoParcialidad(Integer estadoParcialidad) {
        this.estadoParcialidad = estadoParcialidad;
    }

    
    @Column(name="PESO_PARCIALIDAD", precision=10)
    public BigDecimal getPesoParcialidad() {
        return this.pesoParcialidad;
    }
    
    public void setPesoParcialidad(BigDecimal pesoParcialidad) {
        this.pesoParcialidad = pesoParcialidad;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FECHA_PARCIALIDAD_ENVIADA", length=19)
    public Date getFechaParcialidadEnviada() {
        return this.fechaParcialidadEnviada;
    }
    
    public void setFechaParcialidadEnviada(Date fechaParcialidadEnviada) {
        this.fechaParcialidadEnviada = fechaParcialidadEnviada;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FECHA_PARCIALIDAD_ENTREGADA", length=19)
    public Date getFechaParcialidadEntregada() {
        return this.fechaParcialidadEntregada;
    }
    
    public void setFechaParcialidadEntregada(Date fechaParcialidadEntregada) {
        this.fechaParcialidadEntregada = fechaParcialidadEntregada;
    }

    
    @Column(name="LICENCIAS_TRANSPORTISTAS", length=100)
    public String getLicenciasTransportistas() {
        return this.licenciasTransportistas;
    }
    
    public void setLicenciasTransportistas(String licenciasTransportistas) {
        this.licenciasTransportistas = licenciasTransportistas;
    }

    
    @Column(name="PLACA_VEHICULO", length=10)
    public String getPlacaVehiculo() {
        return this.placaVehiculo;
    }
    
    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
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
    
    @Column(name="ID_PARCIALIDAD_BENEFICIO", nullable=false)
    public Integer getIdParcialidadBeneficio() {
        return idParcialidadBeneficio;
    }

    public void setIdParcialidadBeneficio(Integer idParcialidadBeneficio) {
        this.idParcialidadBeneficio = idParcialidadBeneficio;
    }

}