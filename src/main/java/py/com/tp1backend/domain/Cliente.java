package py.com.tp1backend.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="cliente")
public class Cliente extends GenericEntity{
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String numeroDocumento;

    @Column(nullable = false)
    private String tipoDocumento;

    @Column(nullable = false)
    private String nacionalidad;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Date fechaNacimiento;

    @Column(nullable = false)
    private String telefono;

    @OneToMany
    private List <UsoPuntosCabecera> usoPuntosCabeceras;


    public String getEmail() {
        return email;
    }

    public List<UsoPuntosCabecera> getUsoPuntosCabeceras() {
        return usoPuntosCabeceras;
    }

    public void setUsoPuntosCabeceras(List<UsoPuntosCabecera> usoPuntosCabeceras) {
        this.usoPuntosCabeceras = usoPuntosCabeceras;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
