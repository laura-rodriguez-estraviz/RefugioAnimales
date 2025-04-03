
package aplicacion;


import gui.FachadaGui;
import baseDatos.FachadaBaseDatos;
import java.util.List; 
/**
 *
 * @author alumnogreibd
 */
public class GestionCitas {
    
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
    public GestionCitas(FachadaGui fgui, FachadaBaseDatos fbd){
        this.fgui=fgui;
        this.fbd=fbd;
    }  
   
     public List<Cita> buscarCitasCliente(String dni) {
        return fbd.buscarCitasCliente(dni);
    }
    
    public int insertarCita(Cita c) {
        return fbd.insertarCita(c);
    }
    
    public int actualizarCita(Cita oldCita, Cita newCita) {
        return fbd.actualizarCita(oldCita, newCita);
    }
    
    public int borrarCita(Cita c) {
        return fbd.borrarCita(c);
    }
    
    public java.util.List<Cita> consultarCitasP(){
        return fbd.consultarCitasP();
    }
    
    public java.util.List<Cita> buscarCitasP(int numSucursal){
        return fbd.buscarCitasP(numSucursal);
    }
   
    public void aceptarCita(Cita c){
        fbd.aceptarCita(c);
    }
    
    public void denegarCita(Cita c){
        fbd.denegarCita(c);
    }
   
   
}
