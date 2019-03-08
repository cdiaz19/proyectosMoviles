package Control;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import LogicaDeNegocio.Curso;
import LogicaNegocio.ModelCurso;
import Vista.VistaCurso;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author cdiaz
 */
public class ControlCursos {
    ServicioCurso cursoServicio;
    VistaCurso cursoView;
    ModelCurso cursoModel; 
    
    public ControlCursos() throws GlobalException, NoDataException {
        this(new ModelCurso(),new VistaCurso());
    }
    
    public ControlCursos(ModelCurso model,VistaCurso view) throws GlobalException, NoDataException {
        model.init();
        this.cursoServicio= ServicioCurso.getInstancia();
        
        this.cursoView = view;
        this.cursoModel = model;
        iniciar();
        view.setController(this);
        view.setModel(model);
    }
    
    public void iniciar() throws GlobalException, NoDataException{
        try {
            LinkedList lista = cursoServicio.listarCiclos();
            this.cursoModel.setCursos(lista); 
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void agregar() throws GlobalException, NoDataException {
        try {
            Curso ciclo_agregado = new Curso();

            cursoServicio.insertarCiclo(ciclo_agregado);
            LinkedList<Curso> rows = cursoServicio.listarCiclos();
            
            if (rows.isEmpty()) { 
                JOptionPane.showMessageDialog(null, "Ningun registro coincide");
            }
            
            cursoModel.setCursos(rows);
            
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void buscar() throws GlobalException, NoDataException {
        try { 
            String cicloId = cursoView.IDField.getText();
            LinkedList lista = cursoServicio.listarCiclos();
            LinkedList aux = cursoModel.getCursos().buscar(cicloId, lista);
            cursoModel.setCursos(aux);
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void actualizar()throws GlobalException, NoDataException {
        try {
            Curso ciclo_agregado = new Curso();

            cursoServicio.modificarCiclo(ciclo_agregado);
            LinkedList<Curso> rows = cursoServicio.listarCiclos();
            
            if (rows.isEmpty()){
                JOptionPane.showMessageDialog(null, "Ningun registro coincide");
            }
            
            cursoModel.setCiclos(rows);
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void eliminar() throws GlobalException, NoDataException {
        try {
            String cicloId = cursoView.insertarId.getText();
            cursoServicio.eliminar(cicloId);
            LinkedList lista = cursoServicio.listarCiclos();
            cursoModel.setCursos(lista); 
        } catch (GlobalException | NoDataException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
