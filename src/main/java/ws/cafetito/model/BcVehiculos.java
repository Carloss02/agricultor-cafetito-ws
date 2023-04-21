package ws.cafetito.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import ws.dto.VehiculosAutorizadosDto;

@Entity
@Table(name="bc_vehiculos", schema="db_cafetito")
public class BcVehiculos  implements java.io.Serializable {


     private String placaVehiculo;
     private Integer estadoVehiculo;
     private String marcaVehiculo;
     private Integer modeloVehiculo;
     private String colorVehiculo;
     private String tipoVehiculo;
     private BigDecimal pesoVehiculo;
     private String usuarioCreacion;
     private Date fechaCreacion;

    public BcVehiculos() {
    }

	
    public BcVehiculos(String placaVehiculo, Date fechaCreacion) {
        this.placaVehiculo = placaVehiculo;
        this.fechaCreacion = fechaCreacion;
    }
    public BcVehiculos(String placaVehiculo, Integer estadoVehiculo, String marcaVehiculo, Integer modeloVehiculo, String colorVehiculo, String tipoVehiculo, BigDecimal pesoVehiculo, String usuarioCreacion, Date fechaCreacion) {
       this.placaVehiculo = placaVehiculo;
       this.estadoVehiculo = estadoVehiculo;
       this.marcaVehiculo = marcaVehiculo;
       this.modeloVehiculo = modeloVehiculo;
       this.colorVehiculo = colorVehiculo;
       this.tipoVehiculo = tipoVehiculo;
       this.pesoVehiculo = pesoVehiculo;
       this.usuarioCreacion = usuarioCreacion;
       this.fechaCreacion = fechaCreacion;
    }
   
     @Id 

    
    @Column(name="PLACA_VEHICULO", unique=true, nullable=false, length=10)
    public String getPlacaVehiculo() {
        return this.placaVehiculo;
    }
    
    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    @Column(name="ESTADO_VEHICULO")
    public Integer getEstadoVehiculo() {
        return this.estadoVehiculo;
    }
    
    public void setEstadoVehiculo(Integer estadoVehiculo) {
        this.estadoVehiculo = estadoVehiculo;
    }

    
    @Column(name="MARCA_VEHICULO", length=30)
    public String getMarcaVehiculo() {
        return this.marcaVehiculo;
    }
    
    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    
    @Column(name="MODELO_VEHICULO")
    public Integer getModeloVehiculo() {
        return this.modeloVehiculo;
    }
    
    public void setModeloVehiculo(Integer modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    
    @Column(name="COLOR_VEHICULO", length=20)
    public String getColorVehiculo() {
        return this.colorVehiculo;
    }
    
    public void setColorVehiculo(String colorVehiculo) {
        this.colorVehiculo = colorVehiculo;
    }

    
    @Column(name="TIPO_VEHICULO", length=30)
    public String getTipoVehiculo() {
        return this.tipoVehiculo;
    }
    
    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    
    @Column(name="PESO_VEHICULO", precision=10)
    public BigDecimal getPesoVehiculo() {
        return this.pesoVehiculo;
    }
    
    public void setPesoVehiculo(BigDecimal pesoVehiculo) {
        this.pesoVehiculo = pesoVehiculo;
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
    public VehiculosAutorizadosDto tovehiculosAutorizadosDto() {
        VehiculosAutorizadosDto vehiculoDto = new VehiculosAutorizadosDto(
                this.getPlacaVehiculo(),
                this.getMarcaVehiculo(),
                this.getModeloVehiculo(),
                this.getColorVehiculo(),
                this.getTipoVehiculo(),
                this.getPesoVehiculo(),
                this.getEstadoVehiculo()
        );
        return vehiculoDto;
    }


}


