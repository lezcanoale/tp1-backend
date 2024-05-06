package py.com.tp1backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import py.com.tp1backend.domain.Cliente;

import java.util.Date;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c WHERE c.nacionalidad=:nacionalidad")
    public List<Cliente> findByNacionalidad( String nacionalidad);

    @Query("SELECT c FROM Cliente c WHERE c.fechaNacimiento=:fechaCumple")
    public List<Cliente> findByFechaNacimiento( Date fechaCumple);

    @Query("SELECT c FROM Cliente c WHERE c.id=:id")
    public Cliente findClienteById( Long id);
}
