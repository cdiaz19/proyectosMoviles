/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import AccesoADatos.ServicioCurso;
import LogicaDeNegocio.Curso;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro
 */
public class ControlCurso {
    
    ServicioCurso curso;
    
    public ServicioCurso getCurso() {
        return curso;
    }
    
    public void setCurso(ServicioCurso curso) {
        this.curso = this.curso;
    }
    
    public ControlCurso() {
        this.curso = ServicioCurso.getInstancia();
    }
    
    public LinkedList listar() throws GlobalException, NoDataException {
        LinkedList lista = this.curso.listarCursos();
        return lista;
    }
    
    public LinkedList buscarPorCodigo(String id) throws GlobalException, NoDataException {
        LinkedList lista = this.curso.buscarPorCodigo(id);
        return lista;
    }
    
    public void modificarCurso(Curso curso) {
        
        try {
            this.curso.modificarCurso(curso);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ControlProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertarCurso(Curso curso) {
        try {
            this.curso.insertarCurso(curso);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ControlProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminar(String id) {
        try {
            this.curso.eliminar(id);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ControlProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
