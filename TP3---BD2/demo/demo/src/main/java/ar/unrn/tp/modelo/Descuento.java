package ar.unrn.tp.modelo;

import jakarta.persistence.DiscriminatorColumn;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@DiscriminatorColumn(name = "tipo_descuento")
@Entity
public abstract class Descuento {
    protected LocalDate fechaInicio;
    protected LocalDate fechaFin;
    private boolean activo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Descuento(LocalDate fechaInicio, LocalDate fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public boolean estaVigente() {
        LocalDate fechaActual = LocalDate.now();
        activo = false;
        if (fechaActual.isAfter(fechaInicio) && fechaActual.isBefore(fechaFin)) {
            activo = true;
        }
        ;
        return activo;
    }


    public abstract double aplicar(Producto producto, double precioProducto);

}