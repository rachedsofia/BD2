package unrn;

public class Tarjeta {
   private boolean tarjetaActiva;
   private Integer saldo;
   private Integer codigo;
    private String marca;


   Tarjeta(Integer codigo, Integer saldo, String marca){
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

   public Integer agregarSaldo(Integer saldoNuevo){

       return this.saldo += saldoNuevo;
   }


    public String getMarca() {
       return marca;
    }
}
