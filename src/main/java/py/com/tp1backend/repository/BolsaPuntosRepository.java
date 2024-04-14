package py.com.tp1backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import py.com.tp1backend.domain.BolsaPuntos;

import java.util.List;

public interface BolsaPuntosRepository extends JpaRepository<BolsaPuntos, Long> {
    @Query("SELECT bp FROM BolsaPuntos bp WHERE  bp.cliente.id=:idCliente")
    public List<BolsaPuntos> findByCliente( Long idCliente);

    @Query("SELECT bp FROM BolsaPuntos bp WHERE (:puntosInferior is null or bp.saldoPuntos>=:puntosInferior) AND (:puntosSuperior is null or bp.saldoPuntos<=:puntosSuperior)")
    public List<BolsaPuntos> findBolsaPuntosBySaldoPuntos(Integer puntosInferior, Integer puntosSuperior);
}
