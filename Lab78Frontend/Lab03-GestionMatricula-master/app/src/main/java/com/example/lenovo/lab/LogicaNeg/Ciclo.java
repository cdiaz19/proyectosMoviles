package com.example.lenovo.lab.LogicaNeg;

import java.io.Serializable;

/**
 * Created by User on 24/03/2018.
 */

public class Ciclo implements Serializable {
    private String codigo;
    private int anno;
    private String numero;
    private String fechaIncio;
    private String fechaFinal;

    public Ciclo(String codigo, int anno, String numero, String fechaIncio, String fechaFinal) {
        this.codigo=codigo;
        this.anno = anno;
        this.numero = numero;
        this.fechaIncio = fechaIncio;
        this.fechaFinal = fechaFinal;
    }

    public Ciclo(int anno, String numero) {
        this.codigo="123";
        this.anno = anno;
        this.numero = numero;
        this.fechaIncio = "";
        this.fechaFinal = "";
    }

    public String getCodigo(){return codigo;}
    public void setCodigo(String codigo){this.codigo=codigo;}
    public int getAño() {
        return anno;
    }

    public void setAño(int anno) {
        this.anno = anno;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFinicio() {
        return fechaIncio;
    }

    public void setFinicio(String fechaInicio) {
        this.fechaIncio = fechaInicio;
    }

    public String getFfinal() {
        return fechaFinal;
    }

    public void setFfinal(String fechaFinal) {
        this.fechaFinal =fechaFinal;
    }

    @Override
    public String toString() {
        return numero + " ciclo - " + anno;
        /*
        return "Ciclo{" +
                "año=" + año +
                ", numero='" + numero + '\'' +
                ", finicio='" + finicio + '\'' +
                ", ffinal='" + ffinal + '\'' +
                '}';
                */
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ciclo ciclo = (Ciclo) o;

        if (getAño() != ciclo.getAño()) return false;
        return getNumero() != null ? getNumero().equals(ciclo.getNumero()) : ciclo.getNumero() == null;
    }

    @Override
    public int hashCode() {
        int result = getAño();
        result = 31 * result + (getNumero() != null ? getNumero().hashCode() : 0);
        result = 31 * result + (getFinicio() != null ? getFinicio().hashCode() : 0);
        result = 31 * result + (getFfinal() != null ? getFfinal().hashCode() : 0);
        return result;
    }
}
