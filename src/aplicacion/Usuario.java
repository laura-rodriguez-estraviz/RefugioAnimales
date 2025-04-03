package aplicacion;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author basesdatos
 */
public class Usuario {
    private String dni;
    private String clave;
    private String nombre;
    private String direccion;
    private String email;
    private int telefono;

   public Usuario (String dni, String clave, String nombre, String direccion, String email,int telefono){
    this.dni=dni;
    this.clave=clave;
    this.nombre=nombre;
    this.direccion=direccion;
    this.email=email;
    this.telefono=telefono;
   }

   public String getDni(){

       return this.dni;
   }

   public String getClave(){

       return this.clave;
   }

   public String getNombre(){

       return this.nombre;
   }

   public String getDireccion(){

       return this.direccion;
   }

   public String getEmail(){

       return this.email;
   }

   public int getTelefono(){

       return this.telefono;
   }
   

}
