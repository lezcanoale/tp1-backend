package py.com.tp1backend.components;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import py.com.tp1backend.domain.Cliente;

import java.io.Serializable;

@Component
@Transactional
public class ClientesEJB implements IClientesEJB {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Cliente guardar(Cliente cliente) {
        return em.merge(cliente);
    }
}
