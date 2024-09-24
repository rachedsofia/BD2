package ar.unrn.tp.modelo;

import jakarta.persistence.DiscriminatorValue;

import java.time.LocalDate;

@DiscriminatorValue("MARCA")
public class PromocionMarca extends Descuento {
    private String marca;
    private double porcentaje;

    public PromocionMarca(LocalDate fechaInicio, LocalDate fechaFin, String marca, double porcentaje) {
        super(fechaInicio, fechaFin);
        this.marca = marca;
        this.porcentaje = porcentaje;
    }

    @Override
    public double aplicar(Producto producto, double precioProducto) {
        if (estaVigente() && producto.containsMarca(marca)) { //producto.getDescripcion().contains(marca)
            return precioProducto * (porcentaje / 100);

        }
        return 0;
    }

    public String getMarca() {
        return marca;
    }
}
