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
import LogicaNegocio.ModelProfesor;
import Vista.VistaProfesor;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author cdiaz
 */

public class ControlProfesores {
    ServicioProfesor profesorServicio;
    ModelProfesor model;
    VistaProfesor view;
    
    public ControlProfesores() throws SQLException, GlobalException, NoDataException {
        this(new ModelProfesor(), new VistaProfesor());
        view.MostrarMenu();
    }
    
    public ControlProfesores(ModelProfesor model, VistaProfesor view) {
        this.model = model;
        this.view = view;
    }
    
    public void iniciar() throws GlobalException, NoDataException{
//        try {
            Profesor p = new Profesor();
            LinkedList lista = new LinkedList();
            lista.add(p);
//            LinkedList lista = profesorServicio.listarProfesores();
            this.model.setProfesores(lista);
//        } catch (GlobalException | NoDataException ex) {
//            System.err.print(ex.getMessage());
//        }
    }
    
    
    
    
    
    
}


//public class ControlProfesores {
//    ServicioProfesor domainModel;
//    VistaProfesor view;
//    ModelProfesor model;
//    
//    public ControlProfesores() throws GlobalException, NoDataException {
//        this(new ModelProfesor(),new VistaProfesor());
//    }
//
//    public ControlProfesores(ModelProfesor model,VistaProfesor view) throws GlobalException, NoDataException {
//        model.init();
//        this.domainModel= ServicioProfesor.getInstancia();
//
//        this.view = view;
//        this.model = model;
//        iniciar();
//        view.setController(this);
//        view.setModel(model);
//    }
//    
//    public void iniciar() throws GlobalException, NoDataException{
//        try {
//            LinkedList lista = domainModel.listarProfesores();
//            this.model.setProfesores(lista); 
//        } catch (GlobalException | NoDataException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
//    }
//    
//    public void buscar() throws GlobalException, NoDataException{
//        try {
//            String cedula = view.CedulaField.getText();
//            LinkedList lista = domainModel.listarProfesores();
//            LinkedList aux = model.getProfesores().buscar(cedula, lista);
//            model.setProfesores(aux);
//        } catch (GlobalException | NoDataException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
//    }
//    
//    public void agregar() throws GlobalException, NoDataException {
//        try { 
//            Usuario usuario_agregado = new Usuario(view.insertarId.getText(),view.insertarCedula.getText(),view.insertarContrasena.getText());
//            Profesor profesor_agregado = new Profesor(view.insertarId.getText(),view.insertarNombre.getText(),view.insertarEmail.getText(), Integer.parseInt(view.insertarTelefono.getText()), usuario_agregado);
//
//            domainModel.insertarProfesor(profesor_agregado, usuario_agregado);
//            LinkedList<Profesor> rows = domainModel.listarProfesores();
//            
//            if (rows.isEmpty()){
//                JOptionPane.showMessageDialog(null, "Ningun registro coincide");
//            }
//            
//            model.setProfesores(rows);
//        } catch (GlobalException | NoDataException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
//    }
//    
//    public void actualizar()throws GlobalException, NoDataException {
//        try {
//            Usuario usuario_agregado = new Usuario(view.insertarId.getText(),view.insertarCedula.getText(),view.insertarContrasena.getText());
//            Profesor profesor_agregado = new Profesor(view.insertarId.getText(),view.insertarNombre.getText(),view.insertarEmail.getText(), Integer.parseInt(view.insertarTelefono.getText()), usuario_agregado);
//            domainModel.modificarProfesor(profesor_agregado, usuario_agregado);
//            LinkedList<Profesor> rows = domainModel.listarProfesores();
//            
//            if (rows.isEmpty()){
//                JOptionPane.showMessageDialog(null, "Ningun registro coincide");
//            }
//            
//            model.setProfesores(rows);
//        } catch (GlobalException | NoDataException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
//    }
//    
//    public void eliminar() throws GlobalException, NoDataException {
//        try {
//            String profesorId = view.insertarId.getText();
//            domainModel.eliminar(profesorId);
//             LinkedList<Profesor> rows = domainModel.listarProfesores();
//             
//            if (rows.isEmpty()){
//                JOptionPane.showMessageDialog(null, "Ningun registro coincide");
//            }
//            
//            model.setProfesores(rows);
//        } catch (GlobalException | NoDataException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
//    }
//    
//    public void salir(){ 
//    }
//}
