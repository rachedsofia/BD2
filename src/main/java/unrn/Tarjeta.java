package unrn;

import java.util.Objects;

public class Tarjeta {
   private boolean tarjetaActiva;
   private Integer saldo;
   private Integer codigo;
    private String marca;


   public Tarjeta(Integer codigo, Integer saldo, String marca){
       Objects.requireNonNull(saldo);

    if ((codigo.toString().length() != 16))
           throw new RuntimeException("El nombre no puede estar vacio.");

       this.codigo = codigo;
       this.saldo = saldo;
       this.marca = marca;
       this.tarjetaActiva = true;
   }
    public boolean tieneFondosSuficientes(double monto) {
        return tarjetaActiva;
    }
   public boolean estaActivada(){
       return tarjetaActiva;
   }

   public void agregarSaldo(Integer saldoNuevo){
       this.saldo += saldoNuevo;
   }

    public String getMarca() {
       return marca;
    }
}
