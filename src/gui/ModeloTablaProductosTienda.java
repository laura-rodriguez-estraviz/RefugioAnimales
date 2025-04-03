/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import aplicacion.Producto;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaProductosTienda extends AbstractTableModel {
    
    private java.util.List<Producto> productos;

    public ModeloTablaProductosTienda(){
        this.productos=new java.util.ArrayList<Producto>();
        
    }

    public int getColumnCount (){
        return 1;
    }

    public int getRowCount(){
        return productos.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Productos"; break;
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
            case 0: resultado= productos.get(row).getNombre(); break;
        }
        return resultado;
    }

    public void setFilas(java.util.List<Producto> productos){
        this.productos=productos;
        fireTableDataChanged();
    }

    public Producto obtenerProducto(int i){
        return this.productos.get(i);
    }
    
 
    
}
