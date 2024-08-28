package ar.unrn.tp.modelo;

import javax.persistence.*;
import java.util.*;

@Entity
public class Cliente {
    private static Set<String> dnisRegistrados = new HashSet<>();  //PARA REGISTRAR LOS DNI QUE FUERON AGREGADOS Y VALIDAR QUE NO SE REPITA.
    private static Set<Cliente> clientesRegistrados = new HashSet<>(); //se puede?
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Tarjeta> tarjetaCred;

    protected Cliente() {
    }

    public Cliente(String nombre, String apellido, String dni, String email) {

        Objects.requireNonNull(dni);
        Objects.requireNonNull(nombre);
        Objects.requireNonNull(apellido);
        Objects.requireNonNull(email);
        if (nombre.isEmpty())
            throw new RuntimeException("El nombre no puede estar vacio.");

        if (apellido.isEmpty())
            throw new RuntimeException("El apellido no puede esta vacio.");

        if (email.isEmpty())
            throw new RuntimeException("Debe ingresar un mail");

        if (!emailValido(email))
            throw new RuntimeException("Email debe ser valido");

        if (!validarDNIUnico(dni))
            throw new IllegalArgumentException("El DNI ya está registrado para otro cliente");

        dnisRegistrados.add(dni);
        clientesRegistrados.add(this);
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.tarjetaCred = new ArrayList<>();

    }

    public static boolean clienteExistente(String dni) {
        return clientesRegistrados.stream().anyMatch(cliente -> cliente.dni.equals(dni));
    }

    private static Set<String> getDnisRegistrados() {
        return dnisRegistrados;
    }

    private static void setDnisRegistrados(Set<String> dnisRegistrados) {
        Cliente.dnisRegistrados = dnisRegistrados;
    }

    private boolean emailValido(String email) {
        return email.contains("@");
    }

    public void agregarTarjeta(Tarjeta unaTarjeta) {
        tarjetaCred.add(unaTarjeta);
    }

    private List<Tarjeta> getTarjeta() {
        return tarjetaCred;
    }

    private String getDNI() {
        return dni;
    }

    private boolean validarDNIUnico(String dni) {
        return !dnisRegistrados.contains(dni);
    }

    private String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    private String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<Tarjeta> getTarjetas() {
        return tarjetaCred;
    }

    public void setTarjetaCred(List<Tarjeta> tarjetaCred) {
        this.tarjetaCred = tarjetaCred;
    }

}
