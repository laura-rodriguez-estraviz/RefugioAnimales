/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Cita;

import aplicacion.Sucursal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class DAOCitas extends AbstractDAO {
    
    public DAOCitas (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    List<Cita> buscarCitasCliente(String dni){
        Connection con = this.getConexion();
        PreparedStatement stmCitas = null;
        ResultSet citas;
        Cita c; Sucursal s;
        ArrayList<Cita> resultado = new ArrayList<>();
        
        try  {
            stmCitas = con.prepareStatement("select cita.fechasolicitud, cita.fechacita, cita.cancelado, cita.dnicliente, cita.numsucursal, sucursal.municipio  \n" +
                                            "from cita join sucursal on cita.numsucursal = sucursal.numsucursal \n" +
                                            "where cita.dnicliente like ?");
            stmCitas.setString(1, "%"+dni+"%");
            citas= stmCitas.executeQuery();
            while (citas.next())
            {
                Boolean cancelado = citas.getBoolean("cancelado");
                if(citas.wasNull()) cancelado = null;
                s = new Sucursal(citas.getInt("numsucursal"), citas.getString("municipio"));
                c = new Cita(citas.getDate("fechasolicitud"), citas.getDate("fechacita"), 
                        cancelado, s, dni);
                
                resultado.add(c);
            }
        
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCitas.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
        return resultado;
    }
    
    //Requiere fechacita, numsucursal y dnicliente
    public int insertarCita(Cita c) {
        Connection con;
        PreparedStatement stmCita=null;
        int resultado = 0;
        
        con=super.getConexion();

        try {
        stmCita=con.prepareStatement("insert into cita(fechacita, dnicliente, numsucursal) \n" +
                                        "values (?,?,?)");
        stmCita.setDate(1, c.getFechaCita());
        stmCita.setString(2, c.getDni());
        stmCita.setInt(3, c.getSucursal().getNumSucursal());
        
        resultado = stmCita.executeUpdate();
        
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCita.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
        return resultado;
    }
    
    public int actualizarCita(Cita oldCita, Cita newCita) {
        Connection con;
        PreparedStatement stmCita=null;
        int resultado = 0;
        
        con=super.getConexion();

        try {
        stmCita=con.prepareStatement("UPDATE cita\n" +
                                    "SET fechacita = ?,\n" +
                                    "	numsucursal = ?,\n" +
                                    "	cancelado = null\n" +
                                    "WHERE fechasolicitud = ? and dnicliente = ? and numsucursal = ?");
        stmCita.setDate(1, newCita.getFechaCita());
        stmCita.setInt(2, newCita.getSucursal().getNumSucursal());
        stmCita.setDate(3, oldCita.getFechaSolicitud());
        stmCita.setString(4, oldCita.getDni());
        stmCita.setInt(5, oldCita.getSucursal().getNumSucursal());
        
        resultado = stmCita.executeUpdate();
        
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCita.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
        return resultado;
    }
    
    public int borrarCita(Cita c) {
        Connection con;
        PreparedStatement stmCita=null;
        int resultado = 0;
        
        con=super.getConexion();

        try {
        stmCita=con.prepareStatement("delete from cita \n" +
                "where fechasolicitud = ? and dnicliente = ? and numsucursal = ?");
        stmCita.setDate(1, c.getFechaSolicitud());
        stmCita.setString(2, c.getDni());
        stmCita.setInt(3, c.getSucursal().getNumSucursal());
        
        resultado = stmCita.executeUpdate();
        
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCita.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
        return resultado;
    }
    
    
    
        public java.util.List<Cita> consultarCitasP(){
        java.util.List<Cita> resultado = new java.util.ArrayList<>();
        Connection con;
        PreparedStatement stmCitas=null;
        ResultSet rsCitas;
        Cita citaActual;
        
        con=this.getConexion();
        
        try {
            stmCitas = con.prepareStatement("select fechaSolicitud, cancelado, fechaCita, numSucursal, dniCliente "+
                          "from cita "+
                          "where cancelado is null");
            rsCitas=stmCitas.executeQuery();
            
            while(rsCitas.next()){
                citaActual = new Cita(rsCitas.getDate("fechaSolicitud"), rsCitas.getDate("fechaCita"),rsCitas.getBoolean("cancelado"),
                        new Sucursal(rsCitas.getInt("numSucursal")), rsCitas.getString("dniCliente"));
                resultado.add(citaActual);
            }
            
        }catch(SQLException e){
              System.out.println(e.getMessage());
              this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try{stmCitas.close();}catch(SQLException e){System.out.println("Imposible cerrar cursores");};
              
        }
        return resultado;
    }
    
    public java.util.List<Cita> buscarCitasP(int numSucursal){
        java.util.List<Cita> resultado = new java.util.ArrayList<>();
        Connection con;
        PreparedStatement stmCitas=null;
        ResultSet rsCitas;
        Cita citaActual;
        
        con=this.getConexion();
        
        try {
            
            stmCitas = con.prepareStatement("select fechaSolicitud, cancelado, fechaCita, numSucursal, dniCliente "+
                          "from cita "+
                          "where numSucursal = ? "+
                          "and cancelado is null");
            stmCitas.setInt(1,numSucursal);
            rsCitas=stmCitas.executeQuery();
            
            while(rsCitas.next()){
                citaActual = new Cita(rsCitas.getDate("fechaSolicitud"), rsCitas.getDate("fechaCita"),rsCitas.getBoolean("cancelado"),
                        new Sucursal (rsCitas.getInt("numSucursal")), rsCitas.getString("dniCliente"));
                resultado.add(citaActual);
            }
            
        }catch(SQLException e){
              System.out.println(e.getMessage());
              this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try{stmCitas.close();}catch(SQLException e){System.out.println("Imposible cerrar cursores");};
              
        }
        return resultado;
    }
    
    public void aceptarCita(Cita cita){
        Connection con;
        PreparedStatement stmCita=null;
        Boolean rs;
        
        con=this.getConexion();
        try{
            stmCita=con.prepareStatement("update cita "+
                    "set cancelado = true "+
                    "where fechaSolicitud = cast(? as date) "+
                    "and numSucursal = ? "+
                    "and dniCliente = ?");
            stmCita.setDate(1,cita.getFechaSolicitud());
            stmCita.setInt(2, cita.getSucursal().getNumSucursal());
            stmCita.setString(3, cita.getDni());
            rs = stmCita.execute();
            if(rs)
              this.getFachadaAplicacion().muestraExcepcion("Error al aceptar");
        }catch(SQLException e){
              System.out.println(e.getMessage());
              this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try{stmCita.close();}catch(SQLException e){System.out.println("Imposible cerrar cursores");};
              
        }
    }
    
    public void denegarCita(Cita cita){
        Connection con;
        PreparedStatement stmCita=null;
        Boolean rs;
        
        con=this.getConexion();
        try{
            stmCita=con.prepareStatement("update cita "+
                    "set cancelado = false "+
                    "where fechaSolicitud = cast(? as date) "+
                    "and numSucursal = ? "+
                    "and dniCliente = ?");
            stmCita.setDate(1,cita.getFechaSolicitud());
            stmCita.setInt(2, cita.getSucursal().getNumSucursal());
            stmCita.setString(3, cita.getDni());
            rs = stmCita.execute();
            if(rs)
              this.getFachadaAplicacion().muestraExcepcion("Error al denegar");
        }catch(SQLException e){
              System.out.println(e.getMessage());
              this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try{stmCita.close();}catch(SQLException e){System.out.println("Imposible cerrar cursores");};
              
        }
    }
    
    
    
}
