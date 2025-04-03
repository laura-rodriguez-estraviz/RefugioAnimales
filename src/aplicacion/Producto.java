/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

/**
 *
 * @author alumnogreibd
 */
public class Producto {
    private String nombre;
    private String descripcion;
    private float precio;
    private int unidades;

    public Producto(String nombre, String descripcion, float precio, int unidades) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidades=unidades;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public int getUnidades() {
        return unidades;
    }
    
    
    
    
    
    
    
}
