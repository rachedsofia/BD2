package ar.unrn.tp.web;

import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.servicios.JPAProductoServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final JPAProductoServices productoService;

    public ProductoController(JPAProductoServices productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoService.listarProductos();
    }
}
