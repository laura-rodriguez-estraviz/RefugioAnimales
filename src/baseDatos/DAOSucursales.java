/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Sucursal;
import aplicacion.Usuario;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class DAOSucursales extends AbstractDAO{
    
    public DAOSucursales (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public java.util.List<Sucursal>  obtenerSucursales(String numSucursal, String municipio){
        java.util.List<Sucursal> resultado = new java.util.ArrayList<Sucursal>();
         
     
        Sucursal sucursalActual;
        Connection con;
        PreparedStatement stmSucursales=null;
        ResultSet rsSucursales;

        con=this.getConexion();
        
        String consulta = "select s.numsucursal ,s.municipio " +
                    "from sucursal s " +
                    "where municipio like ? and CAST(numSucursal AS VARCHAR) like ?";
      
        try  {
         stmSucursales=con.prepareStatement(consulta);
         stmSucursales.setString(1, "%"+numSucursal+"%");
         stmSucursales.setString(2, "%"+municipio+"%");
         rsSucursales=stmSucursales.executeQuery();
        while (rsSucursales.next())
        {
            sucursalActual = new Sucursal(rsSucursales.getInt("numsucursal"),rsSucursales.getString("municipio"));
            resultado.add(sucursalActual);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmSucursales.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }

        return resultado;
    }
    
    
    
    
    List<Sucursal> listaSucursales(){
        Connection con = this.getConexion();
        PreparedStatement stmSucursal = null;
        ResultSet sucursales;
        Sucursal s;
        ArrayList<Sucursal> resultado = new ArrayList<>();
        
        try  {
            stmSucursal = con.prepareStatement("select numsucursal, municipio \n" +
                                                "from sucursal");
            sucursales = stmSucursal.executeQuery();
            while (sucursales.next())
            {
                s = new Sucursal(sucursales.getInt("numsucursal"), sucursales.getString("municipio"));
                
                resultado.add(s);
            }
        
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmSucursal.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
        return resultado;
    }
    
    
    
    
   
    public void eliminarSucursal(int numSucursal){
        
        Connection con;
        PreparedStatement stmSucursal=null;
        
        con= this.getConexion();
        
        try{
        stmSucursal=con.prepareStatement("delete from sucursal where numsucursal = ?");
        stmSucursal.setInt(1, numSucursal);
        stmSucursal.executeUpdate();

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try{stmSucursal.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        } 
        
    
    }
    
    public void nuevaSucursal(int numSucursal, String municipio){
       Connection con;
        PreparedStatement stmSucursal=null;
        
        con= this.getConexion();
        
        try{
        stmSucursal=con.prepareStatement("INSERT INTO sucursal (numSucursal, municipio) VALUES (?, ?) ");
        stmSucursal.setInt(1, numSucursal);
        stmSucursal.setString(2, municipio);
        stmSucursal.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try{stmSucursal.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        } 
    }
 
    public void actualizarSucursal(int numSucursalViejo, int numSucursalNuevo,String municipio){
       Connection con;
        PreparedStatement stmSucursal=null;
        
        con= this.getConexion();
        
        try{
        stmSucursal=con.prepareStatement("UPDATE sucursal " +
                                        "SET municipio = ?, "+
                                        "numsucursal=? " +
                                        "WHERE numSucursal = ? ");
        stmSucursal.setString(1, municipio);
        stmSucursal.setInt(2, numSucursalNuevo);
        stmSucursal.setInt(3, numSucursalViejo);
        
        stmSucursal.executeUpdate();

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try{stmSucursal.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        } 
    }
 
    
}
