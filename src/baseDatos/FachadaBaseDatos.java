/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;

import aplicacion.Cita;
import aplicacion.Evento;
import aplicacion.Producto;
import aplicacion.Sucursal;
import aplicacion.Tarea;
import aplicacion.Tienda;
import aplicacion.Usuario;
import aplicacion.Voluntario;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author basesdatos
 */
public class FachadaBaseDatos {
    private aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOUsuarios daoUsuarios;
    private DAOSucursales daoSucursales;
    private DAOTiendas daoTiendas;
    private DAOEventos daoEventos;
    private DAOCitas daoCitas;
    private DAOTareas daoTareas;

    public FachadaBaseDatos (aplicacion.FachadaAplicacion fa){
        
        Properties configuracion = new Properties();
        this.fa=fa;
        FileInputStream arqConfiguracion;

        try {
            arqConfiguracion = new FileInputStream("baseDatos.properties");
            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            Properties usuario = new Properties();
     

            String gestor = configuracion.getProperty("gestor");

            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));
            this.conexion=java.sql.DriverManager.getConnection("jdbc:"+gestor+"://"+
                    configuracion.getProperty("servidor")+":"+
                    configuracion.getProperty("puerto")+"/"+
                    configuracion.getProperty("baseDatos"),
                    usuario);

            daoUsuarios = new DAOUsuarios(conexion, fa);
            daoSucursales = new DAOSucursales(conexion, fa);
            daoTiendas = new DAOTiendas(conexion,fa);
            daoEventos=new DAOEventos(conexion, fa);
            daoCitas=new DAOCitas(conexion, fa);
            daoTareas=new DAOTareas(conexion,fa);


        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
            fa.muestraExcepcion(f.getMessage());
        } catch (IOException i){
            System.out.println(i.getMessage());
            fa.muestraExcepcion(i.getMessage());
        } 
        catch (java.sql.SQLException e){
            System.out.println(e.getMessage());
            fa.muestraExcepcion(e.getMessage());
        }

        
    }
    

    public Usuario validarUsuario(String dni, String clave){
        return daoUsuarios.validarUsuario(dni, clave);
    }
    
    public java.util.List<Sucursal>  obtenerSucursales(String numSucursal, String municipio){
        return daoSucursales.obtenerSucursales( numSucursal,  municipio);
    }
    
     public void eliminarSucursal(int numSucursal){
        daoSucursales.eliminarSucursal(numSucursal);
    }
     
    public void nuevaSucursal(int numSucursal, String municipio){
        daoSucursales.nuevaSucursal(numSucursal, municipio);
    }
 
    public void actualizarSucursal(int numSucursalViejo, int numSucursalNuevo,String municipio){
        daoSucursales.actualizarSucursal( numSucursalViejo,  numSucursalNuevo, municipio);
    }
    
    
    public java.util.List<Usuario> buscarUsuarios(String dni, String nombre){
        return daoUsuarios.buscarUsuarios(dni, nombre);
    }
    
    public int eliminarUsuario(String dni){
        return daoUsuarios.eliminarUsuario(dni);
    }
 
    public java.util.List<Producto> obtenerProductosNoTienda(int numSucursal, String Tienda,String nombreProducto){
        return daoTiendas.obtenerProductosNoTienda( numSucursal,  Tienda, nombreProducto);
    }
    
    public void anadirProductoTienda(String producto,String tienda,int numSucursal){
        daoTiendas.anadirProductoTienda(producto,tienda,numSucursal);
    }
    
    public java.util.List<Tienda> obtenerTiendas(String nombreTienda, int numSucursal){
        return daoTiendas.obtenerTiendas(nombreTienda, numSucursal);
    }
   
    public java.util.List<Producto> obtenerProductosTienda(String nombreTienda, int numSucursal, String nombreProducto){
        return daoTiendas.obtenerProductosTienda(nombreTienda,numSucursal,nombreProducto);
    }
    
    public void actualizarTienda(String nombreTiendaActual, String nombreTiendaNuevo, int numSucursal){
        daoTiendas.actualizarTienda( nombreTiendaActual,  nombreTiendaNuevo,  numSucursal);
    }
    public void nuevaTienda(String nombreTienda, int numSucursal){
        daoTiendas.nuevaTienda( nombreTienda,  numSucursal);
    }
    
    public void eliminarTienda(String nombreTienda, int numSucursal){
        daoTiendas.eliminarTienda( nombreTienda,  numSucursal);

    }
    
    public void eliminarProductoTienda(String tienda, String nombreProducto, int numSucursal){
        daoTiendas.eliminarProductoTienda( tienda,  nombreProducto,  numSucursal);
    }
    
     public void actualizarProductoTienda(String tienda, String nombreProducto, int nuevaCantidad, int numSucursal){
        daoTiendas.actualizarProductoTienda( tienda,  nombreProducto,  nuevaCantidad,  numSucursal);
    }
     
    public List<Sucursal> listarSucursales() {
        return daoSucursales.listaSucursales();
    }
    
    
     public List<Evento> listarEventosUsuario(String nombre, Boolean participa, Usuario usr) {
        return daoEventos.listarEventosUsuario(nombre, participa, usr);
    }
    
    public int participar(Usuario usr, Evento ev) {
        return daoEventos.participar(usr, ev);
    }
    
    public int anularParticipacion(Usuario usr, Evento ev) {
        return daoEventos.anularParticipacion(usr, ev);
    }
    
    
     public List<Cita> buscarCitasCliente(String dni) {
        return daoCitas.buscarCitasCliente(dni);
    }
    
    public int insertarCita(Cita c) {
        return daoCitas.insertarCita(c);
    }
    
    public int actualizarCita(Cita oldCita, Cita newCita) {
        return daoCitas.actualizarCita(oldCita, newCita);
    }
    
    public int borrarCita(Cita c) {
        return daoCitas.borrarCita(c);
    }
    
    public List<Sucursal> listaSucursales() {
        return daoSucursales.listaSucursales();
    }
    
    public void nuevoUsuario(Usuario u){
        daoUsuarios.nuevoUsuario(u);
    }
    
    public void aceptarTarea(Voluntario v,Tarea t){
        daoTareas.aceptarTarea(v,t);
    }
    
    public List<Tarea> consultarTareasVoluntario(Voluntario v){
        return daoTareas.consultarTareasVoluntario( v);
    }
    
     public java.util.List<Tarea> consultarTareasLibres(){
        return daoTareas.consultarTareasLibres();
    }
    
    public void ficharTarea(Tarea tarea, Voluntario voluntario){
        daoTareas.ficharTarea(tarea, voluntario);
    }
    
    public void cancelarTarea(Tarea tarea, Voluntario voluntario){
        daoTareas.cancelarTarea(tarea, voluntario);
    }
    
    
    
     public java.util.List<Cita> consultarCitasP(){
        return daoCitas.consultarCitasP();
    }
    
    public java.util.List<Cita> buscarCitasP(int numSucursal){
        return daoCitas.buscarCitasP(numSucursal);
    }
   
    public void aceptarCita(Cita c){
        daoCitas.aceptarCita(c);
    }
    
    public void denegarCita(Cita c){
        daoCitas.denegarCita(c);
    }
    
    public java.util.List<Producto> obtenerProductosNoTiendaConExistencias(int numSucursal, String Tienda,String nombreProducto){
        return daoTiendas.obtenerProductosNoTiendaConExistencias(numSucursal, Tienda, nombreProducto);
    }

    public int comprar(String tienda, String producto, int sucursal, String dni) {
       return daoTiendas.comprar(tienda, producto, sucursal, dni);
    }
    
     public void actualizarUsuario(String s, Usuario u){
        daoUsuarios.actualizarUsuario(s,u);
    }


}
