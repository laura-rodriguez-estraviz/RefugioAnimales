/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import gui.VNuevaTareaV;
import java.util.List;


/**
 *
 * @author basesdatos
 */
public class FachadaAplicacion {
    gui.FachadaGui fgui;
    baseDatos.FachadaBaseDatos fbd;
    GestionTiendas ctiendas;
    GestionUsuarios cu;
    GestionTareas ctareas;
    GestionCitas cc;
    GestionSucursales cs;
    GestionEventos ce;

 
 public FachadaAplicacion(){
   fgui=new gui.FachadaGui(this);
   fbd= new baseDatos.FachadaBaseDatos(this);
   ctiendas= new GestionTiendas(fgui, fbd);
   cu= new GestionUsuarios(fgui, fbd);
   ctareas=new GestionTareas(fgui, fbd);
   cc=new GestionCitas(fgui, fbd);
   cs=new GestionSucursales(fgui,fbd);
   ce=new GestionEventos(fgui,fbd);
 }

 public static void main(String args[]) {
     FachadaAplicacion fa;
     
     fa= new FachadaAplicacion();
     fa.iniciaInterfazUsuario();
 }
 
 public void iniciaInterfazUsuario(){
     fgui.iniciaVista();
 }

 public void muestraExcepcion(String e){
     fgui.muestraExcepcion(e);
 }


public Usuario comprobarAutentificacion(String dni, String clave){
    Usuario u;
    u=cu.comprobarAutentificacion(dni, clave);
  return u;
}

 public java.util.List<Sucursal>  obtenerSucursales(String numSucursal, String municipio){
     return cs.obtenerSucursales( numSucursal,  municipio);
 }
 
 public void nuevaSucursal(int numSucursal, String municipio){
     cs.nuevaSucursal(numSucursal, municipio);
 }
 
 public void actualizarSucursal(int numSucursalViejo, int numSucursalNuevo,String municipio){
        cs.actualizarSucursal( numSucursalViejo,  numSucursalNuevo, municipio);
    }
 
 public void eliminarSucursal(int numSucursal){
        cs.eliminarSucursal(numSucursal);
    }
 
  public java.util.List<Usuario> buscarUsuarios(String dni, String nombre){
     return cu.buscarUsuarios(dni, nombre);
 }
 
 public int eliminarUsuario(String dni){
        return cu.eliminarUsuario(dni);
    }
 
public java.util.List<Producto> obtenerProductosNoTienda(int numSucursal, String Tienda,String nombreProducto){
    return ctiendas.obtenerProductosNoTienda( numSucursal,  Tienda, nombreProducto);
}

 public void anadirProductoTienda(String producto,String tienda,int numSucursal){
      ctiendas.anadirProductoTienda(producto,tienda,numSucursal);
 }
 
public java.util.List<Tienda> obtenerTiendas(String nombreTienda, int numSucursal){
    return ctiendas.obtenerTiendas(nombreTienda, numSucursal);
}

public java.util.List<Producto> obtenerProductosTienda(String nombreTienda, int numSucursal, String nombreProducto){
    return ctiendas.obtenerProductosTienda(nombreTienda,numSucursal,nombreProducto);
}

public void actualizarTienda(String nombreTiendaActual, String nombreTiendaNuevo, int numSucursal){
    ctiendas.actualizarTienda( nombreTiendaActual,  nombreTiendaNuevo,  numSucursal);
}
public void nuevaTienda(String nombreTienda, int numSucursal){
    ctiendas.nuevaTienda( nombreTienda,  numSucursal);
}
public void eliminarTienda(String nombreTienda, int numSucursal){
    ctiendas.eliminarTienda( nombreTienda,  numSucursal);
    
}

public void eliminarProductoTienda(String tienda, String nombreProducto, int numSucursal){
    ctiendas.eliminarProductoTienda( tienda,  nombreProducto,  numSucursal);
}

public void actualizarProductoTienda(String tienda, String nombreProducto, int nuevaCantidad, int numSucursal){
    ctiendas.actualizarProductoTienda( tienda,  nombreProducto,  nuevaCantidad,  numSucursal);
}

public void abrirVProductosTienda(String tienda, int numSucursal){
    ctiendas.abrirVProductosTienda( tienda,  numSucursal);
}

public void abrirVTiendas(int numSucursal){
    ctiendas.abrirVTiendas(numSucursal);
}

public void abrirVentanaPrincipal( Usuario usuario){
    cu.abrirVentanaPrincipal(usuario);
}

  public List<Cita> buscarCitasCliente(String dni) {
        return cc.buscarCitasCliente(dni);
    }
    
    public int insertarCita(Cita c) {
        return cc.insertarCita(c);
    }
    
    public int actualizarCita(Cita oldCita, Cita newCita) {
        return cc.actualizarCita(oldCita, newCita);
    }
    
    public int borrarCita(Cita c) {
        return cc.borrarCita(c);
    }
   
    public List<Evento> listarEventosUsuario(String nombre, Boolean participa, Usuario usr) {
        return ce.listarEventosUsuario(nombre, participa, usr);
    }
    
    public int participar(Usuario usr, Evento ev) {
        return ce.participar(usr, ev);
    }
    
    public int anularParticipacion(Usuario usr, Evento ev) {
        return ce.anularParticipacion(usr, ev);
    }
    
    
    public List<Sucursal> listaSucursales() {
        return cs.listaSucursales();
    }
    
    public void nuevoUsuario(Usuario u){
        
        cu.nuevoUsuario(u);
    }
    
     public void aceptarTarea(Voluntario v,Tarea t){
        ctareas.aceptarTarea(v,t);
    }
    
    public List<Tarea> consultarTareasVoluntario(Voluntario v){
        return ctareas.consultarTareasVoluntario( v);
    }
    
    public List<Tarea> consultarTareasLibres(){
        return ctareas.consultarTareasLibres();
    
}
    
    public List<Cita> consultarCitasP(){
        return cc.consultarCitasP();
    }
    
    
    
    public java.util.List<Cita> buscarCitasP(int numSucursal){
        return cc.buscarCitasP(numSucursal);
    }
   
    public void aceptarCita(Cita c){
        cc.aceptarCita(c);
    }
    
    public void denegarCita(Cita c){
        cc.denegarCita(c);
    }
    
    
     public void nuevaTareaVoluntarios(javax.swing.JFrame padre,Voluntario v){
        ctareas.nuevaTareaVoluntarios(padre,v);
    }
    
    public void ficharTarea(Tarea tarea, Voluntario voluntario){
        ctareas.ficharTarea(tarea, voluntario);
    }
    
    public void cancelarTarea(Tarea tarea, Voluntario voluntario){
        ctareas.cancelarTarea(tarea, voluntario);
    }
    
    
    public java.util.List<Producto> obtenerProductosNoTiendaConExistencias(int numSucursal, String Tienda,String nombreProducto){
        return ctiendas.obtenerProductosNoTiendaConExistencias(numSucursal, Tienda, nombreProducto);
    }

    public int comprar(String tienda, String producto, int sucursal, String dni) {
       return ctiendas.comprar(tienda, producto, sucursal, dni);
    }
    
    public void actualizarUsuario(String s, Usuario u){
        cu.actualizarUsuario(s,u);
    }


}
