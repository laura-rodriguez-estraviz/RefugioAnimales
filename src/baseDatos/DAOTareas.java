/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import java.sql.*;
import aplicacion.Tarea;
import aplicacion.Voluntario;
/**
 *
 * @author alumnogreibd
 */
public class DAOTareas extends AbstractDAO{
    
    public DAOTareas (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public java.util.List<Tarea> consultarTareasVoluntario(Voluntario voluntario){
        java.util.List<Tarea> resultado = new java.util.ArrayList<>();
        Connection con;
        PreparedStatement stmTareas=null;
        ResultSet rsTareas;
        Tarea tareaActual;
        
        con=this.getConexion();
        
        try{
            stmTareas = con.prepareStatement("select id, descripcion, fechaInscripcion "+
                                "from realizarTarea rt, tarea t "+
                                "where t.id = rt.idTarea "+
                                "and rt.dniVoluntario = ? "+
                                "and fechaFin is null");
            stmTareas.setString(1, voluntario.getDni());
            rsTareas = stmTareas.executeQuery();
            
            while(rsTareas.next()){
                tareaActual = new Tarea(rsTareas.getInt("id"),rsTareas.getString("descripcion"), rsTareas.getString("fechaInscripcion"));
                resultado.add(tareaActual);
            }
        }catch(SQLException e){
              System.out.println(e.getMessage());
              this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try{stmTareas.close();}catch(SQLException e){System.out.println("Imposible cerrar cursores");};
              
        }
        return resultado;
    }
    
    public java.util.List<Tarea> consultarTareasLibres(){
        java.util.List<Tarea> resultado = new java.util.ArrayList<>();
        Connection con;
        PreparedStatement stmTareas=null;
        ResultSet rsTareas;
        Tarea tareaActual;
        
        con=this.getConexion();
        
        try{
            stmTareas = con.prepareStatement("select distinct id, descripcion "+
                                "from tarea t left outer join realizarTarea rt on t.id = rt.idTarea "+
                                "except "+
                                "select distinct id, descripcion "+
                                "from tarea t left outer join realizarTarea rt on t.id = rt.idTarea "+
                                "where fechaInscripcion is not null and fechaFin is null");
            rsTareas = stmTareas.executeQuery();
            
            while(rsTareas.next()){
                tareaActual = new Tarea(rsTareas.getInt("id"),rsTareas.getString("descripcion"));
                resultado.add(tareaActual);
            }
        }catch(SQLException e){
              System.out.println(e.getMessage());
              this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try{stmTareas.close();}catch(SQLException e){System.out.println("Imposible cerrar cursores");};
              
        }
        return resultado;
    }
    
    public void ficharTarea(Tarea tarea, Voluntario voluntario){
        Connection con;
        PreparedStatement stmTarea=null;
        Boolean rsTarea;
        
        con=this.getConexion();
        
        try{
            stmTarea = con.prepareStatement("update realizarTarea "+
                                "set fechaFin = current_date "+
                                "where idTarea = ? "+
                                "and dniVoluntario = ?"+
                                "and fechaInscripcion = cast(? as date)");
            stmTarea.setInt(1, tarea.getId());
            stmTarea.setString(2, voluntario.getDni());
            stmTarea.setString(3, tarea.getFechaInscripcion());
            rsTarea = stmTarea.execute();
            if(rsTarea){
              this.getFachadaAplicacion().muestraExcepcion("Error al fichar");
            }
        }catch(SQLException e){
              System.out.println(e.getMessage());
              this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try{stmTarea.close();}catch(SQLException e){System.out.println("Imposible cerrar cursores");};
              
        }
    }
    
    public void cancelarTarea(Tarea tarea, Voluntario voluntario){
        Connection con;
        PreparedStatement stmTarea=null;
        Boolean rsTarea;
        
        con=this.getConexion();
        
        try{
            stmTarea = con.prepareStatement("delete from realizarTarea "+
                                "where idTarea = ? "+
                                "and dniVoluntario = ?"+
                                "and fechaInscripcion = cast(? as date)");
            stmTarea.setInt(1, tarea.getId());
            stmTarea.setString(2, voluntario.getDni());
            stmTarea.setString(3, tarea.getFechaInscripcion());
            rsTarea = stmTarea.execute();
            if(rsTarea){
              this.getFachadaAplicacion().muestraExcepcion("Error al cancelar");
            }
        }catch(SQLException e){
              System.out.println(e.getMessage());
              this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try{stmTarea.close();}catch(SQLException e){System.out.println("Imposible cerrar cursores");};
              
        }
    }
    
    public void aceptarTarea(Voluntario voluntario, Tarea tarea){
        Connection con;
        PreparedStatement stmTarea=null;
        Boolean rsTarea;
        
        con=this.getConexion();
        
        try{
            stmTarea = con.prepareStatement("insert into realizarTarea(fechaInscripcion, fechaFin, dniVoluntario, idTarea) "+
                                "values( current_date, null, ?, ?)");
            stmTarea.setString(1, voluntario.getDni());
            stmTarea.setInt(2, tarea.getId());
            rsTarea = stmTarea.execute();
            if(rsTarea){
              this.getFachadaAplicacion().muestraExcepcion("Error al aceptar");
            }
        }catch(SQLException e){
              System.out.println(e.getMessage());
              this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try{stmTarea.close();}catch(SQLException e){System.out.println("Imposible cerrar cursores");};
              
        }
    }
    
}
