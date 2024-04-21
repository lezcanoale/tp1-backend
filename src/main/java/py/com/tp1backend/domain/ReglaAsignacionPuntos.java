package py.com.tp1backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="regla_asignacion_puntos")
public class ReglaAsignacionPuntos extends GenericEntity{
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private Long limiteInferior;


    private Long limiteSuperior;

    @Column(nullable = false)
    private Long montoPunto;

    public Long getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(Long limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public Long getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(Long limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public Long getMontoPunto() {
        return montoPunto;
    }

    public void setMontoPunto(Long montoPunto) {
        this.montoPunto = montoPunto;
    }
}
