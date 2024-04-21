package py.com.tp1backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import py.com.tp1backend.domain.VencimientoPuntaje;

public interface VencimientoPuntosRepository extends JpaRepository<VencimientoPuntaje,Long> {
    @Query("SELECT v FROM VencimientoPuntaje v WHERE v.id = (SELECT MAX(v2.id) FROM VencimientoPuntaje v2)")
    VencimientoPuntaje findLastRecord();
}
