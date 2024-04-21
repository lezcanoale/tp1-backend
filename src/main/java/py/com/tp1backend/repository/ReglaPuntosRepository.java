package py.com.tp1backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.tp1backend.domain.ReglaAsignacionPuntos;

public interface ReglaPuntosRepository extends JpaRepository<ReglaAsignacionPuntos,Long> {
}
