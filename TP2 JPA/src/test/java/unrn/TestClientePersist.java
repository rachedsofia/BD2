package unrn;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestClientePersist {
    @Test
    public void testPersistCliente() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
        EntityManager em = emf.createEntityManager();


        Cliente cliente = new Cliente("Sofia", "Rached", "44122180", "msofiarached@gmail.com");

        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();

        assertNotNull(cliente.getId());

        em.close();
        emf.close();
    }
}