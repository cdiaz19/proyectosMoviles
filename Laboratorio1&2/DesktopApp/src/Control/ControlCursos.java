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
import LogicaNegocio.ModelCurso;
import Vista.VistaCurso;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejandro
 */

public class ControlCursos {
    ServicioCurso domainModel;
    VistaCurso view;
    ModelCurso model;
    
    public ControlCursos() throws GlobalException, NoDataException {
        this(new ModelCurso(),new VistaCurso());
    }

    public ControlCursos(ModelCurso model,VistaCurso view) throws GlobalException, NoDataException {
        model.init();
        this.domainModel= ServicioCurso.getInstancia();

        this.view = view;
        this.model = model;
        iniciar();
        view.setController(this);
        view.setModel(model);
    }
    
    public void iniciar() throws GlobalException, NoDataException{
        try {
            LinkedList lista = domainModel.listarCursos();
            this.model.setCursos(lista); 
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void buscar() throws GlobalException, NoDataException{
        try {
            String id = view.IdField.getText();
            LinkedList lista = domainModel.listarCursos();
            LinkedList aux = model.getCursos().buscar(id, lista);
            model.setCursos(aux);
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void agregar() throws GlobalException, NoDataException {
        try { 
            
            Curso curso_agregado = new Curso(view.insertarId.getText(),view.insertarCodigo.getText(),view.insertarNombre.getText(),Integer.parseInt(view.insertarCreditos.getText()), Integer.parseInt(view.insertarHoras.getText()));

            domainModel.insertarCurso(curso_agregado);
            LinkedList<Curso> rows = domainModel.listarCursos();
            
            if (rows.isEmpty()){
                JOptionPane.showMessageDialog(null, "Ningun registro coincide");
            }
            
            model.setCursos(rows);
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void actualizar()throws GlobalException, NoDataException {
        try {
            Curso curso_agregado = new Curso(view.insertarId.getText(),view.insertarCodigo.getText(),view.insertarNombre.getText(),Integer.parseInt(view.insertarCreditos.getText()), Integer.parseInt(view.insertarHoras.getText()));
            domainModel.modificarCurso(curso_agregado);
            LinkedList<Curso> rows = domainModel.listarCursos();
            
            if (rows.isEmpty()){
                JOptionPane.showMessageDialog(null, "Ningun registro coincide");
            }
            
            model.setCursos(rows);
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void eliminar() throws GlobalException, NoDataException {
        try {
            String cursoId = view.insertarId.getText();
            domainModel.eliminar(cursoId);
             LinkedList<Curso> rows = domainModel.listarCursos();
             
            if (rows.isEmpty()){
                JOptionPane.showMessageDialog(null, "Ningun registro coincide");
            }
            
            model.setCursos(rows);
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void salir(){ 
    }
}