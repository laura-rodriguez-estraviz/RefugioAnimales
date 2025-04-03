/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Cita;
import aplicacion.Cliente;
import aplicacion.Evento;
import aplicacion.Personal;
import aplicacion.Sucursal;
import aplicacion.Usuario;
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
public class DAOEventos extends AbstractDAO {
    
    public DAOEventos (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    //Participa false -  todos los eventos, participa true solo los que participa
    public List<Evento> listarEventosUsuario(String nombre, Boolean participa, Usuario usr) {
        Connection con = this.getConexion();
        PreparedStatement stmEventos = null;
        ResultSet eventos;
        Evento ev;
        ArrayList<Evento> resultado = new ArrayList<>();
        
        try  {
            stmEventos = con.prepareStatement("select e.nombre, e.descripcion, e.fecha, a.dni, o.dni,(a.dni is not null) or (o.dni is not null) as participa\n" +
                                                "from evento e\n" +
                                                "left join (select * from asistir where dnicliente=?) as a(evento, dni)\n" +
                                                "	on e.nombre = a.evento \n" +
                                                "left join (select * from organizar where dnipersonal=?) as o(evento, dni)\n" +
                                                "	on e.nombre = o.evento \n" +
                                                "where ((a.dni is not null or o.dni is not null) and ? )= ? and  \n" +
                                                "	e.nombre like ? and \n" +
                                                "	e.fecha >= current_date ");
            stmEventos.setString(1, usr.getDni());
            stmEventos.setString(2, usr.getDni());
            stmEventos.setBoolean(3, participa);
            stmEventos.setBoolean(4, participa);
            stmEventos.setString(5, "%"+nombre+"%");
            eventos = stmEventos.executeQuery();
            while (eventos.next())
            {
                ev = new Evento(eventos.getString("nombre"), eventos.getString("descripcion"), eventos.getDate("fecha"), eventos.getBoolean("participa"));    
                resultado.add(ev);
            }
        
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmEventos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
        return resultado;
    }
    
    public int participar(Usuario usr, Evento ev) {
        int resultado = 0;
        if(usr instanceof Cliente) {
            Connection con;
            PreparedStatement stmEvento=null;
            

            con=super.getConexion();

            try {
                con.setAutoCommit(false);
                stmEvento=con.prepareStatement("insert into asistir \n" +
                            "	values (?, ?)");
                stmEvento.setString(1, ev.getNombre());
                stmEvento.setString(2, usr.getDni());

                resultado = stmEvento.executeUpdate();
                
                stmEvento=con.prepareStatement("update cliente set \n" +
                                                "	puntuacion = puntuacion +1\n" +
                                                "where dni = ?");
                stmEvento.setString(1, usr.getDni());
                resultado += stmEvento.executeUpdate();
                
                con.commit();
                
                con.setAutoCommit(true);

            } catch (SQLException e){
              System.out.println(e.getMessage());
              try {con.rollback();} catch (SQLException ee){System.out.println("Imposible cerrar cursores");}
              this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            }finally{
              try {stmEvento.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
            }
        
        }
        
        if(usr instanceof Personal) {
            Connection con;
            PreparedStatement stmEvento=null;
            

            con=super.getConexion();
            try {
                stmEvento=con.prepareStatement("insert into organizar \n" +
                            "	values (?, ?)");
                stmEvento.setString(1, ev.getNombre());
                stmEvento.setString(2, usr.getDni());

                resultado = stmEvento.executeUpdate();

            } catch (SQLException e){
              System.out.println(e.getMessage());
              this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            }finally{
              try {stmEvento.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
            }
        }
        
        return resultado;
    }
    
    public int anularParticipacion(Usuario usr, Evento ev) {
        int resultado = 0;
        if(usr instanceof Cliente) {
            Connection con;
            PreparedStatement stmEvento=null;
            

            con=super.getConexion();

            try {
                con.setAutoCommit(false);
                stmEvento=con.prepareStatement("update cliente set \n" +
                                    "	puntuacion = case \n" +
                                    "		when puntuacion-1 < 0 then 0\n" +
                                    "		else puntuacion-1\n" +
                                    "		end\n" +
                                    "	where dni = ? and exists (\n" +
                                    "select * from asistir as a where a.nombreevento=? and a.dnicliente=?)");
                stmEvento.setString(1, usr.getDni());
                stmEvento.setString(2, ev.getNombre());
                stmEvento.setString(3, usr.getDni());

                resultado = stmEvento.executeUpdate();
                
                stmEvento=con.prepareStatement("delete from asistir \n" +
                                    "where nombreevento=? and dnicliente=?");
                stmEvento.setString(1, ev.getNombre());
                stmEvento.setString(2, usr.getDni());
                resultado += stmEvento.executeUpdate();
                
                con.commit();
                
                con.setAutoCommit(true);

            } catch (SQLException e){
              System.out.println(e.getMessage());
              try {con.rollback();} catch (SQLException ee){System.out.println("Imposible cerrar cursores");}
              this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            }finally{
              try {stmEvento.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
            }
        
        }
        
        if(usr instanceof Personal) {
            Connection con;
            PreparedStatement stmEvento=null;
            

            con=super.getConexion();
            try {
                stmEvento=con.prepareStatement("delete from organizar \n" +
                "where nombreevento=? and dnipersonal=?");
                stmEvento.setString(1, ev.getNombre());
                stmEvento.setString(2, usr.getDni());

                resultado = stmEvento.executeUpdate();

            } catch (SQLException e){
              System.out.println(e.getMessage());
              this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            }finally{
              try {stmEvento.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
            }
        }
        
        return resultado;
    }
    
}
