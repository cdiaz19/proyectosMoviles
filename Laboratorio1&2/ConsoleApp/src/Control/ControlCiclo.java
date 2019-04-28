/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import AccesoADatos.ServicioCiclo;
import LogicaDeNegocio.Ciclo;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro
 */
public class ControlCiclo {
    
    ServicioCiclo curso;
    
    public ServicioCiclo getCiclo() {
        return curso;
    }
    
    public void setCiclo(ServicioCiclo ciclo) {
        this.curso = ciclo;
    }
    
    public ControlCiclo() {
        this.curso = ServicioCiclo.getInstancia();
    }
    
    public LinkedList listar() throws GlobalException, NoDataException {
        LinkedList lista = this.curso.listarCiclos();
        return lista;
    }
    
    public LinkedList buscarPorId(String id) throws GlobalException, NoDataException {
        LinkedList lista = this.curso.buscarPorCiclo(id);
        return lista;
    }
    
    public void modificarCiclo(Ciclo ciclo) {
        
        try {
            this.curso.modificarCiclo(ciclo);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ControlProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertarCiclo(Ciclo ciclo) {
        try {
            this.curso.insertarCiclo(ciclo);
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
