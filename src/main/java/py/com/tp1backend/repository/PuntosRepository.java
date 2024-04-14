package py.com.tp1backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.tp1backend.domain.ConceptoUso;

public interface PuntosRepository extends JpaRepository<ConceptoUso,Long> {
}
