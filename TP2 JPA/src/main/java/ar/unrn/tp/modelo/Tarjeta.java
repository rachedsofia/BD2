package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Tarjeta {
    @Id
    @GeneratedValue
    private Long Id;
    private boolean tarjetaActiva;
    private Integer saldo;
    private String codigo;
    private Integer codigo1;
    private String marca;

    protected Tarjeta() {
    }

    public Tarjeta(String codigo, String marca) {
        if ((codigo.length() != 16))
            throw new RuntimeException("El nombre no puede estar vacio.");

        this.codigo = codigo;
        this.marca = marca;
        this.tarjetaActiva = true;
    }

    public Tarjeta(Integer codigo, Integer saldo, String marca) {
        Objects.requireNonNull(saldo);
        System.out.print(codigo.toString().length());
        //  if ((codigo.toString().length() != 16))
        //     throw new RuntimeException("El nombre no puede estar vacio.");

        this.codigo1 = codigo;
        this.saldo = saldo;
        this.marca = marca;
        this.tarjetaActiva = true;
    }

    public boolean tieneFondosSuficientes(double monto) {
        return tarjetaActiva;
    }

    public boolean estaActivada() {
        return tarjetaActiva;
    }

    public void agregarSaldo(Integer saldoNuevo) {
        this.saldo += saldoNuevo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getCodigo1() {
        return codigo1;
    }

    public void setCodigo1(Integer codigo1) {
        this.codigo1 = codigo1;
    }

    public boolean isTarjetaActiva() {
        return tarjetaActiva;
    }

    public void setTarjetaActiva(boolean tarjetaActiva) {
        this.tarjetaActiva = tarjetaActiva;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
