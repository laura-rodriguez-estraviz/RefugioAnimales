/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import aplicacion.Tienda;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaTiendas extends AbstractTableModel{
    private java.util.List<Tienda> tiendas;

    public ModeloTablaTiendas() {
        this.tiendas=new java.util.ArrayList<Tienda>();
        
    }

    public int getColumnCount (){
        return 1;
    }

    public int getRowCount(){
        return tiendas.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Tiendas"; break;
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
            case 0: resultado= tiendas.get(row).getNombre(); break;
        }
        return resultado;
    }

    public void setFilas(java.util.List<Tienda> tiendas){
        this.tiendas=tiendas;
        fireTableDataChanged();
    }

    public Tienda obtenerTienda(int i){
        return this.tiendas.get(i);
    }
    
 
    
}
