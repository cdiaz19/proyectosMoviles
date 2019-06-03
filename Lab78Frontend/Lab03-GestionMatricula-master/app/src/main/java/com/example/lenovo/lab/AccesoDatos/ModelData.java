package com.example.lenovo.lab.AccesoDatos;

import com.example.lenovo.lab.LogicaNeg.Alumno;
import com.example.lenovo.lab.LogicaNeg.Carrera;
import com.example.lenovo.lab.LogicaNeg.Ciclo;
import com.example.lenovo.lab.LogicaNeg.Curso;
import com.example.lenovo.lab.LogicaNeg.Grupo;
import com.example.lenovo.lab.LogicaNeg.Profesor;
import com.example.lenovo.lab.LogicaNeg.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 26/03/2018.
 */

public class ModelData {

    private List<Carrera> carreraList;
    private List<Curso> cursoList;
    private List<Alumno> alumnoList;
    private List<Profesor> profesorList;
    private List<Ciclo> cicloList;
    private List<Grupo> grupoList;


    public ModelData(List<Ciclo> ciclos,List<Carrera> carreras,List<Curso> cursos,List<Profesor> profesores) {
        carreraList = new ArrayList<>();
        cursoList = new ArrayList<>();
        alumnoList = new ArrayList<>();
        profesorList = new ArrayList<>();
        cicloList = new ArrayList<>();
        grupoList = new ArrayList<>();
        prepareCarreraData(carreras);
        prepareAlumnoData();
        prepareCursoData(cursos);
        prepareCicloData(ciclos);
        prepareProfesorData(profesores);
        prepareGrupoData();
    }

    public ModelData() {
        carreraList = new ArrayList<>();
        cursoList = new ArrayList<>();
        alumnoList = new ArrayList<>();
        profesorList = new ArrayList<>();
        cicloList = new ArrayList<>();
        grupoList = new ArrayList<>();
        //prepareCarreraData();
        prepareAlumnoData();
        //prepareCursoData();
        //prepareCicloData();
       // prepareProfesorData();
        prepareGrupoData();
    }

    public void prepareGrupoData() {
        // return numero + " ciclo - " + año;
        ArrayList<String> estudiantes = new ArrayList<>();
        ArrayList<Double> notas = new ArrayList<>();
        estudiantes.add("123");
        estudiantes.add("321");
        estudiantes.add("234");
        notas.add(9.5);
        notas.add(9.3);
        notas.add(9.1);
        Ciclo ciclo1 = new Ciclo(2018, "Primer");
        Ciclo ciclo2 = new Ciclo(2017, "Primer");
        Ciclo ciclo3 = new Ciclo(2017, "Segundo");
        Grupo grupo = new Grupo(ciclo1, "Soporte", "1801", "M 9AM-11:40AM", "Jose", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(ciclo1, "Fundamentos", "0610", "M&V 8AM-9:40AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(ciclo2, "Programacion I", "0620", "M&V 8AM-9:40AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(ciclo2, "Programacion II", "0144", "L&J 8AM-9:40AM", "Mario", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(ciclo3, "Programacion III", "0551", "M&V 8AM-9:40AM", "Jesus", estudiantes, notas);
        grupoList.add(grupo);
        notas.set(1, 7.5);
        notas.set(0, 2.5);
        notas.set(2, 8.5);
        grupo = new Grupo(ciclo3, "Programacion IV", "0111", "I 8AM-11:20AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(ciclo1, "Fundamentos", "0620", "M&V 8AM-9:40AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(ciclo3, "Estructuras Datos", "0121", "M&V 8AM-9:40AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(ciclo1, "Estructuras Discretas", "0351", "M&V 8AM-9:40AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(ciclo3, "Moviles", "0178", "M&V 1PM-2:40PM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        notas.set(1, 9.0);
        notas.set(0, 8.5);
        notas.set(2, 8.7);
        grupo = new Grupo(ciclo1, "Paradigmas", "0112", "M&V 8AM-9:40AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(ciclo1, "Arquitectura", "7701", "M&V 8AM-9:40AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(ciclo2, "Redes", "0771", "M&V 8AM-9:40AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(ciclo2, "Paradigmas", "0113", "M&V 10AM-11:40AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(ciclo2, "Paradigmas", "0114", "M&V 1PM-2:40PM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
    }

    public void prepareCarreraData(List<Carrera> carreras) {
        if (carreras != null) {
            for (int i = 0; i < carreras.size(); i++) {
                Carrera carrera = new Carrera(carreras.get(i).getCodigo(),carreras.get(i).getNombre(),carreras.get(i).getTitulo());
                carreraList.add(carrera);
            }
        }
    }

    public void prepareCursoData(List<Curso> cursos) {
        if (cursos != null) {
            for (int i = 0; i < cursos.size(); i++) {
                Carrera carrera= new Carrera(cursos.get(i).getCarrera().getCodigo(),cursos.get(i).getCarrera().getNombre(),null);
                Curso curso = new Curso(cursos.get(i).getCodigo(),cursos.get(i).getNombre(), cursos.get(i).getCreditos(),cursos.get(i).getHoras(),carrera);
                cursoList.add(curso);
            }

        }
    }

    public void prepareAlumnoData() {
        Alumno alumno = new Alumno("123", "Jose", 321, "@Jose", "23/06/1985", "Ingenieria en sistemas");
        alumnoList.add(alumno);

        alumno = new Alumno("321", "Juan", 213, "@Juan", "24/06/1986", "Administracion de empresas");
        alumnoList.add(alumno);

        alumno = new Alumno("234", "Miguel", 3241, "@Miguel", "25/06/1987", "Relaciones internacionales");
        alumnoList.add(alumno);

        alumno = new Alumno("345", "Manuel", 3251, "@Manuel", "26/06/1988", "Ingenieria industrial");
        alumnoList.add(alumno);

        alumno = new Alumno("456", "Luis", 567, "@Luis", "27/06/1989", "Ingenieria agricola");
        alumnoList.add(alumno);

        alumno = new Alumno("567", "Alberto", 8765, "@Alberto", "28/06/1990", "Ingenieria civil");
        alumnoList.add(alumno);
    }

    public void prepareProfesorData(List<Profesor> profesores) {
        if (profesores != null) {
            for (int i = 0; i < profesores.size(); i++) {
                Profesor profesor = new Profesor(profesores.get(i).getCedula(), profesores.get(i).getNombre(), profesores.get(i).getEmail(), profesores.get(i).getTelefono());
                profesorList.add(profesor);

            }


        }
    }

    public void prepareCicloData(List<Ciclo> ciclos) {
        if (ciclos != null) {
            for (int i = 0; i < ciclos.size(); i++) {
                System.out.println(ciclos.get(0).getFinicio());
                Ciclo ciclo = new Ciclo(ciclos.get(i).getCodigo(), ciclos.get(i).getAño(), ciclos.get(i).getNumero(), ciclos.get(i).getFinicio(), ciclos.get(i).getFfinal());
                cicloList.add(ciclo);


            }
        }
    }
    public List<Carrera> getCarreraList() {
        return carreraList;
    }

    public List<Curso> getCursoList() {
        return cursoList;
    }

    public List<Alumno> getAlumnoList() {
        return alumnoList;
    }

    public List<Profesor> getProfesorList() {
        return profesorList;
    }

    public List<Ciclo> getCicloList() {
        return cicloList;
    }

    public List<Grupo> getGrupoList() {
        return grupoList;
    }

    public void setGrupoList(List<Grupo> grupoList) {
        this.grupoList = grupoList;
    }

    public void setCarreraList(List<Carrera> carreraList) {
        this.carreraList = carreraList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    public void setAlumnoList(List<Alumno> alumnoList) {
        this.alumnoList = alumnoList;
    }

    public void setProfesorList(List<Profesor> profesorList) {
        this.profesorList = profesorList;
    }

    public void setCicloList(List<Ciclo> cicloList) {
        this.cicloList = cicloList;
    }

    public List<Usuario> getUsuariosList() {
        List<Usuario> users = new ArrayList<>();
        users.add(new Usuario("@admin", "admin", "administrador", "111"));
        users.add(new Usuario("@admin2", "admin", "administrador", "222"));
        users.add(new Usuario("@matric", "matric", "matriculador", "333"));
        users.add(new Usuario("@matric1", "matric", "matriculador", "444"));
        users.add(new Usuario("@matric2", "matric", "matriculador", "555"));
        users.add(new Usuario("@matric3", "matric", "matriculador", "555"));
        return users;
    }
}
