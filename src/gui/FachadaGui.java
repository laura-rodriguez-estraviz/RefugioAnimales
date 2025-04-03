/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import aplicacion.Usuario;
import aplicacion.Voluntario;

/**
 *
 * @author alumno
 */
public class FachadaGui {
    private aplicacion.FachadaAplicacion fa;
    private VAutentificacion va;
    private VPrincipal vp;
    
    
    
   public FachadaGui(aplicacion.FachadaAplicacion fa){
     this.fa=fa;
     this.va = new VAutentificacion(fa);
   }
    
    
    public void iniciaVista(){
    
      va.setVisible(true);
    }
    
    
    public void muestraExcepcion(String txtExcepcion){
       VAviso vaviso;
       
       vaviso = new VAviso(va, true, txtExcepcion);
       vaviso.setVisible(true);
    }
    
    
    public void abrirVProductosTienda(String tienda, int numSucursal){
        VProductosTienda ventanaProductosTienda;
        ventanaProductosTienda=new VProductosTienda(vp,true,tienda,numSucursal, fa );
        ventanaProductosTienda.setVisible(true);
        
    }
    public void abrirVTiendas(int numSucursal){
        VTiendas ventanaTienda;
        ventanaTienda=new VTiendas(vp,true, fa,numSucursal );
        ventanaTienda.setVisible(true);
        
    }
    
    public void abrirVentanaPrincipal( Usuario usuario){
        
        vp=new VPrincipal(fa,usuario);
        vp.setVisible(true);
    }
    
    public void nuevaTareaVoluntarios(javax.swing.JFrame padre,Voluntario v){
        VNuevaTareaV vnt;
        vnt = new VNuevaTareaV(padre,true,fa,v);
        vnt.setVisible(true);
    }
   
}
