/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import java.util.Random;

/**
 *
 * @author cdiaz
 */
public class Curso {
    private int id;
    private String codigo;
    private String nombre;
    private int creditos;
    private int horasSemanales;

    public Curso() {
        codigo = "";
        nombre = "";
        creditos = new Random().nextInt(3);
        horasSemanales = new Random().nextInt(12);
    }

    public Curso(int id, String codigo, String nombre, int creditos, int horasSemanales) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.horasSemanales = horasSemanales;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(int horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    @Override
    public String toString() {
        return id + codigo + nombre + creditos + horasSemanales;
    }    
}
