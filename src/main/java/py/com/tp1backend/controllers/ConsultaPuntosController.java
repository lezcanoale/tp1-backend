package py.com.tp1backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.com.tp1backend.domain.BolsaPuntos;
import py.com.tp1backend.domain.ConceptoUso;
import py.com.tp1backend.domain.UsoPuntosCabecera;
import py.com.tp1backend.repository.BolsaPuntosRepository;
import py.com.tp1backend.repository.UsoPuntosCabeceraRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaPuntosController {
    @Autowired
    private UsoPuntosCabeceraRepository usoPuntosCabeceraRepository;

    @Autowired
    private BolsaPuntosRepository bolsaPuntosRepository;

    @GetMapping("/uso-puntos")
    public ResponseEntity<List<UsoPuntosCabecera>> consultaUsos(
            @RequestParam(value = "idCliente",required = false) Long idCliente ,
            @RequestParam(value = "fecha",required = false) String fechaParametro,
            @RequestBody(required = false) ConceptoUso conceptoUso
            ) throws Exception {

        List<UsoPuntosCabecera>usoPuntosCabeceras=null;

        if(idCliente != null) {
            usoPuntosCabeceras= usoPuntosCabeceraRepository.findByClienteId(idCliente);
        }


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date fecha = null;

        try {
            if(fechaParametro != null) {fecha = sdf.parse(fechaParametro);}
            if(fechaParametro != null) {usoPuntosCabeceras= usoPuntosCabeceraRepository.findByFecha(fecha);}
        } catch (ParseException e) {
            throw new Exception(e.getMessage());
        }

        if(conceptoUso != null) {usoPuntosCabeceras=usoPuntosCabeceraRepository.findByConcepto(conceptoUso);}
        if(idCliente == null && fechaParametro==null && conceptoUso == null) {usoPuntosCabeceras=usoPuntosCabeceraRepository.findAll();}
        return ResponseEntity.ok(usoPuntosCabeceras);

    }

    @GetMapping("/bolsa-puntos")
    public ResponseEntity<List<BolsaPuntos>>consultaBolsaPuntos(
            @RequestParam(required = false)Long idCliente,
            @RequestParam(required = false)Integer rangoInferiorPuntos,
            @RequestParam(required = false)Integer rangoSuperiorPuntos
    ){
        List<BolsaPuntos>bolsaPuntos=null;
        if(idCliente!=null){
            bolsaPuntos=bolsaPuntosRepository.findByCliente(idCliente);
        }
        if(rangoInferiorPuntos!=null || rangoInferiorPuntos!=null){
            bolsaPuntos=bolsaPuntosRepository.findBolsaPuntosBySaldoPuntos(rangoInferiorPuntos,rangoSuperiorPuntos);
        }
        if(idCliente==null && rangoSuperiorPuntos==null && rangoInferiorPuntos==null){
            bolsaPuntos=bolsaPuntosRepository.findAll();
        }
        return ResponseEntity.ok(bolsaPuntos);
    }
}
