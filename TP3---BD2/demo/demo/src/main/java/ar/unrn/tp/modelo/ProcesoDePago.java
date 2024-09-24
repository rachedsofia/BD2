package ar.unrn.tp.modelo;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProcesoDePago {

    public Venta procesarPago(Carrito carrito, Tarjeta tarjeta, List<Descuento> descuentos) {
        double total = 0;

        for (Producto producto : carrito.obtenerProductos()) {
            double precioProducto = producto.getPrecio();
            for (Descuento descuento : descuentos) {
                if ((descuento instanceof PromocionMarca promocionMarca)) {
                    precioProducto -= promocionMarca.aplicar(producto, precioProducto);
                }
            }
            total += precioProducto;
        }

        double descuentoMedioDePago = 0;
        for (Descuento descuento : descuentos) {
            if (descuento instanceof PromocionMedioDePago) {
                descuentoMedioDePago = ((PromocionMedioDePago) descuento).aplicarDescuento(total, tarjeta.getMarca());
            }
        }

        total -= descuentoMedioDePago;
        if (!tarjeta.tieneFondosSuficientes(total)) {
            throw new RuntimeException("Fondos insuficientes");
        }
        Venta venta = new Venta(carrito.obtenerCliente(), carrito.obtenerProductos(), total);
        return venta;
    }
}
