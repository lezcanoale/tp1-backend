package py.com.tp1backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.tp1backend.domain.*;
import py.com.tp1backend.repository.BolsaPuntosRepository;
import py.com.tp1backend.repository.UsoPuntosCabeceraRepository;
import py.com.tp1backend.repository.UsoPuntosDetalleRepository;
import py.com.tp1backend.repository.VencimientoPuntosRepository;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class ClienteService {
    @Autowired
    private PuntosService puntosService;

    @Autowired
    private BolsaPuntosRepository bolsaPuntosRepository;

    @Autowired
    private VencimientoPuntosRepository vencimientoPuntosRepository;

    @Autowired
    private UsoPuntosCabeceraRepository usoPuntosCabeceraRepository;

    @Autowired
    private UsoPuntosDetalleRepository usoPuntosDetalleRepository;

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
    public String usarPuntosCliente(Cliente cliente, ConceptoUso conceptoUso,Long puntos){
        //Traemos las bolsas de puntos del cliente ordenado por los mas antiguos por fecha asignacion
        List<BolsaPuntos> bolsaPuntos=bolsaPuntosRepository.findBolsaPuntosMasViejosAndValidosByCliente(cliente.getId());
        if(bolsaPuntos.size()>0){
            try{
                if(bolsaPuntosRepository.sumaPuntosBolsaPorClienteYValidos(cliente.getId())>=puntos){
                    descontarPuntosdeBolsa(bolsaPuntos,puntos,cliente,conceptoUso);
//                    try{
//                        enviarCorreoOutlook(cliente.getEmail(),"Comprobante de uso de puntos","Se utilizaron: "+puntos+ "de la bolsa de puntos con fecha "+ new Date());
//                    }catch(Exception e){
//                        throw new RuntimeException("Se utilizaron los puntos correctamente pero ocurrió un error al enviar el correo: "+e.getMessage());
//                    }
                    return "Se utilizaron los puntos correctamente.";
                }else{
                    return "No tiene puntos suficientes";
                }

            }catch (RuntimeException e){
                throw new RuntimeException("Ocurrió un error al utilizar los puntos: "+ e.getMessage());
            }
        }else{
            throw new RuntimeException("El cliente no posee bolsa de puntos.");
        }

    }
    public void descontarPuntosdeBolsa(List<BolsaPuntos>bolsaPuntosList,Long puntos,Cliente cliente,ConceptoUso conceptoUso){
        Long puntosAUtilizar = puntos;
        UsoPuntosCabecera usoPuntosCabecera=new UsoPuntosCabecera();
        Long saldoPuntos=puntos;
        for (BolsaPuntos bolsa : bolsaPuntosList) {
            if(bolsa.getSaldoPuntos()>=puntosAUtilizar){
                bolsa.setPuntajeUtilizado(bolsa.getPuntajeUtilizado()+puntosAUtilizar);
                bolsa.setSaldoPuntos(bolsa.getSaldoPuntos()-puntosAUtilizar);
                usoPuntosCabecera.setCliente(cliente);
                usoPuntosCabecera.setConcepto(conceptoUso);
                usoPuntosCabecera.setPuntajeUtilizado(puntosAUtilizar);
                usoPuntosCabecera.setFecha(new Date());
                usoPuntosCabeceraRepository.save(usoPuntosCabecera);
                bolsaPuntosRepository.save(bolsa);
                break;
            }
            if(bolsa.getSaldoPuntos()>0 && bolsa.getSaldoPuntos()<saldoPuntos){
                saldoPuntos =puntosAUtilizar-bolsa.getSaldoPuntos();
                bolsa.setPuntajeUtilizado(bolsa.getPuntajeUtilizado()+bolsa.getSaldoPuntos());
                bolsa.setSaldoPuntos(0L);//en cero queda en teoria
                usoPuntosCabecera.setCliente(cliente);
                usoPuntosCabecera.setConcepto(conceptoUso);
                usoPuntosCabecera.setPuntajeUtilizado(puntosAUtilizar);
                usoPuntosCabecera.setFecha(new Date());
                usoPuntosCabeceraRepository.save(usoPuntosCabecera);
                bolsaPuntosRepository.save(bolsa);
                puntosAUtilizar = saldoPuntos;
            }
        }
    }
    public static void enviarCorreoOutlook(String destinatario, String asunto, String cuerpo) {
        String remitente = "tp1-backend@outlook.com";
        String password = "mgc239Fl4";
        String host = "smtp.office365.com";
        String port = "587";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport.send(message);
            System.out.println("Correo electrónico enviado correctamente.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
