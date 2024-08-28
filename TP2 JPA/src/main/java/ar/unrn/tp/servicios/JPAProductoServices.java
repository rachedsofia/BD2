package ar.unrn.tp.servicios;

import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.modelo.Producto;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class JPAProductoServices implements ProductoService {
    private final EntityManager em;

    public JPAProductoServices(EntityManager em) {
        this.em = em;
    }

    @Override
    public void crearProducto(String codigo, String descripcion, float precio, String IdCategoria) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Producto producto = new Producto(codigo, descripcion, IdCategoria, precio);
            em.persist(producto);
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
    public void modificarProducto(Long idProducto, String codigo, String descripcion, String categoria, double precio) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Producto producto = em.getReference(Producto.class, idProducto);
            producto.setCodigo("1234567");
            producto.setDescripcion("");
            producto.setPrecio(4);
            producto.setMiCategoria("a");
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
    public List listarProductos() {
        return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();

    }

    @Override
    public List<Producto> buscarProductos(List<Long> idsProductos) {
        return em.createQuery("SELECT p FROM Producto p WHERE p.id IN :id", Producto.class)
                .setParameter("id", idsProductos)
                .getResultList();
    }
}
