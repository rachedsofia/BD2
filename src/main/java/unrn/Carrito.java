package unrn;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    Cliente cliente;
    private List<Producto> misProductos;

    public Carrito(Cliente unCliente) {
        this.cliente = unCliente;
        this.misProductos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        misProductos.add(producto);
    }
  /*  public void eliminarProducto(Producto producto){
        try {
            if (!this.misProductos.isEmpty() && this.misProductos.contains(producto))
                this.misProductos.remove(producto);
        } catch (RuntimeException e){
            throw new RuntimeException("No se pudo quitar el producto");
        }
    }
*/
    public double calcularTotal() {
        double total = 0;
        for (Producto producto : misProductos) {
            total = producto.getPrecio();
        }
        return total;
    }

    public Cliente obtenerCliente() {
        return cliente;
    }

    public List<Producto> obtenerProductos() {
        return misProductos;
    }
}
