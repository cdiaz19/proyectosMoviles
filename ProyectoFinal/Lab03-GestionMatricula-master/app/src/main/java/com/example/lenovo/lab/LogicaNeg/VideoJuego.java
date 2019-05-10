package com.example.lenovo.lab.LogicaNeg;

import java.io.Serializable;

/**
 * Created by User on 21/03/2018.
 */

public class VideoJuego implements Serializable{

    private String codigo;
    private String nombre;
    private int precio;
    private String rentor;
    private int plazo;
    private String empresa;
    private Categoria categoria;

    public VideoJuego(String codigo, String nombre, int precio, String rentor, int plazo, String empresa, Categoria categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.rentor = rentor;
        this.plazo = plazo;
        this.empresa = empresa;
        this.categoria = categoria;
    }

    public VideoJuego() {

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
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

    @Override
    public String toString() {
        return "VideoJuego{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", rentor=" + rentor +
                ", plazo=" + plazo +
                ", empresa=" + empresa +
                ", categoria=" + categoria +
                '}';
    }
}
