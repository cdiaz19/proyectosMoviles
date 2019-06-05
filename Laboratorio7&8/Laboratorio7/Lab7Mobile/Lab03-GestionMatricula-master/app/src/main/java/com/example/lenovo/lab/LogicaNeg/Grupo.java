package com.example.lenovo.lab.LogicaNeg;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by HsingPC on 26/3/2018.
 */

public class Grupo implements Serializable {

    public Grupo(Ciclo ciclo, String curso, String numero, String horario, String profesor, ArrayList<String> estudiantes, ArrayList<Double> notas) {
        this.ciclo = ciclo;
        this.curso = curso;
        this.numero = numero;
        this.horario = horario;
        this.profesor = profesor;
        this.estudiantes = estudiantes;
        this.notas = notas;
    }

    public Grupo() {
        this.ciclo = null;
        this.curso = "";
        this.numero = "";
        this.horario = "";
        this.profesor = "";
        this.estudiantes = null;
        this.notas = null;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public ArrayList<String> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<String> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public ArrayList<Double> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Double> notas) {
        this.notas = notas;
    }

    public void setNota(Double n, int pos){
        this.notas.set(pos, n);
    }

    @Override
    public String toString() {
        return numero + " - " + curso;
        /*
        return "Grupo{" +
                "ciclo=" + ciclo +
                ", curso='" + curso + '\'' +
                ", numero='" + numero + '\'' +
                ", horario='" + horario + '\'' +
                ", profesor='" + profesor + '\'' +
                ", estudiantes=" + estudiantes +
                ", notas=" + notas +
                '}';
                */
    }
    public void eliminarAlumno(String ced){
        for(int i=0; i<estudiantes.size();i++){
            if(estudiantes.get(i).compareTo(ced)==0){
                estudiantes.remove(i);
                notas.remove(i);
            }
        }
    }
    public void agregarAlumno(String cedula, double nota){
        estudiantes.add(cedula);
        notas.add(nota);
    }
    public boolean existe(String cedula){
        for(String i: estudiantes)
            if (i.compareTo(cedula)==0)
                return true;
        return false;
    }
    public String getNota(String ced){
        for(int i=0; i<estudiantes.size();i++)
            if(estudiantes.get(i).equals(ced))
                return Double.toString(notas.get(i));
        return "0";
    }

    private Ciclo ciclo;
    private String curso;
    private String numero;
    private String horario;
    private String profesor;
    private ArrayList<String> estudiantes;
    private ArrayList<Double> notas;
}
