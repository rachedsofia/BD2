package ar.unrn.tp.main;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.modelo.*;
import ar.unrn.tp.servicios.JPAClienteServices;
import ar.unrn.tp.servicios.JPAProductoServices;
import ar.unrn.tp.servicios.JPAVentasService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
        EntityManager em = emf.createEntityManager();

        // Crear instancias de servicios
        ClienteService clienteService = new JPAClienteServices(em);
        ProductoService productoService = new JPAProductoServices(em);
        ProcesoDePago procesoDePago = new ProcesoDePago();
        VentaService ventaService = new JPAVentasService(em, procesoDePago, clienteService, productoService);

        // Crear un cliente, productos y tarjeta (esto generalmente sería parte de otro proceso)
        Cliente cliente = new Cliente("Juan", "Perez", "12345678", "juan@example.com");
        Tarjeta tarjeta = new Tarjeta(234 - 5678 - 9012 - 3456, 5000, "Visa");
        cliente.agregarTarjeta(tarjeta);
        Producto producto1 = new Producto("A1", "es una remera", "remera", 100);
        Producto producto2 = new Producto("A2", "es un short", "pantalon", 150);

        em.getTransaction().begin();
        em.persist(cliente);
        em.persist(tarjeta);
        em.persist(producto1);
        em.persist(producto2);
        em.getTransaction().commit();

        // Realizar una venta
        List<Long> productosIds = Arrays.asList(producto1.getId(), producto2.getId());
        Long idTarjeta = tarjeta.getId();
        Long idCliente = cliente.getId();

        ventaService.realizarVenta(idCliente, productosIds, idTarjeta);

        // Calcular el monto total de una venta con descuentos
        float montoTotal = ventaService.calcularMonto(productosIds, idTarjeta);
        System.out.println("Monto total con descuentos: " + montoTotal);

        // Listar todas las ventas realizadas
        List<Venta> ventasRealizadas = ventaService.ventas();
        ventasRealizadas.forEach(venta -> System.out.println("Venta realizada: " + venta));

        em.close();
        emf.close();
    }
}

