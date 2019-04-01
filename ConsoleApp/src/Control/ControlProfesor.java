/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import AccesoADatos.ServicioProfesor;
import LogicaDeNegocio.Profesor;
import LogicaDeNegocio.Usuario;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro
 */
public class ControlProfesor {

    ServicioProfesor profesor;

    public ServicioProfesor getProfesor() {
        return profesor;
    }

    public void setProfesor(ServicioProfesor profesor) {
        this.profesor = profesor;
    }

    public ControlProfesor() {
        this.profesor = ServicioProfesor.getInstancia();
    }

    public LinkedList listar() throws GlobalException, NoDataException {
        LinkedList lista = this.profesor.listarProfesores();
        return lista;
    }

    public LinkedList buscarPorCedula(String cedula) throws GlobalException, NoDataException {
        LinkedList lista = this.profesor.buscarporCedula(cedula);
        return lista;
    }

    public void modificarProfesor(Profesor profe, Usuario usu) {

        try {
            this.profesor.modificarProfesor(profe, usu);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ControlProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertarProfesor(Profesor profe, Usuario usu) {
        try {
            this.profesor.insertarProfesor(profe, usu);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ControlProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(String id) {
        try {
            this.profesor.eliminar(id);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ControlProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
