/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import aplicacion.Cita;
import aplicacion.Evento;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaParticiparEvento extends AbstractTableModel{
    private java.util.List<Evento> eventos;

    public ModeloTablaParticiparEvento(){
        this.eventos = new java.util.ArrayList<Evento>();
    }

    public int getColumnCount (){
        return 3;
    }

    public int getRowCount(){
        return eventos.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Nombre"; break;
            case 1: nombre= "Fecha"; break;
            case 2: nombre= "Participación"; break;
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
        
        Boolean estado = eventos.get(row).getParticipacionActual();
        if(estado)
            estadoStr = "Sí";
        else 
            estadoStr = "No";

        switch (col){
            case 0: resultado= eventos.get(row).getNombre(); break;
            case 1: resultado= eventos.get(row).getFecha().toString(); break;
            case 2: resultado= estadoStr;break;
        }
        return resultado;
    }

    public void setFilas(java.util.List<Evento> eventos){
        this.eventos = eventos;
        fireTableDataChanged();
    }

    public Evento obtenerEvento(int i){
        return this.eventos.get(i);
    }
    
    public void sustituirEvento(int i, Evento e){
        eventos.set(i, e);
        fireTableDataChanged();
    }
    
    public void addEvento(Evento e){
        eventos.add(e);
        fireTableDataChanged();
    }
    
    public void eliminarEvento(int index) {
        eventos.remove(index);
        fireTableDataChanged();
    }
}
