package unrn;

import java.time.LocalDate;
import java.util.List;

public class Venta {
    private LocalDate fechaYHora;
    private Cliente cliente;
    private List<Producto> misProductos;
    private double montoTotal;

    Venta(Cliente cliente, List<Producto> productoSelecc, double montoTotal) {
        this.fechaYHora = LocalDate.now();
        this.cliente = cliente;
        this.misProductos = productoSelecc;
        this.montoTotal = montoTotal;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
