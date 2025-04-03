/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import aplicacion.Sucursal;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author basesdatos
 */
public class ModeloTablaSucursalesComprar extends AbstractTableModel {
    
    private java.util.List<Sucursal> sucursales;

    public ModeloTablaSucursalesComprar(){
        this.sucursales=new java.util.ArrayList<Sucursal>();
        
    }

    public int getColumnCount (){
        return 1;
    }

    public int getRowCount(){
        return sucursales.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Municipio"; break;
        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.String.class; break;
            
        }
        return clase;
    }

    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }

    public Object getValueAt(int row, int col){
        Object resultado=null;

        switch (col){
            case 0: resultado= sucursales.get(row).getMunicipio(); break;
        }
        return resultado;
    }

    public void setFilas(java.util.List<Sucursal> sucursales){
        this.sucursales=sucursales;
        fireTableDataChanged();
    }

    public Sucursal obtenerSucursal(int i){
        return this.sucursales.get(i);
    }
    
 
    
}
