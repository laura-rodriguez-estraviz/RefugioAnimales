/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;
import gui.FachadaGui;
import baseDatos.FachadaBaseDatos;
import java.util.List;
/**
 *
 * @author basesdatos
 */
public class GestionSucursales{
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
    public GestionSucursales(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }
    
    
    public List<Sucursal>  obtenerSucursales(String numSucursal, String municipio){
        return fbd.obtenerSucursales(numSucursal,  municipio);
    }
 
    public List<Sucursal> listaSucursales() {
        return fbd.listaSucursales();
    }
 
    public void eliminarSucursal(int numSucursal){
        fbd.eliminarSucursal(numSucursal);
    }

     public void nuevaSucursal(int numSucursal, String municipio){
        fbd.nuevaSucursal(numSucursal, municipio);
    }

    public void actualizarSucursal(int numSucursalViejo, int numSucursalNuevo,String municipio){
        fbd.actualizarSucursal( numSucursalViejo,  numSucursalNuevo, municipio);
    }


}
