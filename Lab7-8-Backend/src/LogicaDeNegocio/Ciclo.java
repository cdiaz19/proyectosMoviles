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
public class Ciclo implements Serializable {

    private String codigo;
    private int anno;
    private int numero;
    private String fechaIncio;
    private String fechaFinal;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getFechaIncio() {
        return fechaIncio;
    }

    public void setFechaIncio(String fechaIncio) {
        this.fechaIncio = fechaIncio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }


    public Ciclo(String codigo,int anno, int numero, String fechaIncio, String fechaFinal) {
        this.codigo = codigo;
        this.anno = anno;
        this.numero = numero;
        this.fechaIncio = fechaIncio;
        this.fechaFinal = fechaFinal;
    }
    
    @Override
    public String toString() {
        return "Ciclo{" + "codigo=" + codigo + ", anno=" + anno + ", numero=" + numero + ", fechaIncio=" + fechaIncio + ", fechaFinal=" + fechaFinal + '}';
    }

}
