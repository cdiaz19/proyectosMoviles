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

    public ModelData() {
        carreraList = new ArrayList<>();
        cursoList = new ArrayList<>();
        alumnoList = new ArrayList<>();
        profesorList = new ArrayList<>();
        cicloList = new ArrayList<>();
        grupoList = new ArrayList<>();
        prepareCarreraData();
        prepareAlumnoData();
        prepareCursoData();
        prepareCicloData();
        prepareProfesorData();
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

    public void prepareCarreraData() {
        Carrera carrera = new Carrera("EIF", "Ingenieria en sistemas");
        carrera.addCurso(new Curso("ST", "Soporte", 3, 4));
        carrera.addCurso(new Curso("FD", "Fundamentos", 3, 4));
        carrera.addCurso(new Curso("PG1", "Programacion I", 3, 4));
        carrera.addCurso(new Curso("PG2", "Programacion II", 3, 4));
        carrera.addCurso(new Curso("PG3", "Programacion III", 3, 4));
        carrera.addCurso(new Curso("PG4", "Programacion IV", 3, 4));
        carrera.addCurso(new Curso("EDA", "Estructuras Datos", 3, 4));
        carrera.addCurso(new Curso("EDI", "Estructuras Discretas", 3, 4));
        carrera.addCurso(new Curso("MV", "Moviles", 3, 4));
        carrera.addCurso(new Curso("PP", "Paradigmas", 3, 4));
        carrera.addCurso(new Curso("AQ", "Arquitectura", 3, 4));
        carrera.addCurso(new Curso("RD", "Redes", 3, 4));
        carreraList.add(carrera);

        carrera = new Carrera("ADM", "Administracion");
        carrera.addCurso(new Curso("FAD", "Fundamentos de Administracion", 3, 4));
        carrera.addCurso(new Curso("C1", "Contabilidad I", 3, 4));
        carreraList.add(carrera);

        carrera = new Carrera("FIS", "Fisica");
        carrera.addCurso(new Curso("FF", "Fundamentos de Fisica", 3, 4));
        carrera.addCurso(new Curso("F1", "Fisica I", 3, 4));
        carreraList.add(carrera);

        carrera = new Carrera("MAT", "Matematica");
        carrera.addCurso(new Curso("FM", "Fundamentos de Matematica", 3, 4));
        carrera.addCurso(new Curso("HB1", "Historia Basica I", 3, 4));
        carreraList.add(carrera);

        carrera = new Carrera("Star Trek", "Science Fiction");
        carrera.addCurso(new Curso("M1", "Matrix 1", 3, 4));
        carrera.addCurso(new Curso("BDR", "Blade Runner 2049", 3, 4));
        carreraList.add(carrera);

        carrera = new Carrera("The LEGO Movie", "Animation");
        carrera.addCurso(new Curso("ZOO", "Zootopia", 3, 4));
        carrera.addCurso(new Curso("VIN", "Vecinos Invasores", 3, 4));
        carreraList.add(carrera);

        carrera = new Carrera("Back to the Future", "Classics");
        carrera.addCurso(new Curso("TIT", "Titanic", 3, 4));
        carrera.addCurso(new Curso("DDA", "Donnie Darko", 3, 4));
        carreraList.add(carrera);

        carrera = new Carrera("3 Idiots", "Commedy");
        carrera.addCurso(new Curso("WDG", "War Dogs", 3, 4));
        carrera.addCurso(new Curso("ANT", "Ant Man", 3, 4));
        carreraList.add(carrera);

        carrera = new Carrera("Guardians of the Galaxy", "Science Fiction & Fantasy");
        carrera.addCurso(new Curso("JL", "Justice League", 3, 4));
        carrera.addCurso(new Curso("AVG", "Avengers", 3, 4));
        carreraList.add(carrera);


        carrera = new Carrera("Batman", "Action");
        carreraList.add(carrera);
    }

    public void prepareCursoData() {
        Curso curso = new Curso("ST", "Soporte", 3, 4);
        cursoList.add(curso);
        curso = new Curso("FD", "Fundamentos", 3, 4);
        cursoList.add(curso);
        curso = new Curso("PG1", "Programacion I", 3, 4);
        cursoList.add(curso);
        curso = new Curso("PG2", "Programacion II", 3, 4);
        cursoList.add(curso);
        curso = new Curso("PG3", "Programacion III", 3, 4);
        cursoList.add(curso);
        curso = new Curso("PG4", "Programacion IV", 3, 4);
        cursoList.add(curso);
        curso = new Curso("EDA", "Estructuras Datos", 3, 4);
        cursoList.add(curso);
        curso = new Curso("EDI", "Estructuras Discretas", 3, 4);
        cursoList.add(curso);

        curso = new Curso("MV", "Moviles", 3, 4);
        cursoList.add(curso);
        curso = new Curso("PP", "Paradigmas", 3, 4);
        cursoList.add(curso);

        curso = new Curso("AQ", "Arquitectura", 3, 4);
        cursoList.add(curso);

        curso = new Curso("RD", "Redes", 3, 4);
        cursoList.add(curso);
        // de adm
        curso = new Curso("FAD", "Fundamentos de Administracion", 3, 4);
        cursoList.add(curso);
        curso = new Curso("C1", "Contabilidad I", 3, 4);
        cursoList.add(curso);
        // de fisica
        curso = new Curso("FF", "Fundamentos de Fisica", 3, 4);
        cursoList.add(curso);
        curso = new Curso("F1", "Fisica I", 3, 4);
        cursoList.add(curso);
        // de matematica
        curso = new Curso("FM", "Fundamentos de Matematica", 3, 4);
        cursoList.add(curso);
        curso = new Curso("HB1", "Historia Basica I", 3, 4);
        cursoList.add(curso);
        // de science fiction
        curso = new Curso("M1", "Matrix 1", 3, 4);
        cursoList.add(curso);
        curso = new Curso("BDR", "Blade Runner 2049", 3, 4);
        cursoList.add(curso);
        // de animation
        curso = new Curso("ZOO", "Zootopia", 3, 4);
        cursoList.add(curso);
        curso = new Curso("VIN", "Vecinos Invasores", 3, 4);
        cursoList.add(curso);
        // de classics
        curso = new Curso("TIT", "Titanic", 3, 4);
        cursoList.add(curso);
        curso = new Curso("DDA", "Donnie Darko", 3, 4);
        cursoList.add(curso);
        // de commedy
        curso = new Curso("WDG", "War Dogs", 3, 4);
        cursoList.add(curso);
        curso = new Curso("ANT", "Ant Man", 3, 4);
        cursoList.add(curso);
        // de Science Fiction & Fantasy
        curso = new Curso("JL", "Justice League", 3, 4);
        cursoList.add(curso);
        curso = new Curso("AVG", "Avengers", 3, 4);
        cursoList.add(curso);
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

    public void prepareProfesorData() {
        Profesor profesor = new Profesor("123", "Jose", "@jose", 678);
        profesorList.add(profesor);

        profesor = new Profesor("234", "Juan", "@juan", 876);
        profesorList.add(profesor);

        profesor = new Profesor("345", "Mario", "@mario", 789);
        profesorList.add(profesor);

        profesor = new Profesor("456", "Jesus", "@Jesus", 978);
        profesorList.add(profesor);
    }

    public void prepareCicloData() {
        Ciclo ciclo = new Ciclo(2018, "Primer", "12/02", "12/06");
        cicloList.add(ciclo);

        ciclo = new Ciclo(2018, "Segundo", "12/07", "12/11");
        cicloList.add(ciclo);

        ciclo = new Ciclo(2017, "Primer", "12/02", "12/06");
        cicloList.add(ciclo);

        ciclo = new Ciclo(2017, "Segundo", "12/07", "12/11");
        cicloList.add(ciclo);

        ciclo = new Ciclo(2016, "Primer", "12/02", "12/06");
        cicloList.add(ciclo);

        ciclo = new Ciclo(2016, "Segundo", "12/07", "12/11");
        cicloList.add(ciclo);
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
