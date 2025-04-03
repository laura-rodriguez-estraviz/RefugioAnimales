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
public class Cita {
    private Date fechaSolicitud;
    private Date fechaCita;
    private Boolean cancelado;
    private Sucursal sucursal;
    private String dni;

    public Cita(Cita c) {
        this.fechaSolicitud = c.getFechaSolicitud();
        this.fechaCita = c.getFechaCita();
        this.cancelado = c.isCancelado();
        this.sucursal = new Sucursal(c.getSucursal().getNumSucursal(), c.getSucursal().getMunicipio());
        this.dni = c.getDni();
    }
    
    public Cita(Date fechaSolicitud, Date fechaCita, Boolean cancelado, Sucursal sucursal, String dni) {
        this.fechaSolicitud = fechaSolicitud;
        this.fechaCita = fechaCita;
        this.cancelado = cancelado;
        this.sucursal = sucursal;
        this.dni = dni;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    
    
}
