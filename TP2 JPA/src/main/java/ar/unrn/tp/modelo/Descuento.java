package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public abstract class Descuento {
    protected LocalDate fechaInicio;
    protected LocalDate fechaFin;
    private boolean activo = true;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Descuento(LocalDate fechaInicio, LocalDate fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public boolean estaVigente() {
        LocalDate fechaActual = LocalDate.now();
        return fechaActual.isAfter(fechaInicio) && fechaActual.isBefore(fechaFin);
    }


    public abstract double aplicar(Producto producto, double precioProducto);

}