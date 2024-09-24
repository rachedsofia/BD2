package ar.unrn.tp.web;

import ar.unrn.tp.modelo.Descuento;
import ar.unrn.tp.servicios.JPADescuentoServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/descuentos")
public class DescuentoController {

    private final JPADescuentoServices descuentoService;

    public DescuentoController(JPADescuentoServices descuentoService) {
        this.descuentoService = descuentoService;
    }

    @GetMapping("/")
    public List<Descuento> obtenerDescuentos() {
        return descuentoService.listarDescuentos();
    }
}
