package aplicacion;

public class Cliente extends Usuario {
    private int puntuacion;

    public Cliente(String dni, String clave, String nombre, String direccion, String email, int telefono, int puntuacion) {
        super(dni, clave, nombre, direccion, email, telefono);
        this.puntuacion = puntuacion;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}