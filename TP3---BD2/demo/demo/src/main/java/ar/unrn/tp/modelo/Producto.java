package ar.unrn.tp.modelo;


import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String descripcion;
    private String miCategoria;
    private double precio;
    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    protected Producto() {
    }

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

   /* public String getDescripcion() {
        return descripcion;
    }*/

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return miCategoria;
    }

    public boolean containsMarca(String marca) {
        return this.descripcion.contains(marca);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMiCategoria() {
        return miCategoria;
    }

    public void setMiCategoria(String miCategoria) {
        this.miCategoria = miCategoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }
}
