package py.com.tp1backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;


@Entity
@Table(name = "vencimiento_puntos")
public class VencimientoPuntaje extends GenericEntity{
    private static final long serialVersionUID = 1L;

   @Column(nullable = false)
    private Date fechaInicioValidez;
    @Column(nullable = false)
    private Date fechaFinValidez;
    @Column(nullable = false)
    private Integer duracionDiasPuntaje;

    public Date getFechaInicioValidez() {
        return fechaInicioValidez;
    }

    public void setFechaInicioValidez(Date fechaInicioValidez) {
        this.fechaInicioValidez = fechaInicioValidez;
    }

    public Date getFechaFinValidez() {
        return fechaFinValidez;
    }

    public void setFechaFinValidez(Date fechaFinValidez) {
        this.fechaFinValidez = fechaFinValidez;
    }

    public Integer getDuracionDiasPuntaje() {
        return duracionDiasPuntaje;
    }

    public void setDuracionDiasPuntaje(Integer duracionDiasPuntaje) {
        this.duracionDiasPuntaje = duracionDiasPuntaje;
    }
}
