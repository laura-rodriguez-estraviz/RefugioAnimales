/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

/**
 *
 * @author alumnogreibd
 */
public class Tarea {
    private int id;
    private String descripcion;
    private String fechaInscripcion;

    public Tarea(int id, String descripcion, String fechaInscripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaInscripcion = fechaInscripcion;
    }

    public Tarea(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }
    
    
    
    
}
