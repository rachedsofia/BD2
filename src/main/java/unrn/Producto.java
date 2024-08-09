package unrn;

public class Producto {
        private String codigo;
        private String descripcion;
        private String miCategoria;
        private double precio;

        public Producto(String codigo, String descripcion, String categoria, double precio) {
            if (codigo == null || descripcion == null || categoria == null || precio <= 0) {
                throw new IllegalArgumentException("Datos de producto inválidos");
            }
            this.codigo = codigo;
            this.descripcion = descripcion;
            this.miCategoria = categoria;
            this.precio = precio;
        }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoria() {
        return miCategoria;
    }
}
