package py.com.tp1backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.tp1backend.domain.BolsaPuntos;
import py.com.tp1backend.domain.Cliente;
import py.com.tp1backend.domain.VencimientoPuntaje;
import py.com.tp1backend.repository.BolsaPuntosRepository;
import py.com.tp1backend.repository.VencimientoPuntosRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private PuntosService puntosService;

    @Autowired
    private BolsaPuntosRepository bolsaPuntosRepository;

    @Autowired
    private VencimientoPuntosRepository vencimientoPuntosRepository;

    public BolsaPuntos asignarPuntosCliente(Cliente cliente, Long monto){
        //Traemos el ultimo periodo de puntajes validos de la tabla
            VencimientoPuntaje vencimientoPuntaje=vencimientoPuntosRepository.findLastRecord();
            Long puntos=puntosService.calcularPuntos(monto);
            BolsaPuntos bolsaPuntos=new BolsaPuntos();
            Date fechaActual=new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaActual);
            calendar.add(Calendar.DAY_OF_MONTH,vencimientoPuntaje.getDuracionDiasPuntaje());
            Date fechaVencimiento=calendar.getTime();
            //Verificamos que la fechaActual está dentro del periodo de validez propio del puntaje
            if(fechaActual.after(vencimientoPuntaje.getFechaInicioValidez()) && fechaActual.before(vencimientoPuntaje.getFechaFinValidez())){
                bolsaPuntos.setFechaAsignacionPuntaje(fechaActual);
                //Calculamos cuantos días es válido el puntaje para el cliente

                //Una vez sumamos los dias válidos a la fecha de asignacion, preguntamos si cae dentro del rango de vencimiento del puntaje
                if(fechaVencimiento.before(vencimientoPuntaje.getFechaFinValidez())){
                    //le corresponde los x dias definidos en parametrizacion
                    bolsaPuntos.setFechaCaducidadPuntaje(fechaVencimiento);
                }else{
                    //la fecha de caducidad pasa a ser directamente la fecha de vencimiento del propio del puntaje
                    bolsaPuntos.setFechaCaducidadPuntaje(vencimientoPuntaje.getFechaFinValidez());
                }

            }else{
                throw new RuntimeException("La fecha está fuera del periodo de validez de los puntajes para poder asignar al cliente");
            }
        bolsaPuntos.setCliente(cliente);
        bolsaPuntos.setPuntajeAsignado(puntos);
        bolsaPuntos.setSaldoPuntos(0L);
        bolsaPuntos.setMontoOperacion(monto);
        bolsaPuntosRepository.save(bolsaPuntos);
        return bolsaPuntos;
    }
}
