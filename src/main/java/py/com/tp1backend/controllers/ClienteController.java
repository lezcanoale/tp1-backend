package py.com.tp1backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.com.tp1backend.domain.Cliente;
import py.com.tp1backend.repository.BolsaPuntosRepository;
import py.com.tp1backend.repository.ClienteRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/api/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private BolsaPuntosRepository bolsaPuntosRepository;


    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping(value = "/puntos-vencimiento")
    public ResponseEntity<List<Cliente>>listarClientesPorVencimiento(@RequestParam(required=true) Integer diasVencimiento){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, diasVencimiento);
        Date  fechaLimite = calendar.getTime();
        List<Cliente>clientes=bolsaPuntosRepository.findPuntosPorVencer(fechaLimite);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/por-nacionalidad")
    public ResponseEntity<List<Cliente>> listarClientePorNacionalidad(@RequestParam(required=true) String nacionalidad){
        List<Cliente>clientes=clienteRepository.findByNacionalidad(nacionalidad);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/por-cumple")
    public ResponseEntity<List<Cliente>> listarClientePorCumple(@RequestParam(required=true) String fechaCumple) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date fecha = null;

        try{
            if(fechaCumple!=null){

                fecha = sdf.parse(fechaCumple);

            };
        }catch (ParseException e){
            throw new Exception(e.getMessage());
        }
        List<Cliente>clientes=clienteRepository.findByFechaNacimiento(fecha);
        return ResponseEntity.ok(clientes);
    }
    @PostMapping("/agregar-cliente")
    public ResponseEntity<?> agregarCliente(@RequestBody Cliente cliente){
        try{
            Cliente nuevoCliente=new Cliente();
            nuevoCliente.setFechaNacimiento(cliente.getFechaNacimiento());
            nuevoCliente.setNacionalidad(cliente.getNacionalidad());
            nuevoCliente.setNombre(cliente.getNombre());
            nuevoCliente.setApellido(cliente.getApellido());
            nuevoCliente.setEmail(cliente.getEmail());
            nuevoCliente.setTipoDocumento(cliente.getTipoDocumento());
            nuevoCliente.setNumeroDocumento(cliente.getNumeroDocumento());
            nuevoCliente.setTelefono(cliente.getTelefono());
            clienteRepository.save(nuevoCliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
    @PutMapping("/modificar-cliente")
    public ResponseEntity<?> modificarCliente(@RequestBody Cliente clienteRequest){
        try{
            Cliente cliente= clienteRepository.findClienteById(clienteRequest.getId());
            cliente.setFechaNacimiento(clienteRequest.getFechaNacimiento());
            cliente.setNacionalidad(clienteRequest.getNacionalidad());
            cliente.setNombre(clienteRequest.getNombre());
            cliente.setApellido(clienteRequest.getApellido());
            cliente.setEmail(clienteRequest.getEmail());
            cliente.setNumeroDocumento(clienteRequest.getNumeroDocumento());
            cliente.setTipoDocumento(clienteRequest.getTipoDocumento());
            cliente.setTelefono(clienteRequest.getTelefono());
            clienteRepository.save(cliente);
            return ResponseEntity.status(HttpStatus.OK).body(cliente);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
