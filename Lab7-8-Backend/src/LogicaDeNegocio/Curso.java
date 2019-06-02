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
public class Curso implements Serializable {
    
    private String codigo;
    private String nombre;
    private int creditos;
    private int horas;   
    private Carrera carrera;

    @Override
    public String toString() {
        return "Curso{" + "codigo=" + codigo + ", nombre=" + nombre + ", creditos=" + creditos + ", horas=" + horas + ", codigo_carrera=" + carrera.getCodigo() + ", nombre_carrera=" + carrera.getNombre() + '}';
    }

    public Curso(String codigo, String nombre, int creditos, int horas, Carrera carrera) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.horas = horas;
        this.carrera = carrera;
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

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
    
    
    
}
