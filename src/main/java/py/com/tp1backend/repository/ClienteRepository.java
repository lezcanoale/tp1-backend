package py.com.tp1backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.tp1backend.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
