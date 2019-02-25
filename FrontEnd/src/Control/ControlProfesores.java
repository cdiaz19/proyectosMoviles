/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import AccesoADatos.ServicioProfesor;
import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import LogicaDeNegocio.Modelo;
import LogicaDeNegocio.Profesor;
import Vista.Vista;
import java.util.LinkedList;
/**
 *
 * @author Alejandro
 */
public class ControlProfesores {
    ServicioProfesor domainModel;
    Vista view;
    Modelo model;
     public ControlProfesores() throws GlobalException, NoDataException {
        this(new Modelo(),new Vista());
    }

    public ControlProfesores(Modelo model,Vista view) throws GlobalException, NoDataException {
        model.init();
        this.domainModel= ServicioProfesor.getInstancia();
        
        this.view = view;
        this.model = model;
        iniciar();
        view.setController(this);
        view.setModel(model);
    }
    public void iniciar() throws GlobalException, NoDataException{
        LinkedList lista = domainModel.listar();
        
        
        this.model.setProfesores(domainModel.listar());
    }
    
    public void buscar() throws GlobalException, NoDataException{
        String nombre = view.nombreField.getText();
        LinkedList lista = domainModel.listar();
        LinkedList aux = model.getProfesores().buscar(nombre, lista);
        model.setProfesores(aux);
    }
    
    public void agregar() throws GlobalException, NoDataException{
        Profesor profesor_agregado = new Profesor(view.insertarId.getText(),view.insertarCedula.getText(),view.insertarNombre.getText(),view.insertarEmail.getText(),view.insertarContrasena.getText(),Integer.parseInt(view.insertarTelefono.getText()));
        domainModel.insertar(profesor_agregado);
        LinkedList<Profesor> rows = domainModel.listar();
        if (rows.isEmpty()){
            model.getErrores().put("nombreFld", "Ningun registro coincide");
            model.setMensaje("NINGUN REGISTRO COINCIDE");
        }
        model.setProfesores(rows);
    }

    public void salir(){
       
    }
}
