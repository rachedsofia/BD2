package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Venta {
    @Id
    @GeneratedValue
    private Long Id;
    private LocalDate fechaYHora;
    private Cliente cliente;
    private List<Producto> misProductos;
    private double montoTotal;

    public Venta(Cliente cliente, List<Producto> productoSelecc, double montoTotal) {
        this.fechaYHora = LocalDate.now();
        this.cliente = cliente;
        this.misProductos = productoSelecc;
        this.montoTotal = montoTotal;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getMisProductos() {
        return misProductos;
    }

    public void setMisProductos(List<Producto> misProductos) {
        this.misProductos = misProductos;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDate getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDate fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}
