package py.com.tp1backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import py.com.tp1backend.domain.ConceptoUso;
import py.com.tp1backend.domain.UsoPuntosCabecera;

import java.util.Date;
import java.util.List;

public interface UsoPuntosCabeceraRepository extends JpaRepository<UsoPuntosCabecera,Long> {
    @Query("SELECT pc FROM UsoPuntosCabecera pc WHERE pc.cliente.id=:idCliente")
    public List<UsoPuntosCabecera> findByClienteId(Long idCliente);

    @Query("SELECT pu FROM UsoPuntosCabecera pu WHERE pu.concepto=:concepto ")
    public List<UsoPuntosCabecera> findByConcepto(ConceptoUso concepto);

    @Query("SELECT pu FROM UsoPuntosCabecera pu WHERE pu.fecha=:fecha")
    public List<UsoPuntosCabecera> findByFecha(Date fecha);

}
