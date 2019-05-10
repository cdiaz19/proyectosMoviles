package com.example.lenovo.lab.AccesoDatos;

import com.example.lenovo.lab.LogicaNeg.Cliente;
import com.example.lenovo.lab.LogicaNeg.Categoria;
import com.example.lenovo.lab.LogicaNeg.Ciclo;
import com.example.lenovo.lab.LogicaNeg.VideoJuego;
import com.example.lenovo.lab.LogicaNeg.Grupo;
import com.example.lenovo.lab.LogicaNeg.Profesor;
import com.example.lenovo.lab.LogicaNeg.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 26/03/2018.
 */

public class ModelData {

    private List<Categoria> categoriaList;
    private List<VideoJuego> videoJuegoList;
    private List<Cliente> clienteList;
    private List<Profesor> profesorList;
    private List<Ciclo> cicloList;
    private List<Grupo> grupoList;

    public ModelData() {
        categoriaList = new ArrayList<>();
        videoJuegoList = new ArrayList<>();
        clienteList = new ArrayList<>();
        profesorList = new ArrayList<>();
        cicloList = new ArrayList<>();
        grupoList = new ArrayList<>();
        prepareCategoriaData();
        prepareClienteData();
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

    public void prepareCategoriaData() {
        Categoria categoria = new Categoria("ACC", "Accion");
        categoriaList.add(categoria);

        categoria = new Categoria("SHO", "Disparos/Shooter");
        categoriaList.add(categoria);

        categoria = new Categoria("STR", "Estrategia");
        categoriaList.add(categoria);

        categoria = new Categoria("SIN", "Simulacion");
        categoriaList.add(categoria);

        categoria = new Categoria("SPO", "Deportes");
        categoriaList.add(categoria);
    }

    public void prepareCursoData() {
        Categoria categoria1 = new Categoria("ACC", "Accion");

        VideoJuego videoJuego = new VideoJuego("GTAV", "Grand Theaft Auto V", 350000, "", 0, "RockStart", categoria1);
        videoJuegoList.add(videoJuego);

    }

    public void prepareClienteData() {
        Cliente cliente = new Cliente("123", "Jose", 321, "@Jose");
        clienteList.add(cliente);

        cliente = new Cliente("321", "Juan", 213, "@Juan");
        clienteList.add(cliente);

        cliente = new Cliente("234", "Miguel", 3241, "@Miguel");
        clienteList.add(cliente);
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

    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public List<VideoJuego> getVideoJuegoList() {
        return videoJuegoList;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
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

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    public void setVideoJuegoList(List<VideoJuego> videoJuegoList) {
        this.videoJuegoList = videoJuegoList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
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
