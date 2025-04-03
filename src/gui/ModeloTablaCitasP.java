/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import aplicacion.Cita;
import javax.swing.table.*;

/**
 *
 * @author alumnogreibd
 */

public class ModeloTablaCitasP extends AbstractTableModel{
    private java.util.List<Cita> citas;
    
    public ModeloTablaCitasP(){
        this.citas=new java.util.ArrayList<Cita>();
    }
    
    public int getColumnCount(){
        return 2;
    }
    
    public int getRowCount(){
        return citas.size();
    }
    
    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "fecha"; break;
            case 1: nombre= "Sucursal"; break;
        }
        return nombre;
    }
    
    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.String.class; break;
            case 1: clase= java.lang.Integer.class; break;
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
            case 0: resultado= citas.get(row).getFechaCita(); break;
            case 1: resultado= citas.get(row).getSucursal().getNumSucursal(); break;
        }
        return resultado;
    }
    
    public void setFilas(java.util.List<Cita> citas){
        this.citas=citas;
        fireTableDataChanged();
    }

    public Cita obtenerCita(int i){
        return this.citas.get(i);
    }
    
}
