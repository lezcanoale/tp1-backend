package py.com.tp1backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.com.tp1backend.domain.ConceptoUso;
import py.com.tp1backend.domain.UsoPuntosCabecera;
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

        return ResponseEntity.ok(usoPuntosCabeceras);

    }
}
