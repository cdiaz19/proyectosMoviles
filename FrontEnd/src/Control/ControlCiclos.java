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
import LogicaNegocio.ModelCiclo;
import Vista.VistaCiclo;
import static java.lang.Integer.parseInt;
import java.util.LinkedList;

/**
 *
 * @author cdiaz
 */

public class ControlCiclos {
    ServicioCiclo cicloServicio;
    VistaCiclo cicloView;
    ModelCiclo cicloModel; 
    
    public ControlCiclos() throws GlobalException, NoDataException {
        this(new ModelCiclo(),new VistaCiclo());
    }
    
    public ControlCiclos(ModelCiclo model,VistaCiclo view) throws GlobalException, NoDataException {
        model.init();
        this.cicloServicio= ServicioCiclo.getInstancia();
        
        this.cicloView = view;
        this.cicloModel = model;
        iniciar();
        view.setController(this);
        view.setModel(cicloModel);
    }
    
    public void iniciar() throws GlobalException, NoDataException{
        LinkedList lista = cicloServicio.listarCiclos();
        this.cicloModel.setCiclos(lista); 
    }
    
    public void agregar() throws GlobalException, NoDataException{
        Ciclo ciclo_agregado = new Ciclo(cicloView.insertarId.getText(), parseInt(cicloView.insertarAnno.getText()), parseInt(cicloView.insertarNumero.getText()), cicloView.insertarFechaInicio.getText(), cicloView.insertarFechaFinal.getText() );

        cicloServicio.insertarCiclo(ciclo_agregado);
        LinkedList<Ciclo> rows = cicloServicio.listarCiclos();
        if (rows.isEmpty()){
            cicloModel.getErrores().put("nombreFld", "Ningun registro coincide");
            cicloModel.setMensaje("NINGUN REGISTRO COINCIDE");
        }
        cicloModel.setCiclos(rows);
    }
    
    public void buscar() throws GlobalException, NoDataException{
        String cicloId = cicloView.insertarId.getText();
        LinkedList lista = cicloServicio.listarCiclos();
        LinkedList aux = cicloModel.getCiclos().buscar(cicloId, lista);
        cicloModel.setCiclos(aux);
    }
    
    public void actualizar()throws GlobalException, NoDataException{
        Ciclo ciclo_agregado = new Ciclo(cicloView.insertarId.getText(), parseInt(cicloView.insertarAnno.getText()), parseInt(cicloView.insertarNumero.getText()), cicloView.insertarFechaInicio.getText(), cicloView.insertarFechaFinal.getText() );

        cicloServicio.modificarCiclo(ciclo_agregado);
        LinkedList<Ciclo> rows = cicloServicio.listarCiclos();
        if (rows.isEmpty()){
            cicloModel.getErrores().put("nombreFld", "Ningun registro coincide");
            cicloModel.setMensaje("NINGUN REGISTRO COINCIDE");
        }
        cicloModel.setCiclos(rows);
    }
    
    public void eliminar() throws GlobalException, NoDataException {
        String cicloId = cicloView.insertarId.getText();
        cicloServicio.eliminar(cicloId);
        LinkedList lista = cicloServicio.listarCiclos();
        cicloModel.setCiclos(lista); 
    }
}