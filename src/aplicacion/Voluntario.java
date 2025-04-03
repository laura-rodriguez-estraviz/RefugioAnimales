package aplicacion;
import java.sql.Time;
public class Voluntario extends Usuario {
    private Time horaEntrada;
    private Time horaSalida;

    // Constructor
    public Voluntario(String dni, String clave, String nombre, String direccion, String email, int telefono,
                      Time horaEntrada, Time horaSalida) {
        super(dni, clave, nombre, direccion, email, telefono);
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    // Getters y setters
    public Time getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Time horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }
}