/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;
import aplicacion.Usuario;
import aplicacion.Voluntario;
import aplicacion.Cliente;
import aplicacion.Personal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author basesdatos
 */
public class DAOUsuarios extends AbstractDAO {

   public DAOUsuarios (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public Usuario validarUsuario(String dni, String clave){
        Usuario resultado=null;
        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsUsuario;

        con=this.getConexion();

        try {
        stmUsuario=con.prepareStatement("SELECT 'voluntario' as tipo " +
                                        "FROM usuario, voluntario  " +
                                        "WHERE usuario.dni = voluntario.dni and  usuario.dni = ? and usuario.clave =? " +
                                        "UNION " +
                                        "SELECT 'personal' as tipo " +
                                        "FROM usuario, personal " +
                                        "WHERE usuario.dni = personal.dni and usuario.dni = ? and usuario.clave =? " +
                                        "UNION " +
                                        "SELECT 'cliente' as tipo " +
                                        "FROM usuario, cliente " +
                                        "WHERE usuario.dni=cliente.dni and usuario.dni = ? and usuario.clave = ? ");
        
        stmUsuario.setString(1, dni);
        stmUsuario.setString(2, clave);
        stmUsuario.setString(3, dni);
        stmUsuario.setString(4, clave);
        stmUsuario.setString(5, dni);
        stmUsuario.setString(6, clave);
        
        rsUsuario=stmUsuario.executeQuery();
        String TipoUsuario=null;
        if (rsUsuario.next())
        {
            TipoUsuario = new String(rsUsuario.getString("tipo"));

        }
        if (TipoUsuario!=null){
            
            Connection con2;
            PreparedStatement stmUsuario2=null;
            ResultSet rsUsuario2;
            con2=this.getConexion();
            
            try {
                
                if(TipoUsuario.equals("voluntario")){
                    stmUsuario2=con2.prepareStatement("SELECT u.nombre, u.dni, u.direccion, u.telefono, u.email, u.clave, v.horaEntrada, v.horaSalida " +
                                                        "FROM usuario u " +
                                                        "INNER JOIN voluntario v ON u.dni = v.dni " +
                                                        "WHERE u.dni = ? ");

                    stmUsuario2.setString(1, dni);
                    
                    rsUsuario2=stmUsuario2.executeQuery();
                    if (rsUsuario2.next())
                    {
                        resultado = (Usuario)(new Voluntario(rsUsuario2.getString("dni"), rsUsuario2.getString("clave"),
                                                  rsUsuario2.getString("nombre"), rsUsuario2.getString("direccion"),
                                                  rsUsuario2.getString("email"), rsUsuario2.getInt("telefono"),rsUsuario2.getTime("horaEntrada"),rsUsuario2.getTime("horaSalida")));

                    }

                }
                else if(TipoUsuario.equals("cliente")){
                    stmUsuario2=con2.prepareStatement("SELECT u.nombre, u.dni, u.direccion, u.telefono, u.email, u.clave, c.puntuacion " +
                                                        "FROM usuario u " +
                                                        "INNER JOIN cliente c ON u.dni = c.dni " +
                                                        "WHERE u.dni = ? ");

                    stmUsuario2.setString(1, dni);
                    rsUsuario2=stmUsuario2.executeQuery();
                    if (rsUsuario2.next())
                    {
                        resultado = (Usuario)(new Cliente(rsUsuario2.getString("dni"), rsUsuario2.getString("clave"),
                                                  rsUsuario2.getString("nombre"), rsUsuario2.getString("direccion"),
                                                  rsUsuario2.getString("email"), rsUsuario2.getInt("telefono"),rsUsuario2.getInt("puntuacion")));

                    }
                }
                else {//personal
                    stmUsuario2=con2.prepareStatement("SELECT u.nombre, u.dni, u.direccion, u.telefono, u.email, u.clave, p.cargo, p.horaEntrada, p.horaSalida, p.cuentaBancaria " +
                                                        "FROM usuario u " +
                                                        "INNER JOIN personal p ON u.dni = p.dni " +
                                                        "WHERE u.dni = ? ");

                    stmUsuario2.setString(1, dni);
                    rsUsuario2=stmUsuario2.executeQuery();
                    if (rsUsuario2.next())
                    {
                        resultado = (Usuario)(new Personal(rsUsuario2.getString("dni"), rsUsuario2.getString("clave"),
                                                  rsUsuario2.getString("nombre"), rsUsuario2.getString("direccion"),
                                                  rsUsuario2.getString("email"), rsUsuario2.getInt("telefono"),rsUsuario2.getString("cargo"),rsUsuario2.getTime("horaEntrada"),rsUsuario2.getTime("horaSalida"),rsUsuario2.getString("cuentaBancaria")));

                    }

                }
            } 
            catch (SQLException e){
                System.out.println(e.getMessage());
                this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            }
            finally{
                try {stmUsuario2.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
            }
        }
        
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    
    
    
     List<Usuario> buscarUsuarios(String dni, String nombre){
        Connection con = this.getConexion();
        PreparedStatement stmUsrs = null;
        ResultSet usrs;
        Cliente c; Voluntario v; Personal p;
        ArrayList<Usuario> resultado = new ArrayList<>();
        
        try  {
            stmUsrs = con.prepareStatement("select usuario.dni, usuario.nombre, usuario.direccion, usuario.telefono, usuario.email, usuario.clave, cliente.puntuacion \n" +
                                            "from usuario join cliente on usuario.dni=cliente.dni\n" +
                                            "where usuario.dni like ? and usuario.nombre like ?");
            stmUsrs.setString(1, "%"+dni+"%");
            stmUsrs.setString(2, "%"+nombre+"%");
            usrs = stmUsrs.executeQuery();
            while (usrs.next())
            {
                c = new Cliente(usrs.getString("dni"), usrs.getString("clave"), usrs.getString("nombre"),
                        usrs.getString("direccion"), usrs.getString("email"), usrs.getInt("telefono"),
                        usrs.getInt("puntuacion"));
                resultado.add(c);
            }
            
            stmUsrs = con.prepareStatement("select usuario.dni, usuario.nombre, usuario.direccion, usuario.telefono, usuario.email, usuario.clave, voluntario.horaentrada, voluntario.horasalida \n" +
                                        "from usuario join voluntario on usuario.dni=voluntario.dni\n" +
                                        "where usuario.dni like ? and usuario.nombre like ?");
            stmUsrs.setString(1, "%"+dni+"%");
            stmUsrs.setString(2, "%"+nombre+"%");
            usrs = stmUsrs.executeQuery();
            while (usrs.next())
            {

                v = new Voluntario(usrs.getString("dni"), usrs.getString("clave"), usrs.getString("nombre"),
                        usrs.getString("direccion"), usrs.getString("email"), usrs.getInt("telefono"),
                        usrs.getTime("horaentrada"), usrs.getTime("horasalida"));
                resultado.add(v);
            }
            
            stmUsrs = con.prepareStatement("select usuario.dni, usuario.nombre, usuario.direccion, usuario.telefono, usuario.email, usuario.clave, personal.cargo, personal.horaentrada, personal.horasalida, personal.cuentabancaria\n" +
                                            "from usuario join personal on usuario.dni=personal.dni\n" +
                                            "where usuario.dni like ? and usuario.nombre like ?");
            stmUsrs.setString(1, "%"+dni+"%");
            stmUsrs.setString(2, "%"+nombre+"%");
            usrs = stmUsrs.executeQuery();
            while (usrs.next())
            {

                p = new Personal(usrs.getString("dni"), usrs.getString("clave"), usrs.getString("nombre"),
                        usrs.getString("direccion"), usrs.getString("email"), usrs.getInt("telefono"),
                        usrs.getString("cargo"), usrs.getTime("horaentrada"), usrs.getTime("horasalida"),
                        usrs.getString("cuentabancaria"));
                resultado.add(p);
            }
        
        
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmUsrs.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
        return resultado;
    }
    
    public int eliminarUsuario(String dni) {
         Connection con;
        int result = 0;
        PreparedStatement stmUsuario=null;
        
        con= this.getConexion();
        
        try{
        stmUsuario=con.prepareStatement("delete from usuario where dni like ?");
        stmUsuario.setString(1, dni);
        result = stmUsuario.executeUpdate();

        stmUsuario.executeUpdate();
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try{stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        } 
        
        return result;
    }
    
    
    public void nuevoUsuario(Usuario u){
        
        Connection con;
        PreparedStatement stmUsuario=null;
        
        con= this.getConexion();
        
        try{
            if (u instanceof Cliente){
                stmUsuario=con.prepareStatement("INSERT INTO usuario (nombre, dni, direccion, telefono, email, clave) VALUES "+
                                                "(?, ?,?,?,?,?); "+
                                                "INSERT INTO cliente (dni, puntuacion) VALUES " +
                                                "(?, ?) ");
                Cliente c=(Cliente)u;
                stmUsuario.setString(1, u.getNombre());
                stmUsuario.setString(2, u.getDni());
                stmUsuario.setString(3, u.getDireccion());
                stmUsuario.setInt(4, u.getTelefono());
                stmUsuario.setString(5, u.getEmail());
                stmUsuario.setString(6, u.getClave());
                stmUsuario.setString(7, u.getDni());
                stmUsuario.setInt(8, c.getPuntuacion());
            }
            
            else if (u instanceof Voluntario){
        
                stmUsuario=con.prepareStatement("INSERT INTO usuario (nombre, dni, direccion, telefono, email, clave) VALUES "+
                                                "(?, ?,?,?,?,?); "+
                                                "INSERT INTO voluntario (dni, horaEntrada, horaSalida) VALUES " +
                                                "(?, ?, ?) ");
                Voluntario v=(Voluntario)u;
                        stmUsuario.setString(1, u.getNombre());
                        stmUsuario.setString(2, u.getDni());
                        stmUsuario.setString(3, u.getDireccion());
                        stmUsuario.setInt(4, u.getTelefono());
                        stmUsuario.setString(5, u.getEmail());
                        stmUsuario.setString(6, u.getClave());
                        stmUsuario.setString(7, u.getDni());
                        
                        stmUsuario.setTime(8, v.getHoraEntrada());
                        stmUsuario.setTime(9, v.getHoraSalida());
            }
            else{
                
                stmUsuario=con.prepareStatement("INSERT INTO usuario (nombre, dni, direccion, telefono, email, clave) VALUES "+
                                                "(?, ?,?,?,?,?); "+
                                                "INSERT INTO personal (dni, cargo, horaEntrada, horaSalida, cuentaBancaria) VALUES " +
                                                "(?, ?, ?, ?, ?) ");
                
                Personal p=(Personal)u;
                stmUsuario.setString(1, u.getNombre());
                stmUsuario.setString(2, u.getDni());
                stmUsuario.setString(3, u.getDireccion());
                stmUsuario.setInt(4, u.getTelefono());
                stmUsuario.setString(5, u.getEmail());
                stmUsuario.setString(6, u.getClave());
                stmUsuario.setString(7, u.getDni());

                stmUsuario.setString(8, p.getCargo());
                stmUsuario.setTime(9, p.getHoraEntrada());
                stmUsuario.setTime(10, p.getHoraSalida());
                stmUsuario.setString(11, p.getCuentaBancaria());
            }
        
        
        stmUsuario.executeUpdate();
        
        
        
        
        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try{stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        } 
        
        
    }
    
    public void actualizarUsuario(String s, Usuario u){
        Connection con;
        PreparedStatement stmUsuario=null;
        
        con= this.getConexion();
        
        try{
            if (u instanceof Voluntario ){
                stmUsuario=con.prepareStatement("update usuario " +
                                                "set nombre= ?, " +
                                                "dni= ?, " +
                                                "direccion= ?, " +
                                                "telefono=? , " +
                                                "email=? , " +
                                                "clave=? " +
                                                "where dni=?; "+
                                                "update voluntario " +
                                                "set horaEntrada=?, " +
                                                "horaSalida=? " +
                                                "where dni=? ");
                stmUsuario.setString(1, u.getNombre());
                stmUsuario.setString(2, u.getDni());
                stmUsuario.setString(3, u.getDireccion());
                stmUsuario.setInt(4, u.getTelefono());
                stmUsuario.setString(5, u.getEmail());
                stmUsuario.setString(6, u.getClave());
                stmUsuario.setString(7, s);
                stmUsuario.setTime(8, ((Voluntario) u).getHoraEntrada());
                stmUsuario.setTime(9, ((Voluntario) u).getHoraSalida());
                stmUsuario.setString(10, u.getDni());

                stmUsuario.executeUpdate();
            }
            else if (u instanceof Personal){
                stmUsuario=con.prepareStatement("update usuario " +
                                                "set nombre= ?, " +
                                                "dni= ?, " +
                                                "direccion= ?, " +
                                                "telefono=? , " +
                                                "email=? , " +
                                                "clave=? " +
                                                "where dni=?; "+
                                                "update personal " +
                                                "set cargo=?, " +
                                                "horaEntrada=?, " +
                                                "horaSalida=?, " +
                                                "cuentaBancaria=? " +
                                                "where dni=?");
                stmUsuario.setString(1, u.getNombre());
                stmUsuario.setString(2, u.getDni());
                stmUsuario.setString(3, u.getDireccion());
                stmUsuario.setInt(4, u.getTelefono());
                stmUsuario.setString(5, u.getEmail());
                stmUsuario.setString(6, u.getClave());
                stmUsuario.setString(7, s);
                stmUsuario.setString(8, ((Personal) u).getCargo());
                stmUsuario.setTime(9, ((Personal) u).getHoraEntrada());
                stmUsuario.setTime(10, ((Personal) u).getHoraSalida());
                stmUsuario.setString(11, ((Personal) u).getCuentaBancaria());
                stmUsuario.setString(12, u.getDni());

                stmUsuario.executeUpdate();
            }
            else if (u instanceof Cliente){
                stmUsuario=con.prepareStatement("update usuario " +
                                                "set nombre= ?, " +
                                                "dni= ?, " +
                                                "direccion= ?, " +
                                                "telefono=? , " +
                                                "email=? , " +
                                                "clave=? " +
                                                "where dni=?; "+
                                                "update cliente " +
                                                "set puntuacion=? " +
                                                "where dni=?");
                stmUsuario.setString(1, u.getNombre());
                stmUsuario.setString(2, u.getDni());
                stmUsuario.setString(3, u.getDireccion());
                stmUsuario.setInt(4, u.getTelefono());
                stmUsuario.setString(5, u.getEmail());
                stmUsuario.setString(6, u.getClave());
                stmUsuario.setString(7, s);
                stmUsuario.setInt(8, ((Cliente) u).getPuntuacion());
                stmUsuario.setString(9, u.getDni());

            stmUsuario.executeUpdate();
            }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try{stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        } 
    }
    
    
}

    

