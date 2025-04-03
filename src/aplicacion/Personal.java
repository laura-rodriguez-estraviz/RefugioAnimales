package aplicacion;
import java.sql.Time;
public class Personal extends Usuario {
    private String cargo;
    private Time horaEntrada;
    private Time horaSalida;
    private String cuentaBancaria;

    public Personal(String dni, String clave, String nombre, String direccion, String email, int telefono,
                    String cargo, Time horaEntrada, Time horaSalida, String cuentaBancaria) {
        super(dni, clave, nombre, direccion, email, telefono);
        this.cargo = cargo;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.cuentaBancaria = cuentaBancaria;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

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

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }
}
