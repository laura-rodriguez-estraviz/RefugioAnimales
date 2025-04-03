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
public class GestionTiendas{
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
    public GestionTiendas(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }
    
    public java.util.List<Producto> obtenerProductosNoTienda(int numSucursal, String Tienda,String nombreProducto){
        return fbd.obtenerProductosNoTienda( numSucursal,  Tienda, nombreProducto);
    }
    
    public void anadirProductoTienda(String producto,String tienda,int numSucursal){
        fbd.anadirProductoTienda(producto,tienda,numSucursal);
    }
    
    public java.util.List<Tienda> obtenerTiendas(String nombreTienda, int numSucursal){
        return fbd.obtenerTiendas(nombreTienda, numSucursal);
    }
    
    public java.util.List<Producto> obtenerProductosTienda(String nombreTienda, int numSucursal, String nombreProducto){
        return fbd.obtenerProductosTienda(nombreTienda,numSucursal,nombreProducto);
    }
    
    public void actualizarTienda(String nombreTiendaActual, String nombreTiendaNuevo, int numSucursal){
        fbd.actualizarTienda( nombreTiendaActual,  nombreTiendaNuevo,  numSucursal);
    }
    public void nuevaTienda(String nombreTienda, int numSucursal){
        fbd.nuevaTienda( nombreTienda,  numSucursal);
    }
    public void eliminarTienda(String nombreTienda, int numSucursal){
        fbd.eliminarTienda( nombreTienda,  numSucursal);
    
    }
    
    public void eliminarProductoTienda(String tienda, String nombreProducto, int numSucursal){
        fbd.eliminarProductoTienda( tienda,  nombreProducto,  numSucursal);
    }
    
    public void actualizarProductoTienda(String tienda, String nombreProducto, int nuevaCantidad, int numSucursal){
        fbd.actualizarProductoTienda( tienda,  nombreProducto,  nuevaCantidad,  numSucursal);
    }

    public void abrirVProductosTienda(String tienda, int numSucursal){
        fgui.abrirVProductosTienda( tienda,  numSucursal);
    }
    
    public void abrirVTiendas(int numSucursal){
        fgui.abrirVTiendas( numSucursal);
    }
    
    public java.util.List<Producto> obtenerProductosNoTiendaConExistencias(int numSucursal, String Tienda,String nombreProducto){
        return fbd.obtenerProductosNoTiendaConExistencias(numSucursal, Tienda, nombreProducto);
    }

    public int comprar(String tienda, String producto, int sucursal, String dni) {
       return fbd.comprar(tienda, producto, sucursal, dni);
    }

}
