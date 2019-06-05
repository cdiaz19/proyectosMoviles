package com.example.lenovo.lab.LogicaNeg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis Carrillo Rodriguez on 17/3/2018.
 */

public class Carrera implements Serializable {

    private String codigo, nombre, titulo;
    private List<Curso> cursos;

    public Carrera() {
    }

    public Carrera(String codigo, String nombre, String titulo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.titulo = titulo;
        this.cursos = new ArrayList<>();
    }

    public Carrera(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.titulo = "Bachiller";
        this.cursos = new ArrayList<>();
    }

    public void addCurso(Curso curso){
        this.cursos.add(curso);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public List<Curso> getCursos() {
        return cursos;
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
        return "Carrera{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", titulo='" + titulo + '\'' +
                '}';
                */
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Carrera carrera = (Carrera) o;

        if (getCodigo() != null ? !getCodigo().equals(carrera.getCodigo()) : carrera.getCodigo() != null)
            return false;
        if (getNombre() != null ? !getNombre().equals(carrera.getNombre()) : carrera.getNombre() != null)
            return false;
        return getTitulo() != null ? getTitulo().equals(carrera.getTitulo()) : carrera.getTitulo() == null;
    }

    @Override
    public int hashCode() {
        int result = getCodigo() != null ? getCodigo().hashCode() : 0;
        result = 31 * result + (getNombre() != null ? getNombre().hashCode() : 0);
        result = 31 * result + (getTitulo() != null ? getTitulo().hashCode() : 0);
        return result;
    }
}