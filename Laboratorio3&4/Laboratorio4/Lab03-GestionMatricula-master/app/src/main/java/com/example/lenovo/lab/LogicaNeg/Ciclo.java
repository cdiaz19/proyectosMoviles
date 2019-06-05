package com.example.lenovo.lab.LogicaNeg;

import java.io.Serializable;

/**
 * Created by User on 24/03/2018.
 */

public class Ciclo implements Serializable {

    private int año;
    private String numero;
    private String finicio;
    private String ffinal;

    public Ciclo(int año, String numero, String finicio, String ffinal) {
        this.año = año;
        this.numero = numero;
        this.finicio = finicio;
        this.ffinal = ffinal;
    }

    public Ciclo(int año, String numero) {
        this.año = año;
        this.numero = numero;
        this.finicio = "";
        this.ffinal = "";
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFinicio() {
        return finicio;
    }

    public void setFinicio(String finicio) {
        this.finicio = finicio;
    }

    public String getFfinal() {
        return ffinal;
    }

    public void setFfinal(String ffinal) {
        this.ffinal = ffinal;
    }

    @Override
    public String toString() {
        return numero + " ciclo - " + año;
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
