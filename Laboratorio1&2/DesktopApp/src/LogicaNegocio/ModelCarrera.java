/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import LogicaDeNegocio.Carrera;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author cdiaz
 */

public class ModelCarrera extends Observable {
    Carrera filter;
    TableCarrera carreras;
    HashMap<String, String> errores;
    String mensaje;

    public void init() {
      this.filter = new Carrera();
      LinkedList<Carrera> rows = new LinkedList();
      setCarreras(rows);
      clearErrors();
    }

    public void setCarreras(LinkedList<Carrera> carreras) {
      int[] cols = { 0, 1, 2, 3, 4, 5 };
      this.carreras = new TableCarrera(cols, carreras);
      setChanged();
      notifyObservers();
    }

    public Carrera getFilter() {
      return this.filter;
    }

    public void setFilter(Carrera filter) {
      this.filter = filter;
    }

    public TableCarrera getCarreras() {
      return this.carreras;
    }

    public void addObserver(Observer o) {
      super.addObserver(o);
      setChanged();
      notifyObservers();
    }

    public String getMensaje() {
      return this.mensaje;
    }

    public void setMensaje(String mensaje) {
      this.mensaje = mensaje;
    }

    public HashMap<String, String> getErrores() {
      return this.errores;
    }

    public void setErrores(HashMap<String, String> errores) {
      this.errores = errores;
    }

    public void clearErrors() {
      setErrores(new HashMap());
      setMensaje("");
    }   
}
