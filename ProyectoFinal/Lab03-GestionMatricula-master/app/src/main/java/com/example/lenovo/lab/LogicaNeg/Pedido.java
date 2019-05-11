package com.example.lenovo.lab.LogicaNeg;

import java.io.Serializable;

/**
 * Created by User on 24/03/2018.
 */

public class Pedido implements Serializable {

    private int cantidad;
    private String nombre;
    private String rentor;
    private String precio;

    public Pedido(int cantidad, String nombre, String rentor, String precio) {
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.rentor = rentor;
        this.precio = precio;
    }

    public Pedido(int cantidad, String nombre) {
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.rentor = "";
        this.precio = "";
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRentor() {
        return rentor;
    }

    public void setRentor(String rentor) {
        this.rentor = rentor;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "cantidad=" + cantidad +
                ", nombre='" + nombre + '\'' +
                ", rentor='" + rentor + '\'' +
                ", precio='" + precio + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pedido pedido = (Pedido) o;

        if (getCantidad() != pedido.getCantidad()) return false;
        return getNombre() != null ? getNombre().equals(pedido.getNombre()) : pedido.getNombre() == null;
    }

    @Override
    public int hashCode() {
        int result = getCantidad();
        result = 31 * result + (getNombre() != null ? getNombre().hashCode() : 0);
        result = 31 * result + (getPrecio() != null ? getPrecio().hashCode() : 0);
        result = 31 * result + (getRentor() != null ? getRentor().hashCode() : 0);
        return result;
    }
}
