package py.com.tp1backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="concepto_uso")
public class ConceptoUso extends GenericEntity {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String descripcionUso;

    @Column(nullable = false)
    private Integer puntosRequeridos;

    public String getDescripcionUso() {
        return descripcionUso;
    }

    public void setDescripcionUso(String descripcionUso) {
        this.descripcionUso = descripcionUso;
    }

    public Integer getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(Integer puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }
}
