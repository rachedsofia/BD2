package unrn;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente {
    private String nombre;
    private String apellido;
    private Integer dni;
    private String email;
    private List<Tarjeta> tarjetaCred;

    Cliente(String nombre, String apellido, Integer dni, String email) {

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

        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.tarjetaCred = new ArrayList<>();

    }

    private boolean emailValido(String email) {
        return email.contains("@");
    }

    public void agregarTarjeta(Tarjeta unaTarjeta) {
        tarjetaCred.add(unaTarjeta);
    }

    public List<Tarjeta> getTarjeta() {
        return tarjetaCred;
    }

    public Integer getDNI() {
        return dni;
}


}
