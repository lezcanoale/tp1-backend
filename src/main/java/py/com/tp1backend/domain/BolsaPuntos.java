package py.com.tp1backend.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bolsa_puntos")
public class BolsaPuntos extends GenericEntity{
    private static final long serialVersionUID = 1L;


    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;


    @Column(nullable = false)
    private Date fechaAsignacionPuntaje;

    @Column(nullable = false)
    private Date fechaCaducidadPuntaje;


    private Long puntajeAsignado ;


    private Long puntajeUtilizado;


    private Long saldoPuntos;

    @Column(nullable = false)
    private Long montoOperacion;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaAsignacionPuntaje() {
        return fechaAsignacionPuntaje;
    }

    public void setFechaAsignacionPuntaje(Date fechaAsignacionPuntaje) {
        this.fechaAsignacionPuntaje = fechaAsignacionPuntaje;
    }

    public Date getFechaCaducidadPuntaje() {
        return fechaCaducidadPuntaje;
    }

    public void setFechaCaducidadPuntaje(Date fechaCaducidadPuntaje) {
        this.fechaCaducidadPuntaje = fechaCaducidadPuntaje;
    }

    public Long getPuntajeAsignado() {
        return puntajeAsignado;
    }

    public void setPuntajeAsignado(Long puntajeAsignado) {
        this.puntajeAsignado = puntajeAsignado;
    }

    public Long getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Long puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }


    public Long getSaldoPuntos() {
        return saldoPuntos;
    }

    public void setSaldoPuntos(Long saldoPuntos) {
        this.saldoPuntos = saldoPuntos;
    }

    public Long getMontoOperacion() {
        return montoOperacion;
    }

    public void setMontoOperacion(Long montoOperacion) {
        this.montoOperacion = montoOperacion;
    }



}
