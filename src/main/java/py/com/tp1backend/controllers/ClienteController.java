package py.com.tp1backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.com.tp1backend.domain.Cliente;
import py.com.tp1backend.repository.ClienteRepository;

import java.util.List;

@RestController
@RequestMapping(value="/api/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return ResponseEntity.ok(clientes);
    }
}
