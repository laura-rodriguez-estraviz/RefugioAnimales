/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;


import aplicacion.Producto;
import aplicacion.Tienda;
import java.sql.Connection;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class DAOTiendas extends AbstractDAO{
    
    public DAOTiendas (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public java.util.List<Producto> obtenerProductosNoTienda(int numSucursal, String Tienda,String nombreProducto){
        java.util.List<Producto> resultado=new ArrayList<>();
        
        
        Producto ProductoActual;
        Connection con;
        PreparedStatement stmProductos=null;
        ResultSet rsProductos;

        con=this.getConexion();
        
        String consulta = "SELECT DISTINCT p.nombre " +
                            "FROM producto p " +
                            "LEFT JOIN ubicacionProducto up ON p.nombre = up.nombreProducto " +
                                       "AND up.numSucursal = ? " +
                                       "AND up.nombreTienda = ? " +
                             "WHERE up.nombreProducto IS NULL AND p.nombre like ? ";
      
        try  {
         stmProductos=con.prepareStatement(consulta);
         stmProductos.setInt(1, numSucursal);
         stmProductos.setString(2, Tienda);
         stmProductos.setString(3, "%"+nombreProducto+"%");
         rsProductos=stmProductos.executeQuery();
        while (rsProductos.next())
        {
            ProductoActual = new Producto(rsProductos.getString("nombre"),"",0,0);
            resultado.add(ProductoActual);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmProductos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
      
        return resultado;
    }
    
    
    
    public java.util.List<Tienda> obtenerTiendas(String nombreTienda, int numSucursal){
        java.util.List<Tienda> resultado=new ArrayList<>();
        
        
        Tienda TiendaActual;
        Connection con;
        PreparedStatement stmTiendas=null;
        ResultSet rsTiendas;

        con=this.getConexion();
        
        String consulta = "select t.nombre " +
                    "from tienda t " +
                    "where numsucursal=? and nombre like ? ";
      
        try  {
         stmTiendas=con.prepareStatement(consulta);
         stmTiendas.setInt(1, numSucursal);
         stmTiendas.setString(2, "%"+nombreTienda+"%");
         rsTiendas=stmTiendas.executeQuery();
        while (rsTiendas.next())
        {
            TiendaActual = new Tienda(rsTiendas.getString("nombre"));
            resultado.add(TiendaActual);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmTiendas.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
        
        
        
        return resultado;
    }
    
    public java.util.List<Producto> obtenerProductosTienda(String nombreTienda, int numSucursal, String nombreProducto){
        java.util.List<Producto> resultado=new ArrayList<>();
        
         
        Producto ProductoActual;
        Connection con;
        PreparedStatement stmProductos=null;
        ResultSet rsProductos;

        con=this.getConexion();
        
        String consulta = "SELECT up.nombreProducto, up.existencias " +
                            "FROM producto p " +
                            "INNER JOIN ubicacionProducto up ON p.nombre = up.nombreProducto " +
                            "WHERE up.numSucursal = ? " +
                                       "AND up.nombreTienda = ? " +
                                       "AND up.nombreProducto like ? ";
      
        try  {
         stmProductos=con.prepareStatement(consulta);
         stmProductos.setInt(1, numSucursal);
         stmProductos.setString(2, nombreTienda);
         stmProductos.setString(3, "%"+nombreProducto+"%");
         rsProductos=stmProductos.executeQuery();
         
        while (rsProductos.next())
        {
            ProductoActual = new Producto(rsProductos.getString("nombreProducto"),"",0,rsProductos.getInt("existencias"));
            resultado.add(ProductoActual);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmProductos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
      
        
        return resultado;
    }
    
    
    public java.util.List<Producto> obtenerProductosNoTiendaConExistencias(int numSucursal, String Tienda,String nombreProducto){
        java.util.List<Producto> resultado=new ArrayList<>();
        
        
        Producto ProductoActual;
        Connection con;
        PreparedStatement stmProductos=null;
        ResultSet rsProductos;

        con=this.getConexion();
        
        String consulta = "SELECT DISTINCT p.nombre " +
                            "FROM producto p " +
                            "JOIN ubicacionProducto up ON p.nombre = up.nombreProducto " +
                                       "AND up.numSucursal = ? " +
                                       "AND up.nombreTienda = ? " +
                                       "AND up.nombreProducto like ? " +
                            "WHERE up.existencias > 0";
      
        try  {
         stmProductos=con.prepareStatement(consulta);
         stmProductos.setInt(1, numSucursal);
         stmProductos.setString(2, Tienda);
         stmProductos.setString(3, "%"+nombreProducto+"%");
         rsProductos=stmProductos.executeQuery();
        while (rsProductos.next())
        {
            ProductoActual = new Producto(rsProductos.getString("nombre"),"",0,0);
            resultado.add(ProductoActual);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmProductos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
      
        return resultado;
    }
    
   
    
    
    public void nuevaTienda(String nombreTienda, int numSucursal){
                Connection con;
        PreparedStatement stmTienda=null;
        
        con= this.getConexion();
        
        try{
        stmTienda=con.prepareStatement("INSERT INTO tienda (numSucursal, nombre) "
                                            + "VALUES (?,?) ");
        stmTienda.setInt(1, numSucursal);
        stmTienda.setString(2, nombreTienda);
        
        stmTienda.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try{stmTienda.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        } 
    }
    
    
    public void anadirProductoTienda(String producto,String tienda,int numSucursal){
        Connection con;
        PreparedStatement stmTienda=null;
        
        con= this.getConexion();
        
        try{
        stmTienda=con.prepareStatement("INSERT INTO ubicacionProducto (existencias, nombreTienda, nombreProducto, numSucursal) "
                                            + "VALUES (?, ?,?,?) ");
        stmTienda.setInt(1, 0);
        stmTienda.setString(2, tienda);
        stmTienda.setString(3, producto);
        stmTienda.setInt(4, numSucursal);
        stmTienda.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try{stmTienda.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        } 
    }
    
    public void eliminarTienda(String nombreTienda, int numSucursal){
        Connection con;
        PreparedStatement stmTienda=null;
        
        con= this.getConexion();
        
        try{
        stmTienda=con.prepareStatement("delete from tienda where numsucursal = ? and nombre =? ");
        stmTienda.setInt(1, numSucursal);
        stmTienda.setString(2, nombreTienda);
        stmTienda.executeUpdate();

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try{stmTienda.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        } 

    }
    
    public void eliminarProductoTienda(String tienda, String nombreProducto, int numSucursal){
        Connection con;
        PreparedStatement stmTienda=null;
        
        con= this.getConexion();
        
        try{
        stmTienda=con.prepareStatement("delete from ubicacionProducto where numsucursal = ? and nombreTienda =? and nombreProducto = ? ");
        stmTienda.setInt(1, numSucursal);
        stmTienda.setString(2, tienda);
        stmTienda.setString(3, nombreProducto);
        stmTienda.executeUpdate();

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try{stmTienda.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        } 
        
    }
    
    public void actualizarProductoTienda(String tienda, String nombreProducto, int nuevaCantidad, int numSucursal){
        Connection con;
        PreparedStatement stmTienda=null;
        
        con= this.getConexion();
        
        try{
        stmTienda=con.prepareStatement("UPDATE ubicacionProducto " +
                                        "SET existencias = ? "+
                                        "WHERE numSucursal = ? and nombreProducto=? and nombreTienda=? ");
        stmTienda.setInt(1, nuevaCantidad);
        stmTienda.setInt(2, numSucursal);
        stmTienda.setString(3, nombreProducto);
        stmTienda.setString(4, tienda);
        
        stmTienda.executeUpdate();

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try{stmTienda.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

        
    
    
    public void actualizarTienda(String nombreTiendaActual, String nombreTiendaNuevo, int numSucursal){
        Connection con;
        PreparedStatement stmTienda=null;
        
        con= this.getConexion();
        
        try{
        stmTienda=con.prepareStatement("UPDATE tienda " +
                                        "SET nombre = ? "+
                                        "WHERE numSucursal = ? and nombre=? ");
        stmTienda.setString(1, nombreTiendaNuevo);
        stmTienda.setInt(2, numSucursal);
        stmTienda.setString(3, nombreTiendaActual);
        
        stmTienda.executeUpdate();

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try{stmTienda.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    
    
     public int comprar(String tienda, String producto, int sucursal, String dni) {
        Connection con;
            PreparedStatement stmT=null;
            int resultado = 0;

            con=super.getConexion();

            try {
                con.setAutoCommit(false);
                stmT=con.prepareStatement("update ubicacionproducto set \n" +
                                "	existencias = existencias - 1\n" +
                                "	where numSucursal = ?\n" +
                                "          and nombreTienda = ? \n" +
                                "          and nombreProducto = ?;");
                stmT.setInt(1, sucursal);
                stmT.setString(2, tienda);
                stmT.setString(3, producto);

                resultado = stmT.executeUpdate();
                
                stmT=con.prepareStatement("update cliente set \n" +
                        "	puntuacion = puntuacion +1\n" +
                        "	where dni = ?");
                stmT.setString(1, dni);
                resultado += stmT.executeUpdate();
                
                con.commit();
                
                con.setAutoCommit(true);

            } catch (SQLException e){
              System.out.println(e.getMessage());
              try {con.rollback();} catch (SQLException ee){System.out.println("Imposible cerrar cursores");}
              this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            }finally{
              try {stmT.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
            }
            
            return resultado;
    }
    
    
    
}
