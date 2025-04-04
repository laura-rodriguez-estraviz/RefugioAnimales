/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import aplicacion.Cita;
import aplicacion.Producto;
import aplicacion.Sucursal;
import aplicacion.Tienda;
import aplicacion.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class VComprar extends javax.swing.JFrame {

    /**
     * Creates new form VComprar
     */
    aplicacion.FachadaAplicacion fa;
    Usuario usr;
    
    
    public VComprar(aplicacion.FachadaAplicacion fa, Usuario usr) {
         this.setContentPane(new Fondo());
        initComponents();
        this.fa = fa;
        this.usr = usr;
        
        ModeloTablaProductosTienda mtpt = new ModeloTablaProductosTienda();
        ModeloTablaTiendas mtt = new ModeloTablaTiendas();
        ModeloTablaSucursalesComprar mtsc = new ModeloTablaSucursalesComprar();
        tbSucursales.setModel(mtsc);
        tbProducto.setModel(mtpt);
        tbTienda.setModel(mtt);
        
        tbSucursales.getSelectionModel().addListSelectionListener((lse) -> {
            int index = tbSucursales.getSelectedRow();
            if(index < 0) {
                ((ModeloTablaTiendas)tbTienda.getModel()).setFilas(new ArrayList<Tienda>());
                return;
            }
            
            Sucursal s = ((ModeloTablaSucursalesComprar)tbSucursales.getModel()).obtenerSucursal(index);
            java.util.List<Tienda> tiendas = fa.obtenerTiendas(textTienda.getText(), s.getNumSucursal());
            ((ModeloTablaTiendas)tbTienda.getModel()).setFilas(tiendas);
        });
        
        tbTienda.getSelectionModel().addListSelectionListener((lse) -> {
            int index = tbTienda.getSelectedRow();
            if(index < 0) {
                ((ModeloTablaProductosTienda)tbProducto.getModel()).setFilas(new ArrayList<Producto>());
                return;
            }
            int index2 = tbSucursales.getSelectedRow();
            if(index2 < 0)
                return;
            
            Sucursal s = ((ModeloTablaSucursalesComprar)tbSucursales.getModel()).obtenerSucursal(index2);
            Tienda t = ((ModeloTablaTiendas)tbTienda.getModel()).obtenerTienda(index);
            java.util.List<Producto> productos = fa.obtenerProductosNoTiendaConExistencias(s.getNumSucursal(), t.getNombre() , textProducto.getText());
            ((ModeloTablaProductosTienda)tbProducto.getModel()).setFilas(productos);
        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnBuscarS = new javax.swing.JButton();
        btnBuscarT = new javax.swing.JButton();
        btnBuscarP = new javax.swing.JButton();
        textSucursal = new javax.swing.JTextField();
        textTienda = new javax.swing.JTextField();
        textProducto = new javax.swing.JTextField();
        btnComprar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSucursales = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbTienda = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbProducto = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Sucursales:");

        jLabel2.setText("Nombre Tienda:");

        jLabel3.setText("Nombre Producto:");

        btnBuscarS.setText("Buscar");
        btnBuscarS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarSActionPerformed(evt);
            }
        });

        btnBuscarT.setText("Buscar");
        btnBuscarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTActionPerformed(evt);
            }
        });

        btnBuscarP.setText("Buscar");
        btnBuscarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPActionPerformed(evt);
            }
        });

        btnComprar.setText("Comprar");
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });

        tbSucursales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbSucursales);

        tbTienda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbTienda);

        tbProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tbProducto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel1))
                            .addComponent(textSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(textTienda)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBuscarP)
                                    .addComponent(btnComprar))
                                .addGap(17, 17, 17))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnBuscarS)
                        .addGap(101, 101, 101)
                        .addComponent(btnBuscarT)))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textTienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarP)
                    .addComponent(btnBuscarT)
                    .addComponent(btnBuscarS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnComprar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(192, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarSActionPerformed
        java.util.List<Sucursal> scrs = fa.obtenerSucursales("", textSucursal.getText());
        ((ModeloTablaSucursalesComprar)tbSucursales.getModel()).setFilas(scrs);
    }//GEN-LAST:event_btnBuscarSActionPerformed

    private void btnBuscarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTActionPerformed
        int index = tbSucursales.getSelectedRow();
            if(index < 0)
                return;
            
            Sucursal s = ((ModeloTablaSucursalesComprar)tbSucursales.getModel()).obtenerSucursal(index);
            java.util.List<Tienda> tiendas = fa.obtenerTiendas(textTienda.getText(), s.getNumSucursal());
            ((ModeloTablaTiendas)tbTienda.getModel()).setFilas(tiendas);
    }//GEN-LAST:event_btnBuscarTActionPerformed

    private void btnBuscarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPActionPerformed
        int index = tbTienda.getSelectedRow();
            if(index < 0)
                return;
            int index2 = tbSucursales.getSelectedRow();
            if(index2 < 0)
                return;
            
            Sucursal s = ((ModeloTablaSucursalesComprar)tbSucursales.getModel()).obtenerSucursal(index2);
            Tienda t = ((ModeloTablaTiendas)tbTienda.getModel()).obtenerTienda(index);
            java.util.List<Producto> productos = fa.obtenerProductosNoTiendaConExistencias(s.getNumSucursal(), t.getNombre() , textProducto.getText());
            ((ModeloTablaProductosTienda)tbProducto.getModel()).setFilas(productos);
    }//GEN-LAST:event_btnBuscarPActionPerformed

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarActionPerformed
        int index3 = tbProducto.getSelectedRow();
        if(index3 < 0) 
            return;
        int index = tbTienda.getSelectedRow();
        if(index < 0)
            return;
        int index2 = tbSucursales.getSelectedRow();
        if(index2 < 0)
            return;
        Sucursal s = ((ModeloTablaSucursalesComprar)tbSucursales.getModel()).obtenerSucursal(index2);
        Tienda t = ((ModeloTablaTiendas)tbTienda.getModel()).obtenerTienda(index);
        Producto p = ((ModeloTablaProductosTienda) tbProducto.getModel()).obtenerProducto(index3);
            
        int resultado = fa.comprar(t.getNombre(), p.getNombre(), s.getNumSucursal(), usr.getDni());
        if(resultado > 0) {
            java.util.List<Producto> productos = fa.obtenerProductosNoTiendaConExistencias(s.getNumSucursal(), t.getNombre() , textProducto.getText());
            ((ModeloTablaProductosTienda)tbProducto.getModel()).setFilas(productos);
        }
            
    }//GEN-LAST:event_btnComprarActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarP;
    private javax.swing.JButton btnBuscarS;
    private javax.swing.JButton btnBuscarT;
    private javax.swing.JButton btnComprar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbProducto;
    private javax.swing.JTable tbSucursales;
    private javax.swing.JTable tbTienda;
    private javax.swing.JTextField textProducto;
    private javax.swing.JTextField textSucursal;
    private javax.swing.JTextField textTienda;
    // End of variables declaration//GEN-END:variables
}
