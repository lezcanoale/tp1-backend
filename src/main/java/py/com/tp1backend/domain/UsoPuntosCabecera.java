package py.com.tp1backend.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "uso_puntos_cabecera")
public class UsoPuntosCabecera extends GenericEntity{
    private static final long serialVersionUID = 1L;


    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(nullable = false)
    private Long puntajeUtilizado;

    @Column(nullable = false)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_concepto_uso")
    private ConceptoUso concepto;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Long puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ConceptoUso getConcepto() {
        return concepto;
    }

    public void setConcepto(ConceptoUso concepto) {
        this.concepto = concepto;
    }
}
