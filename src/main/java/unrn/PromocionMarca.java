package unrn;

import java.time.LocalDateTime;

public class PromocionMarca extends Descuento {
    private String marca;
    private double porcentaje;

    public PromocionMarca(LocalDateTime fechaInicio, LocalDateTime fechaFin, String marca, double porcentaje) {
        super(fechaInicio, fechaFin);
        this.marca = marca;
        this.porcentaje = porcentaje;
    }

    @Override
    public double aplicar(Producto producto, double precioProducto) {
        if (estaVigente() && producto.getDescripcion().contains(marca)) {
            return precioProducto * (porcentaje / 100);

        }
        return 0;
    }

    public String getMarca(){
        return marca;
    }
}
