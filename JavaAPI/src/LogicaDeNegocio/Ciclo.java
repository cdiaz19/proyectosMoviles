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
public class Ciclo {
    private String id;
    private int anno;
    private int numero;
    private String fechaInicio;
    private String fechaFinal;
    
    public Ciclo() {
        anno = 2019;
        numero = new Random().nextInt(5);
        fechaInicio= "";
        fechaFinal= "";
    }

    public Ciclo(String id, int anno, int numero, String fechaInicio, String fechaFinal) {
        this.id = id;
        this.anno = anno;
        this.numero = numero;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    @Override
    public String toString() {
        return id + anno + numero + fechaInicio + fechaFinal;
    }
}
