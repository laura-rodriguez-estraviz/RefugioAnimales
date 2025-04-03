/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import aplicacion.Tarea;
import javax.swing.table.*;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaTareas extends AbstractTableModel {
    private java.util.List<Tarea> tareas;
    
    public ModeloTablaTareas(){
        this.tareas=new java.util.ArrayList<Tarea>();
    }
    
    public int getColumnCount(){
        return 1;
    }
    
    public int getRowCount(){
        return tareas.size();
    }
    
    @Override
    public String getColumnName(int col){
        return "id";
    }
    
    @Override
    public Class getColumnClass(int col){
        return java.lang.Integer.class;
    }
    
    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }

    public Object getValueAt(int row, int col){
        return tareas.get(row).getId();
    }
    
    public void setFilas(java.util.List<Tarea> tareas){
        this.tareas=tareas;
        fireTableDataChanged();
    }

    public Tarea obtenerTarea(int i){
        return this.tareas.get(i);
    }
}
