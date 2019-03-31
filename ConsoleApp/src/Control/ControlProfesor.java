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
import java.util.LinkedList;

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
}  
