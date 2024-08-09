package unrn;

import java.time.LocalDateTime;

public abstract class Descuento {
    protected LocalDateTime fechaInicio;
    protected LocalDateTime fechaFin;

    public Descuento(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public boolean estaVigente() {
        LocalDateTime fechaActual = LocalDateTime.now();
        return fechaActual.isAfter(fechaInicio) && fechaActual.isBefore(fechaFin);
    }
    public abstract double aplicar(Producto producto, double precioProducto);

}