/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class GestionEventos {
    
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
   
    public GestionEventos(FachadaGui fgui, FachadaBaseDatos fbd){
        this.fgui=fgui;
        this.fbd=fbd;
    }  
    
    public List<Evento> listarEventosUsuario(String nombre, Boolean participa, Usuario usr) {
        return fbd.listarEventosUsuario(nombre, participa, usr);
    }
    
    public int participar(Usuario usr, Evento ev) {
        return fbd.participar(usr, ev);
    }
    
    public int anularParticipacion(Usuario usr, Evento ev) {
        return fbd.anularParticipacion(usr, ev);
    }
    
    
    
}
