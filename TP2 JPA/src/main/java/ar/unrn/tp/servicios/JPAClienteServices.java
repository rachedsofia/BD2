package ar.unrn.tp.servicios;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.Tarjeta;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class JPAClienteServices implements ClienteService {
    private final EntityManager em;

    public JPAClienteServices(EntityManager em) {
        this.em = em;
    }

    @Override
    public void crearCliente(String nombre, String apellido, String dni, String email) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Cliente nuevoCliente = new Cliente(nombre, apellido, dni, email);
            em.persist(nuevoCliente);
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
    public void modificarCliente(Long idCliente, String nombre, String apellido, String dni, String email) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Cliente cliente = em.getReference(Cliente.class, idCliente);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setDni(dni);
            cliente.setEmail(email);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
           /* if (em != null && em.isOpen()) {
                em.close();
            }*/
        }
    }


    @Override
    public void agregarTarjeta(Long idCliente, String nro, String marca) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Cliente cliente = em.find(Cliente.class, idCliente);
            Tarjeta nuevaTarjeta = new Tarjeta(nro, marca);
            cliente.agregarTarjeta(nuevaTarjeta);
            em.merge(cliente);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            /*if (em != null && em.isOpen()) {
                em.close();
            }*/
        }
    }

    @Override
    public List listarTarjetas(Long idCliente) {
        Cliente cliente = em.getReference(Cliente.class, idCliente);
        // Cliente cliente = em.find(Cliente.class, idCliente);
        return cliente.getTarjetas();
    }

    @Override
    public Cliente buscarCliente(Long idCliente) {
        return em.find(Cliente.class, idCliente);
    }

    @Override
    public Tarjeta buscarTarjeta(Long idTarjeta) {
        return em.getReference(Tarjeta.class, idTarjeta);
    }


}
