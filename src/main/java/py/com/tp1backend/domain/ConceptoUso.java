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
    private Long puntosRequeridos;

    public String getDescripcionUso() {
        return descripcionUso;
    }

    public void setDescripcionUso(String descripcionUso) {
        this.descripcionUso = descripcionUso;
    }

    public Long getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(Long puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }
}
