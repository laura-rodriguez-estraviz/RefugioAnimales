/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import java.sql.Date;

/**
 *
 * @author alumnogreibd
 */
public class Evento {
    private String nombre;
    private String descripcion;
    private java.sql.Date fecha;
    private Boolean participacionActual;
    //sucursal

    public Evento(String nombre, String descripcion, Date fecha, Boolean participacionActual) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.participacionActual = participacionActual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getParticipacionActual() {
        return participacionActual;
    }

    public void setParticipacionActual(Boolean participacionActual) {
        this.participacionActual = participacionActual;
    }
     
}
