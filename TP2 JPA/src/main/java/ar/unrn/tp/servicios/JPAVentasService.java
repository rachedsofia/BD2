package ar.unrn.tp.servicios;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.modelo.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.stream.Collectors;

public class JPAVentasService implements VentaService {
    private final EntityManager em;
    private final ProcesoDePago procesoDePago;
    private final ClienteService clienteService;
    private final ProductoService productoService;

    public JPAVentasService(EntityManager em, ProcesoDePago procesoDePago, ClienteService clienteService, ProductoService productoService) {
        this.em = em;
        this.procesoDePago = procesoDePago;
        this.clienteService = clienteService;
        this.productoService = productoService;
    }


    @Override
    public void realizarVenta(Long idCliente, List<Long> productos, Long idTarjeta) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Cliente cliente = clienteService.buscarCliente(idCliente);

            List<Producto> listaProductos = productos.stream()
                    .map(idProducto -> em.find(Producto.class, idProducto))
                    .collect(Collectors.toList());

            Tarjeta tarjeta = em.find(Tarjeta.class, idTarjeta);

            Carrito carrito = new Carrito(cliente);
            listaProductos.forEach(carrito::agregarProducto);

            List<Descuento> descuentos = em.createQuery("SELECT d FROM Descuento d", Descuento.class).getResultList();

            Venta venta = procesoDePago.procesarPago(carrito, tarjeta, descuentos);
            em.persist(venta);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
          /*  if (em != null && em.isOpen()) {
                em.close();
            }*/
        }
    }

    @Override
    public float calcularMonto(List<Long> productos, Long idTarjeta) {
        Tarjeta tarjeta = em.find(Tarjeta.class, idTarjeta);

        //   Tarjeta tarjeta = clienteService.buscarTarjeta(idTarjeta);
        List<Producto> listaProductos = productoService.buscarProductos(productos);

        Carrito carrito = new Carrito(null);
        listaProductos.forEach(carrito::agregarProducto);

        // Obtener los descuentos activos
        List<Descuento> descuentos = em.createQuery("SELECT d FROM Descuento d WHERE d.activo = true", Descuento.class)
                .getResultList();

        // Calcular monto total aplicando descuentos

        double montoTotal = 0.0;
        for (Producto producto : listaProductos) {
            double precioProducto = producto.getPrecio();

            // Aplicar descuentos por marca
            for (Descuento descuento : descuentos) {
                if (descuento instanceof PromocionMarca promocionMarca) {
                    precioProducto -= promocionMarca.aplicar(producto, precioProducto);
                }
            }

            montoTotal += precioProducto;
        }

        // Aplicar descuentos por medio de pago
        double descuentoMedioDePago = 0;
        for (Descuento descuento : descuentos) {
            if (descuento instanceof PromocionMedioDePago) {
                descuentoMedioDePago = ((PromocionMedioDePago) descuento).aplicarDescuento(montoTotal, tarjeta.getMarca());
            }
        }

        montoTotal -= descuentoMedioDePago;

        return (float) montoTotal;
    }


    @Override
    public List ventas() {
        return em.createQuery("SELECT v FROM Venta v", Venta.class).getResultList();
    }

}
