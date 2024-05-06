package py.com.tp1backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import py.com.tp1backend.domain.ReglaAsignacionPuntos;

public interface ReglaPuntosRepository extends JpaRepository<ReglaAsignacionPuntos,Long> {
    @Query("SELECT ra.montoPunto FROM ReglaAsignacionPuntos ra WHERE :monto>=ra.limiteInferior AND :monto<=ra.limiteSuperior")
    public Long getMontoPunto(@Param("monto") Long monto);
}
