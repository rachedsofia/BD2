package ar.unrn.tp.servicios;

import ar.unrn.tp.modelo.Descuento;
import ar.unrn.tp.modelo.PromocionMarca;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;

public class JPADescuentoServices implements ar.unrn.tp.api.DescuentoService {
    private final EntityManager em;

    public JPADescuentoServices(EntityManager em) {
        this.em = em;
    }

    @Override
    public void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde, LocalDate fechaHasta, float porcentaje) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Descuento descuento = new PromocionMarca(fechaDesde, fechaHasta, marcaTarjeta, porcentaje);
            em.persist(descuento);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void crearDescuento(String marcaProducto, LocalDate fechaDesde, LocalDate fechaHasta, float porcentaje) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Descuento descuento = new PromocionMarca(fechaDesde, fechaHasta, marcaProducto, porcentaje);
            em.persist(descuento);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
