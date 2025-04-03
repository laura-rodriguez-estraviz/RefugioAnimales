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
public class GestionTareas {
    
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
   
    public GestionTareas(FachadaGui fgui, FachadaBaseDatos fbd){
        this.fgui=fgui;
        this.fbd=fbd;
    }  
    
    
     public java.util.List<Tarea> consultarTareasVoluntario(Voluntario v){
        return fbd.consultarTareasVoluntario(v);
    }
    
    public java.util.List<Tarea> consultarTareasLibres(){
        return fbd.consultarTareasLibres();
    }
    
    public void ficharTarea(Tarea tarea, Voluntario voluntario){
        fbd.ficharTarea(tarea, voluntario);
    }
    
    public void cancelarTarea(Tarea tarea, Voluntario voluntario){
        fbd.cancelarTarea(tarea, voluntario);
    }
    
    public void aceptarTarea(Voluntario voluntario, Tarea tarea){
        fbd.aceptarTarea(voluntario, tarea);
    }
    
     public void nuevaTareaVoluntarios(javax.swing.JFrame padre,Voluntario v){
        fgui.nuevaTareaVoluntarios(padre,v);
    }
    
   

    
    
}
