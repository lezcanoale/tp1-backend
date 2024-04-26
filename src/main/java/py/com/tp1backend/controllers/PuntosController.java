package py.com.tp1backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import py.com.tp1backend.domain.BolsaPuntos;
import py.com.tp1backend.domain.Cliente;
import py.com.tp1backend.domain.ConceptoUso;
import py.com.tp1backend.repository.ClienteRepository;
import py.com.tp1backend.repository.ConceptoUsoRepository;
import py.com.tp1backend.repository.PuntosRepository;
import py.com.tp1backend.services.ClienteService;
import py.com.tp1backend.services.PuntosService;

@RestController
@RequestMapping("/api/puntos")
public class PuntosController {
    @Autowired
    private PuntosRepository puntosRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PuntosService puntosService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ConceptoUsoRepository conceptoUsoRepository;

    @PostMapping("/cargar-puntos")
    private ResponseEntity<?> cargarPuntos(@RequestParam(required = true)Long idCliente,@RequestParam(required = true)Long monto){
        Cliente cliente=clienteRepository.findById(idCliente).orElse(null);
        if(cliente==null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró el cliente con ID: "+ idCliente);
        try{
            BolsaPuntos bolsaPuntos=clienteService.asignarPuntosCliente(cliente,monto);
            return ResponseEntity.ok("Los puntos asignados al cliente:" + cliente.getNombre() + " " + cliente.getApellido() + ",son " + bolsaPuntos.getPuntajeAsignado() + " puntos y son válidos hasta el: "+bolsaPuntos.getFechaCaducidadPuntaje());

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PostMapping("/usar-puntos")
    private ResponseEntity<?> usarPuntos(@RequestParam(required = true)Long idCliente,@RequestParam(required = true)Long idConceptoUso){
        Cliente cliente=clienteRepository.findById(idCliente).orElse(null);
        if(cliente==null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró el cliente con ID: "+ idCliente);
        ConceptoUso conceptoUso=conceptoUsoRepository.findById(idConceptoUso).orElse(null);
        if(conceptoUso==null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró el concepto con ID: "+ idCliente);
        try{
          return ResponseEntity.ok(clienteService.usarPuntosCliente(cliente,conceptoUso,conceptoUso.getPuntosRequeridos()));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
