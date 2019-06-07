/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import java.io.Serializable;

/**
 *
 * @author alejandro
 */
public class Cliente implements Serializable {
    
    private String nombre;
    int telefono;
    Usuario usuario;

    public Cliente(String nombre, int telefono, Usuario usuario) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", telefono=" + telefono + ", usuario=" + usuario + '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
   
    
}