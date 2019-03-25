/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import AccesoADatos.ServicioCarrera;
import LogicaNegocio.ModelListaCurso;
import Vista.VistaListaCurso;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author cdiaz
 */

public class ControlListaCurso {
    ServicioCarrera carreraServicio;
    VistaListaCurso listaCursoView;
    ModelListaCurso listaCursoModel;
    
    public ControlListaCurso(String carrera) throws GlobalException, NoDataException {
        this(new ModelListaCurso(),new VistaListaCurso(), carrera);
    }
    
    public ControlListaCurso(ModelListaCurso model,VistaListaCurso view, String carrera) throws GlobalException, NoDataException {
        model.init();
        this.carreraServicio= ServicioCarrera.getInstancia();
        
        this.listaCursoView = view;
        this.listaCursoModel = model;
        iniciar(carrera);
        view.setController(this);
        view.setModel(listaCursoModel);
    }
    
    public void iniciar(String carreraId) throws GlobalException, NoDataException{
        try {
            LinkedList listaCurso = carreraServicio.listarCursosCarrera(carreraId);
            this.listaCursoModel.setListaCurso(listaCurso);
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
