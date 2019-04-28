package com.example.lenovo.lab.LogicaNeg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis Carrillo Rodriguez on 17/3/2018.
 */

public class Categoria implements Serializable {

    private String codigo, nombre;
    private List<Categoria> categorias;

    public Categoria() {
    }

    public Categoria(String codigo, String nombre, String titulo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categorias = new ArrayList<>();
    }

    public Categoria(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categorias = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public List<Categoria> getCursos() {
        return categorias;
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

    @Override
    public String toString() {
        return nombre;
        /*
        return "Categoria{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
                */
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categoria categoria = (Categoria) o;

        if (getCodigo() != null ? !getCodigo().equals(categoria.getCodigo()) : categoria.getCodigo() != null)
            return false;
        return getNombre() != null ? getNombre().equals(categoria.getNombre()) : categoria.getNombre() == null;
    }

    @Override
    public int hashCode() {
        int result = getCodigo() != null ? getCodigo().hashCode() : 0;
        result = 31 * result + (getNombre() != null ? getNombre().hashCode() : 0);
        return result;
    }
}