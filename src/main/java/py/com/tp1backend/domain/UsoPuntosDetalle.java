package py.com.tp1backend.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "uso_puntos_detalle")
public class UsoPuntosDetalle extends GenericEntity{
    private static final long serialVersionUID = 1L;

    @ManyToOne
    private UsoPuntosCabecera usoPuntosCabecera;

    @ManyToOne
    @JoinColumn(name = "id_bolsa_puntos")
    private BolsaPuntos bolsaPuntos;

    @Column(nullable = false)
    private Integer puntajeUtilizado;

    public UsoPuntosCabecera getUsoPuntosCabecera() {
        return usoPuntosCabecera;
    }

    public void setUsoPuntosCabecera(UsoPuntosCabecera usoPuntosCabecera) {
        this.usoPuntosCabecera = usoPuntosCabecera;
    }

    public BolsaPuntos getBolsaPuntos() {
        return bolsaPuntos;
    }

    public void setBolsaPuntos(BolsaPuntos bolsaPuntos) {
        this.bolsaPuntos = bolsaPuntos;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }
}
