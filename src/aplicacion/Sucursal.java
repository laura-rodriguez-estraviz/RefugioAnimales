/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;
import java.util.Objects;
/**
 *
 * @author alumnogreibd
 */
public class Sucursal {
    int numSucursal;
    String municipio;

    public Sucursal(int numSucursal, String municipio) {
        this.numSucursal = numSucursal;
        this.municipio = municipio;
    }
    
     public Sucursal(int numSucursal) {
        this.numSucursal = numSucursal;
    }
    
    

    public int getNumSucursal() {
        return numSucursal;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setNumSucursal(int numSucursal) {
        this.numSucursal = numSucursal;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    
    
    
    @Override
    public String toString() {
        return this.municipio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.municipio);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sucursal other = (Sucursal) obj;
        return Objects.equals(this.municipio, other.municipio);
    }
    
    
    
}
