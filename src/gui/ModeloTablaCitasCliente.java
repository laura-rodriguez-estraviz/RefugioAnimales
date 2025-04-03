/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import aplicacion.Cita;
import aplicacion.Cliente;
import aplicacion.Personal;
import aplicacion.Usuario;
import aplicacion.Voluntario;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaCitasCliente extends AbstractTableModel{
    private java.util.List<Cita> citas;

    public ModeloTablaCitasCliente(){
        this.citas = new java.util.ArrayList<Cita>();
    }

    public int getColumnCount (){
        return 3;
    }

    public int getRowCount(){
        return citas.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Fecha"; break;
            case 1: nombre= "Sucursal"; break;
            case 2: nombre= "Estado"; break;
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
        }
        return clase;
    }

    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }

    public Object getValueAt(int row, int col){
        Object resultado=null;
        String estadoStr = "";
        
        Boolean estado = citas.get(row).isCancelado();
        if(estado == null)
            estadoStr = "Pendiente";
        else if(estado)
            estadoStr = "Cancelada";
        else 
            estadoStr = "Aceptada";

        switch (col){
            case 0: resultado= citas.get(row).getFechaCita().toString(); break;
            case 1: resultado= citas.get(row).getSucursal().getMunicipio(); break;
            case 2: resultado= estadoStr;break;
        }
        return resultado;
    }

    public void setFilas(java.util.List<Cita> citas){
        this.citas = citas;
        fireTableDataChanged();
    }

    public Cita obtenerCita(int i){
        return this.citas.get(i);
    }
    
    public void sustituirCita(int i, Cita c){
        citas.set(i, c);
        fireTableDataChanged();
    }
    
    public void addCita(Cita c){
        citas.add(c);
        fireTableDataChanged();
    }
    
    public void eliminarCita(int index) {
        citas.remove(index);
        fireTableDataChanged();
    }
}
