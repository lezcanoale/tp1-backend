package py.com.tp1backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.tp1backend.domain.ReglaAsignacionPuntos;
import py.com.tp1backend.repository.ReglaPuntosRepository;

import java.util.List;

@Service
public class PuntosService {

    @Autowired
    private ReglaPuntosRepository reglaDePuntosRepository;

    public Long calcularPuntos(Long monto) {
        List<ReglaAsignacionPuntos> reglas = reglaDePuntosRepository.findAll();
        for (ReglaAsignacionPuntos regla : reglas) {
            if ((monto >= regla.getLimiteInferior()) && (regla.getLimiteSuperior() == null || monto <= regla.getLimiteSuperior())) {
                return  (monto / regla.getMontoPunto());
            }
        }
        return 0L;
    }

}