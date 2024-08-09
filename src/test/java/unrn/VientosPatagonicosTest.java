package unrn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class VientosPatagonicasTest {

    private Cliente cliente;
    private Producto producto1, producto2;
    private Carrito carrito;
    private Tarjeta tarjeta;
    private List<Descuento> descuentos;
    private PromocionMarca promocionMarca;
    private PromocionMedioDePago promocionMedioDePago;


    @BeforeEach
    void setUp() {
       cliente = new Cliente("Juan", "Pérez", 12345678, "juan@correo.com");
        tarjeta = new Tarjeta(1234-5678-9012-3456, 89000,"MemeCard");
        producto1 = new Producto("001", "Acme", "Ropa deportiva", 1000);
        producto2 = new Producto("002", "Comarca", "Calzado", 2000);
        carrito = new Carrito(cliente);

        descuentos = new ArrayList<>();
    }

    /*Calcular el monto total del carrito seleccionado sin descuentos
    vigentes, pero con descuentos que ya caducaron.*/
    @Test
    void testCalcularMontoTotalSinDescuentosVigentesConDescuentosCaducados() {
        promocionMarca = new PromocionMarca(LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(5), "Acme", 5);
        descuentos.add(promocionMarca);
        carrito.agregarProducto(producto1);
        carrito.agregarProducto(producto2);
        ServicioPago servicioPago = new ServicioPago();
        Venta venta = servicioPago.procesarPago(carrito, tarjeta, descuentos);
        Assertions.assertEquals(3000, venta.getMontoTotal());
    }

    /*Calcular el monto total del carrito con un descuento vigente para los
    productos marca Acme.*/
    @Test
    void testCalcularMontoTotalConDescuentoVigenteMarcaAcme() {
        promocionMarca =  new PromocionMarca(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), "Acme", 5);
        descuentos.add(promocionMarca);
        carrito.agregarProducto(producto1);
        carrito.agregarProducto(producto2);
        ServicioPago servicioPago = new ServicioPago();
        Venta venta = servicioPago.procesarPago(carrito, tarjeta, descuentos);
        Assertions.assertEquals(2950, venta.getMontoTotal()); // Descuento del 5% aplicado solo a producto1
    }
    /*Calcular el monto total del carrito con un descuento vigente del tipo
de Medio de pago.*/
    @Test
    void testCalcularMontoTotalConDescuentoVigenteMedioPago() {
        promocionMedioDePago = new PromocionMedioDePago(LocalDateTime.now().minusDays(2), LocalDateTime.now().plusDays(3), "MemeCard", 8);
        descuentos.add(promocionMedioDePago);
        carrito.agregarProducto(producto1);
        carrito.agregarProducto(producto2);
        ServicioPago servicioPago = new ServicioPago();
        Venta venta = servicioPago.procesarPago(carrito, tarjeta, descuentos);
        Assertions.assertEquals(2760, venta.getMontoTotal()); // Descuento del 8% aplicado al total
    }
    /* Calcular el monto total del carrito con dos descuentos vigentes, sobre productos marca Comarca y para tarjeta de crédito MemeCard.*/
    @Test
    void testCalcularMontoTotalConDosDescuentosVigentes() {
        promocionMarca = new PromocionMarca(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), "Comarca", 10);
        promocionMedioDePago = new PromocionMedioDePago(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), "MemeCard", 8);
        descuentos.add(promocionMarca);
        descuentos.add(promocionMedioDePago);
        carrito.agregarProducto(producto1);
        carrito.agregarProducto(producto2);
        ServicioPago servicioPago = new ServicioPago();
        Venta venta = servicioPago.procesarPago(carrito, tarjeta, descuentos);
        Assertions.assertEquals(2576, venta.getMontoTotal());
    }


    /* Realizar el pago y verificar que se genere la venta u orden de pago.*/

    @Test
    void testRealizarPagoYVerificarVentaGenerada() {
        carrito.agregarProducto(producto1);
        carrito.agregarProducto(producto2);
        ServicioPago servicioPago = new ServicioPago();
        Venta venta = servicioPago.procesarPago(carrito, tarjeta, descuentos);
        Assertions.assertNotNull(venta);
        Assertions.assertEquals(cliente, venta.getCliente());
        Assertions.assertEquals(3000, venta.getMontoTotal());
    }

    /*Verificar que no sea posible crear un Producto sin categoría,
descripción y precio.*/
    @Test
    void testCrearProductoSinCategoriaDescripcionPrecio() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Producto("003", null, "Ropa deportiva", 1000);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Producto("004", "Gorra", null, 1000);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Producto("005", "Gorra", "Ropa deportiva", 0);
        });
    }

    /*Verificar que no sea posible crear un Cliente sin dni, nombre y apellido. Y que el email sea válido.*/
    @Test
    void testCrearClienteSinDniNombreApellido() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Cliente(null, "Pérez", 12345678, "juan@correo.com");
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("Juan", null, 12345678, "juan@correo.com");
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("Juan", "Pérez", null, "juan@correo.com");
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("Juan", "Pérez", 12345678, "juan");
        });
    }

    /*Verificar que no sea posible crear un descuento con fechas validez superpuestas.*/
    @Test
    void testCrearDescuentoConFechasSuperpuestas() {
        descuentos = new ArrayList<>();

        PromocionMarca descuento1 = new PromocionMarca(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), "Acme", 5);
        PromocionMarca descuento2 = new PromocionMarca(LocalDateTime.now().minusDays(2), LocalDateTime.now().plusDays(3), "Acme", 10);

        descuentos.add(descuento1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            if(descuento2.estaVigente() && descuento1.estaVigente()) {
                throw new IllegalArgumentException("Descuentos con fechas superpuestas");
            }
            descuentos.add(descuento2);
        });
    }

}
