/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import LogicaNegocio.ModelProfesor;
import AccesoADatos.ServicioProfesor;
import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import LogicaDeNegocio.Profesor;
import LogicaDeNegocio.Usuario;
import Vista.VistaProfesor;
import java.util.LinkedList;

/**
 *
 * @author Alejandro
 */

public class ControlProfesores {
    ServicioProfesor domainModel;
    VistaProfesor view;
    ModelProfesor model;
    
    public ControlProfesores() throws GlobalException, NoDataException {
        this(new ModelProfesor(),new VistaProfesor());
    }

    public ControlProfesores(ModelProfesor model,VistaProfesor view) throws GlobalException, NoDataException {
        model.init();
        this.domainModel= ServicioProfesor.getInstancia();
        
        this.view = view;
        this.model = model;
        iniciar();
        view.setController(this);
        view.setModel(model);
    }
    
    public void iniciar() throws GlobalException, NoDataException{
        LinkedList lista = domainModel.listarProfesores();
        this.model.setProfesores(lista); 
    }
    
    public void buscar() throws GlobalException, NoDataException{
        String cedula = view.CedulaField.getText();
        LinkedList lista = domainModel.listarProfesores();
        LinkedList aux = model.getProfesores().buscar(cedula, lista);
        model.setProfesores(aux);
    }
    
    public void agregar() throws GlobalException, NoDataException{
        Usuario usuario_agregado = new Usuario(view.insertarId.getText(),view.insertarCedula.getText(),view.insertarContrasena.getText());
        Profesor profesor_agregado = new Profesor(view.insertarId.getText(),view.insertarNombre.getText(),view.insertarEmail.getText(), Integer.parseInt(view.insertarTelefono.getText()), usuario_agregado);

        domainModel.insertarProfesor(profesor_agregado, usuario_agregado);
        LinkedList<Profesor> rows = domainModel.listarProfesores();
        if (rows.isEmpty()){
            model.getErrores().put("nombreFld", "Ningun registro coincide");
            model.setMensaje("NINGUN REGISTRO COINCIDE");
        }
        model.setProfesores(rows);
    }
    
    public void actualizar()throws GlobalException, NoDataException{
        Usuario usuario_agregado = new Usuario(view.insertarId.getText(),view.insertarCedula.getText(),view.insertarContrasena.getText());
        Profesor profesor_agregado = new Profesor(view.insertarId.getText(),view.insertarNombre.getText(),view.insertarEmail.getText(), Integer.parseInt(view.insertarTelefono.getText()), usuario_agregado);
        domainModel.modificarProfesor(profesor_agregado, usuario_agregado);
        LinkedList<Profesor> rows = domainModel.listarProfesores();
        if (rows.isEmpty()){
            model.getErrores().put("nombreFld", "Ningun registro coincide");
            model.setMensaje("NINGUN REGISTRO COINCIDE");
        }
        model.setProfesores(rows);
    }
    
    public void eliminar() throws GlobalException, NoDataException {
        String profesorId = view.insertarId.getText();
        domainModel.eliminar(profesorId);
         LinkedList<Profesor> rows = domainModel.listarProfesores();
        if (rows.isEmpty()){
            model.getErrores().put("nombreFld", "Ningun registro coincide");
            model.setMensaje("NINGUN REGISTRO COINCIDE");
        }
        model.setProfesores(rows);
    }
    
    public void salir(){ 
    }
}
