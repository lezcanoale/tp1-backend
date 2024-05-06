package py.com.tp1backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import py.com.tp1backend.domain.BolsaPuntos;
import py.com.tp1backend.domain.Cliente;

import java.util.Date;
import java.util.List;

public interface BolsaPuntosRepository extends JpaRepository<BolsaPuntos, Long> {
    @Query("SELECT bp FROM BolsaPuntos bp WHERE  bp.cliente.id=:idCliente")
    public List<BolsaPuntos> findByCliente( Long idCliente);

    @Query("SELECT bp FROM BolsaPuntos bp WHERE (:puntosInferior is null or bp.saldoPuntos>=:puntosInferior) AND (:puntosSuperior is null or bp.saldoPuntos<=:puntosSuperior)")
    public List<BolsaPuntos> findBolsaPuntosBySaldoPuntos(Integer puntosInferior, Integer puntosSuperior);

    @Query("SELECT b.cliente FROM BolsaPuntos b WHERE b.fechaCaducidadPuntaje BETWEEN CURRENT_DATE AND :fechaLimite AND b.saldoPuntos > 0")
    List<Cliente> findPuntosPorVencer(Date fechaLimite);

    @Query("SELECT bp FROM BolsaPuntos bp WHERE bp.cliente.id=:idCliente AND bp.fechaCaducidadPuntaje>= current date  ORDER BY bp.fechaAsignacionPuntaje ASC")
    List<BolsaPuntos> findBolsaPuntosMasViejosAndValidosByCliente(Long idCliente);

    @Query("SELECT SUM(bp.saldoPuntos)  FROM BolsaPuntos bp WHERE bp.cliente.id=:idCliente AND bp.fechaCaducidadPuntaje>= current date")
    Long  sumaPuntosBolsaPorClienteYValidos(Long idCliente);
}
