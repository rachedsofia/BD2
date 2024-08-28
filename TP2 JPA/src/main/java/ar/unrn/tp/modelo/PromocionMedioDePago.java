package ar.unrn.tp.modelo;

import java.time.LocalDate;

public class PromocionMedioDePago extends Descuento {
    private String medioDePago;
    private double porcentaje;

    public PromocionMedioDePago(LocalDate fechaInicio, LocalDate fechaFin, String medioDePago, double porcentaje) {
        super(fechaInicio, fechaFin);
        this.medioDePago = medioDePago;
        this.porcentaje = porcentaje;
    }

    @Override
    public double aplicar(Producto producto, double precioProducto) {
        return 0;
    }

    public double aplicarDescuento(double total, String tarjeta) {
        if (estaVigente() && (tarjeta == medioDePago)) {
            return total * (porcentaje / 100);
        }
        return 0;
    }
}
