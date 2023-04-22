package ws.cafetito.model;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Builder;
import ws.dto.TransportistasAutorizadosDto;

@Builder
@Entity
@Table(name="bc_transportistas", schema="db_cafetito")
public class BcTransportistas  implements java.io.Serializable {


     private String idLicencia;
     private Integer estadoTransportista;
     private String tipoLicencia;
     private String nombreTransportista;
     private String telefonoTransportista;
     private String emailTransportista;
     private String usuarioCreacion;
     private Date fechaCreacion;

    public BcTransportistas() {
    }

	
    public BcTransportistas(String idLicencia, Date fechaCreacion) {
        this.idLicencia = idLicencia;
        this.fechaCreacion = fechaCreacion;
    }
    public BcTransportistas(String idLicencia, Integer estadoTransportista, String tipoLicencia, String nombreTransportista, String telefonoTransportista, String emailTransportista, String usuarioCreacion, Date fechaCreacion) {
       this.idLicencia = idLicencia;
       this.estadoTransportista = estadoTransportista;
       this.tipoLicencia = tipoLicencia;
       this.nombreTransportista = nombreTransportista;
       this.telefonoTransportista = telefonoTransportista;
       this.emailTransportista = emailTransportista;
       this.usuarioCreacion = usuarioCreacion;
       this.fechaCreacion = fechaCreacion;
    }
   
     @Id 

    
    @Column(name="ID_LICENCIA", unique=true, nullable=false, length=15)
    public String getIdLicencia() {
        return this.idLicencia;
    }
    
    public void setIdLicencia(String idLicencia) {
        this.idLicencia = idLicencia;
    }

    @Column(name="ESTADO_TRANSPORTISTA")
    public Integer getEstadoTransportista() {
        return this.estadoTransportista;
    }
    
    public void setEstadoTransportista(Integer estadoTransportista) {
        this.estadoTransportista = estadoTransportista;
    }

    
    @Column(name="TIPO_LICENCIA", length=5)
    public String getTipoLicencia() {
        return this.tipoLicencia;
    }
    
    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    
    @Column(name="NOMBRE_TRANSPORTISTA", length=50)
    public String getNombreTransportista() {
        return this.nombreTransportista;
    }
    
    public void setNombreTransportista(String nombreTransportista) {
        this.nombreTransportista = nombreTransportista;
    }

    
    @Column(name="TELEFONO_TRANSPORTISTA", length=10)
    public String getTelefonoTransportista() {
        return this.telefonoTransportista;
    }
    
    public void setTelefonoTransportista(String telefonoTransportista) {
        this.telefonoTransportista = telefonoTransportista;
    }

    
    @Column(name="EMAIL_TRANSPORTISTA", length=50)
    public String getEmailTransportista() {
        return this.emailTransportista;
    }
    
    public void setEmailTransportista(String emailTransportista) {
        this.emailTransportista = emailTransportista;
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

    // metodo que permite mapear los datos que necesito devolver como respuesta
    //utilizando dtos
    public TransportistasAutorizadosDto toAutorizadosDto() {
        TransportistasAutorizadosDto autorizadosDto = new TransportistasAutorizadosDto(
                this.getIdLicencia(),
                this.getTipoLicencia(),
                this.getNombreTransportista(),
                this.getTelefonoTransportista(),
                this.getEmailTransportista(),
                this.getEstadoTransportista()
        );
        
        return autorizadosDto;
    }


}


