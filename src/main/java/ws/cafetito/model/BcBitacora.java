package ws.cafetito.model;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="bc_bitacora", schema="db_cafetito")
public class BcBitacora  implements java.io.Serializable {


     private Integer idBitacora;
     private String nombreTabla;
     private String idRegistro;
     private Character tipoOperacion;
     private String usuarioCreacion;
     private Date fechaCreacion;
     private String datos;

    public BcBitacora() {
    }

	
    public BcBitacora(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public BcBitacora(String nombreTabla, String idRegistro, Character tipoOperacion, String usuarioCreacion, Date fechaCreacion, String datos) {
       this.nombreTabla = nombreTabla;
       this.idRegistro = idRegistro;
       this.tipoOperacion = tipoOperacion;
       this.usuarioCreacion = usuarioCreacion;
       this.fechaCreacion = fechaCreacion;
       this.datos = datos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ID_BITACORA", unique=true, nullable=false)
    public Integer getIdBitacora() {
        return this.idBitacora;
    }
    
    public void setIdBitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    
    @Column(name="NOMBRE_TABLA", length=30)
    public String getNombreTabla() {
        return this.nombreTabla;
    }
    
    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    
    @Column(name="ID_REGISTRO", length=30)
    public String getIdRegistro() {
        return this.idRegistro;
    }
    
    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    
    @Column(name="TIPO_OPERACION", length=1)
    public Character getTipoOperacion() {
        return this.tipoOperacion;
    }
    
    public void setTipoOperacion(Character tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
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

    
    @Column(name="DATOS", length=200)
    public String getDatos() {
        return this.datos;
    }
    
    public void setDatos(String datos) {
        this.datos = datos;
    }




}


