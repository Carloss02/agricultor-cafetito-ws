package ws.agricultor.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="agr_usuarios"
    ,catalog="db_agricultor"
)
public class AgrUsuarios  implements java.io.Serializable {


     private String idUsuario;
     private String nombreUsuario;
     private String telefonoUsuario;
     private String emailUsuario;
     private String password;
     private String roles;
     private Character estadoUsuario;
     private String usuarioCreacion;
     private Date fechaCreacion;

    public AgrUsuarios() {
    }

	
    public AgrUsuarios(String idUsuario, Date fechaCreacion) {
        this.idUsuario = idUsuario;
        this.fechaCreacion = fechaCreacion;
    }
    public AgrUsuarios(String idUsuario, String nombreUsuario, String telefonoUsuario, String emailUsuario, String password, String roles, Character estadoUsuario, String usuarioCreacion, Date fechaCreacion) {
       this.idUsuario = idUsuario;
       this.nombreUsuario = nombreUsuario;
       this.telefonoUsuario = telefonoUsuario;
       this.emailUsuario = emailUsuario;
       this.password = password;
       this.roles = roles;
       this.estadoUsuario = estadoUsuario;
       this.usuarioCreacion = usuarioCreacion;
       this.fechaCreacion = fechaCreacion;
    }
   
     @Id 

    
    @Column(name="ID_USUARIO", unique=true, nullable=false, length=50)
    public String getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    @Column(name="NOMBRE_USUARIO", length=50)
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    
    @Column(name="TELEFONO_USUARIO", length=10)
    public String getTelefonoUsuario() {
        return this.telefonoUsuario;
    }
    
    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    
    @Column(name="EMAIL_USUARIO", length=50)
    public String getEmailUsuario() {
        return this.emailUsuario;
    }
    
    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    
    @Column(name="PASSWORD", length=200)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="ROLES", length=200)
    public String getRoles() {
        return this.roles;
    }
    
    public void setRoles(String roles) {
        this.roles = roles;
    }

    
    @Column(name="ESTADO_USUARIO", length=1)
    public Character getEstadoUsuario() {
        return this.estadoUsuario;
    }
    
    public void setEstadoUsuario(Character estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
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


