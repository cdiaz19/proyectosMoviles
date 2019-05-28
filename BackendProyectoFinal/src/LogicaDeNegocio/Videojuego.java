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
public class Videojuego implements Serializable {
    private String codigoJuego;
    private String nombre;
    private int cantidad;
    private int precio;
    private String rentor;
    private String plazo;
    private String empresa;
    private Categoria categoria;

    @Override
    public String toString() {
        return  "codigoJuego=" + codigoJuego + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio + ", rentor=" + rentor + ", plazo=" + plazo + ", empresa=" + empresa + ", categoria=" + getCategoria().getNombre() ;
    }

    public Videojuego(String codigoJuego, String nombre, int cantidad, int precio, String rentor, String plazo, String empresa, Categoria categoria) {
        this.codigoJuego = codigoJuego;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.rentor = rentor;
        this.plazo = plazo;
        this.empresa = empresa;
        this.categoria = categoria;
    }

    public String getCodigoJuego() {
        return codigoJuego;
    }

    public void setCodigoJuego(String codigoJuego) {
        this.codigoJuego = codigoJuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getRentor() {
        return rentor;
    }

    public void setRentor(String rentor) {
        this.rentor = rentor;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
    
    
}
