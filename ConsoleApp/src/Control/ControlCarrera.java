package Control;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import AccesoADatos.ServicioCarrera;
import LogicaDeNegocio.Carrera;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro
 */
public class ControlCarrera {
    
    ServicioCarrera carrera;
    
    public ServicioCarrera getCiclo() {
        return carrera;
    }
    
    public void setCiclo(ServicioCarrera ciclo) {
        this.carrera = ciclo;
    }
    
    public ControlCarrera() {
        this.carrera = ServicioCarrera.getInstancia();
    }
    
    public LinkedList listar() throws GlobalException, NoDataException {
        LinkedList lista = this.carrera.listarCarreras();
        return lista;
    }
    
    public LinkedList buscarPorCodigo(String codigo) throws GlobalException, NoDataException {
        LinkedList lista = this.carrera.buscarPorCodigo(codigo);
        return lista;
    }
    
    public void modificarCarrera(Carrera carrera) {
        
        try {
            this.carrera.modificarCarrera(carrera);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ControlProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertarCarrera(Carrera carrera) {
        try {
            this.carrera.insertarCarrera(carrera);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ControlProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminar(String id) {
        try {
            this.carrera.eliminar(id);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ControlProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
