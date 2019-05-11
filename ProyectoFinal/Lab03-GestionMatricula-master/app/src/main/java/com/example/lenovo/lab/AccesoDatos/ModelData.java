package com.example.lenovo.lab.AccesoDatos;

import com.example.lenovo.lab.LogicaNeg.Cliente;
import com.example.lenovo.lab.LogicaNeg.Categoria;
import com.example.lenovo.lab.LogicaNeg.Pedido;
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
    private List<Pedido> pedidoList;
    private List<Grupo> grupoList;

    public ModelData() {
        categoriaList = new ArrayList<>();
        videoJuegoList = new ArrayList<>();
        clienteList = new ArrayList<>();
        profesorList = new ArrayList<>();
        pedidoList = new ArrayList<>();
        grupoList = new ArrayList<>();
        prepareCategoriaData();
        prepareClienteData();
        prepareCursoData();
        preparePedidoData();
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
        Pedido pedido1 = new Pedido(2018, "Primer");
        Pedido pedido2 = new Pedido(2017, "Primer");
        Pedido pedido3 = new Pedido(2017, "Segundo");
        Grupo grupo = new Grupo(pedido1, "Soporte", "1801", "M 9AM-11:40AM", "Jose", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(pedido1, "Fundamentos", "0610", "M&V 8AM-9:40AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(pedido2, "Programacion I", "0620", "M&V 8AM-9:40AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(pedido2, "Programacion II", "0144", "L&J 8AM-9:40AM", "Mario", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(pedido3, "Programacion III", "0551", "M&V 8AM-9:40AM", "Jesus", estudiantes, notas);
        grupoList.add(grupo);
        notas.set(1, 7.5);
        notas.set(0, 2.5);
        notas.set(2, 8.5);
        grupo = new Grupo(pedido3, "Programacion IV", "0111", "I 8AM-11:20AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(pedido1, "Fundamentos", "0620", "M&V 8AM-9:40AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(pedido3, "Estructuras Datos", "0121", "M&V 8AM-9:40AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(pedido1, "Estructuras Discretas", "0351", "M&V 8AM-9:40AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(pedido3, "Moviles", "0178", "M&V 1PM-2:40PM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        notas.set(1, 9.0);
        notas.set(0, 8.5);
        notas.set(2, 8.7);
        grupo = new Grupo(pedido1, "Paradigmas", "0112", "M&V 8AM-9:40AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(pedido1, "Arquitectura", "7701", "M&V 8AM-9:40AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(pedido2, "Redes", "0771", "M&V 8AM-9:40AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(pedido2, "Paradigmas", "0113", "M&V 10AM-11:40AM", "Juan", estudiantes, notas);
        grupoList.add(grupo);
        grupo = new Grupo(pedido2, "Paradigmas", "0114", "M&V 1PM-2:40PM", "Juan", estudiantes, notas);
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

        VideoJuego videoJuego = new VideoJuego("GTAV", "Grand Theaft Auto V", 35000, "", 0, "RockStart", categoria1);
        videoJuegoList.add(videoJuego);
        VideoJuego videoJuego1 = new VideoJuego("GTAII", "Grand Theaft Auto II", 10000, "", 0, "RockStart", categoria1);
        videoJuegoList.add(videoJuego1);
        VideoJuego videoJuego2 = new VideoJuego("GTAIV", "Grand Theaft Auto IV", 25000, "", 0, "RockStart", categoria1);
        videoJuegoList.add(videoJuego2);

        Categoria categoria2 = new Categoria("STR", "Estrategia");
        VideoJuego videoJuego3 = new VideoJuego("SPO", "Spoling", 35000, "", 0, "Test", categoria2);
        videoJuegoList.add(videoJuego3);

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

    public void preparePedidoData() {
        Pedido pedido = new Pedido(2, "GTAV", "Alejandro Gamboa", "70000");
        pedidoList.add(pedido);

        pedido = new Pedido(1, "GTAIV", "Jose Gamboa", "25000");
        pedidoList.add(pedido);

        pedido = new Pedido(2, "SPOOLING", "Carlos Gamboa", "35000");
        pedidoList.add(pedido);

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

    public List<Pedido> getPedidoList() {
        return pedidoList;
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

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    public List<Usuario> getUsuariosList() {
        List<Usuario> users = new ArrayList<>();
        users.add(new Usuario("@admin", "admin", "administrador", "111"));
//        users.add(new Usuario("@admin2", "admin", "administrador", "222"));
//        users.add(new Usuario("@matric", "matric", "matriculador", "333"));
//        users.add(new Usuario("@matric1", "matric", "matriculador", "444"));
//        users.add(new Usuario("@matric2", "matric", "matriculador", "555"));
//        users.add(new Usuario("@matric3", "matric", "matriculador", "555"));
        return users;
    }
}
