/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;
import gui.FachadaGui;
import baseDatos.FachadaBaseDatos;
/**
 *
 * @author basesdatos
 */
public class GestionUsuarios {
     
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
   
    public GestionUsuarios(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }  
    
    
  public Usuario comprobarAutentificacion(String dni, String clave){
      Usuario u;

      u=fbd.validarUsuario(dni, clave);
      return u;
  }
  
   public java.util.List<Usuario> buscarUsuarios(String dni, String nombre){
     return fbd.buscarUsuarios(dni, nombre);
 }
 
    public int eliminarUsuario(String dni){
        return fbd.eliminarUsuario(dni);
    }
  
  
  public void abrirVentanaPrincipal( Usuario usuario){
    fgui.abrirVentanaPrincipal(usuario);
}
  
  public void nuevoUsuario(Usuario u){
        
        fbd.nuevoUsuario(u);
    }
  
   public void actualizarUsuario(String s, Usuario u){
        fbd.actualizarUsuario(s,u);
    }
  
  
 
  
}
