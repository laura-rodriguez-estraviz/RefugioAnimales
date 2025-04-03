/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import aplicacion.Cliente;
import aplicacion.Personal;
import aplicacion.Usuario;
import aplicacion.Voluntario;
import javax.swing.table.*;
/**
 *
 * @author basesdatos
 */
public class ModeloTablaUsuarios extends AbstractTableModel{
    private java.util.List<Usuario> usuarios;

    public ModeloTablaUsuarios(){
        this.usuarios = new java.util.ArrayList<Usuario>();
    }

    public int getColumnCount (){
        return 6;
    }

    public int getRowCount(){
        return usuarios.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Nombre"; break;
            case 1: nombre= "DNI"; break;
            case 2: nombre="Típo"; break;
            case 3: nombre="Dirección"; break;
            case 4: nombre="Telefono"; break;
            case 5: nombre="Email";break;
        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.String.class; break;
            case 1: clase= java.lang.String.class; break;
            case 2: clase=java.lang.String.class; break;
            case 3: clase=java.lang.String.class; break;
            case 4: clase=java.lang.String.class; break;
            case 5: clase=java.lang.String.class; break;
        }
        return clase;
    }

    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }

    public Object getValueAt(int row, int col){
        Object resultado=null;
        String tipo = "";
        
        if(usuarios.get(row) instanceof Cliente)
            tipo = "Cliente";
        if(usuarios.get(row) instanceof Voluntario)
            tipo = "Voluntario";
        if(usuarios.get(row) instanceof Personal)
            tipo = "Personal";

        switch (col){
            case 0: resultado= usuarios.get(row).getNombre(); break;
            case 1: resultado= usuarios.get(row).getDni(); break;
            case 2: resultado= tipo;break;
            case 3: resultado= usuarios.get(row).getDireccion(); break;
            case 4: resultado= usuarios.get(row).getTelefono(); break;
            case 5: resultado= usuarios.get(row).getEmail(); break;
        }
        return resultado;
    }

    public void setFilas(java.util.List<Usuario> usuarios){
        this.usuarios = usuarios;
        fireTableDataChanged();
    }

    public Usuario obtenerUsuario(int i){
        return this.usuarios.get(i);
    }
    
    public void eliminarUsuario(int index) {
        usuarios.remove(index);
        fireTableDataChanged();
    }

}
