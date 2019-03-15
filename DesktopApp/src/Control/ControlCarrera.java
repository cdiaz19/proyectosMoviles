/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import AccesoADatos.ServicioCarrera;
import LogicaDeNegocio.Carrera;
import LogicaNegocio.ModelCarrera;
import Vista.VistaCarrera;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author cdiaz
 */

public class ControlCarrera {
    ServicioCarrera carreraServicio;
    VistaCarrera carreraView;
    ModelCarrera carreraModel; 
    
    public ControlCarrera() throws GlobalException, NoDataException {
        this(new ModelCarrera(),new VistaCarrera());
    }
    
    public ControlCarrera(ModelCarrera model,VistaCarrera view) throws GlobalException, NoDataException {
        model.init();
        this.carreraServicio= ServicioCarrera.getInstancia();
        
        this.carreraView = view;
        this.carreraModel = model;
        iniciar();
        view.setController(this);
        view.setModel(carreraModel);
    }
    
    public void iniciar() throws GlobalException, NoDataException{
        try {
            LinkedList lista = carreraServicio.listarCarreras();
            this.carreraModel.setCarreras(lista); 
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void agregar() throws GlobalException, NoDataException {
        try {
            Carrera carrera_agregado = new Carrera(carreraView.insertarId.getText(),carreraView.insertarCodigo.getText(),carreraView.insertarNombre.getText(),carreraView.insertarTitulo.getText());

            carreraServicio.insertarCarrera(carrera_agregado);
            LinkedList<Carrera> rows = carreraServicio.listarCarreras();
            
            if (rows.isEmpty()) { 
                JOptionPane.showMessageDialog(null, "Ningun registro coincide");
            }
            
            carreraModel.setCarreras(rows);
            
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void buscar() throws GlobalException, NoDataException {
        try { 
            String carreraId = carreraView.IDField.getText();
            LinkedList lista = carreraServicio.listarCarreras();
            LinkedList aux = carreraModel.getCarreras().buscar(carreraId, lista);
            carreraModel.setCarreras(aux);
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void actualizar()throws GlobalException, NoDataException {
        try {
            Carrera carrera_agregado = new Carrera(carreraView.insertarId.getText(),carreraView.insertarCodigo.getText(),carreraView.insertarNombre.getText(),carreraView.insertarTitulo.getText());

            carreraServicio.modificarCarrera(carrera_agregado);
            LinkedList<Carrera> rows = carreraServicio.listarCarreras();
            
            if (rows.isEmpty()){
                JOptionPane.showMessageDialog(null, "Ningun registro coincide");
            }
            
            carreraModel.setCarreras(rows);
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void eliminar() throws GlobalException, NoDataException {
        try {
            String carreraId = carreraView.insertarId.getText();
            carreraServicio.eliminar(carreraId);
            LinkedList lista = carreraServicio.listarCarreras();
            carreraModel.setCarreras(lista); 
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
