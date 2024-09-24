package ar.unrn.tp.web;

import ar.unrn.tp.servicios.JPAVentasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final JPAVentasService ventasService;

    public VentaController(JPAVentasService ventasService) {
        this.ventasService = ventasService;
    }

    @PostMapping("/calcular-monto")
    public double calcularMonto(@RequestBody VentaRequest request) {
        return ventasService.calcularMonto(request.getProductos(), request.getIdTarjeta());
    }

    /* PREGUNTAR SI EN VENTASERVICE TIENE QUE RETORNAR VENTA O VOID */
   /* @PostMapping("/realizar-compra")
    public void realizarCompra(@RequestBody VentaRequest request) {
        ventasService.realizarVenta(request.getIdCliente(), request.getProductos(), request.getIdTarjeta());
    }*/
    @PostMapping("/realizar-compra")
    public ResponseEntity<String> realizarCompra(@RequestBody VentaRequest request) {
        ventasService.realizarVenta(request.getIdCliente(), request.getProductos(), request.getIdTarjeta());
        return ResponseEntity.ok("Compra realizada con éxito");
    }


    // Clase interna para manejar los requests
    public static class VentaRequest {
        private Long idCliente;
        private List<Long> productos;
        private Long idTarjeta;

        // Getters y Setters

        public Long getIdCliente() {
            return idCliente;
        }

        public void setIdCliente(Long idCliente) {
            this.idCliente = idCliente;
        }

        public List<Long> getProductos() {
            return productos;
        }

        public void setProductos(List<Long> productos) {
            this.productos = productos;
        }

        public Long getIdTarjeta() {
            return idTarjeta;
        }

        public void setIdTarjeta(Long idTarjeta) {
            this.idTarjeta = idTarjeta;
        }
    }
}
