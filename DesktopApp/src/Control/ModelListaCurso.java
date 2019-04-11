/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import LogicaDeNegocio.Carrera;
import LogicaDeNegocio.ListaCurso;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author cdiaz
 */

public class ModelListaCurso extends Observable {
    ListaCurso filter;
    TableListaCurso listaCursos;
    HashMap<String, String> errores;
    String mensaje;

    public void init() {
      this.filter = new ListaCurso();
      LinkedList<ListaCurso> rows = new LinkedList();
      setListaCurso(rows);
      clearErrors();
    }

    public void setListaCurso(LinkedList<ListaCurso> listaCurso) {
      int[] cols = { 0, 1, 2, 3, 4, 5 };
      this.listaCursos = new TableListaCurso(cols, listaCurso);
      setChanged();
      notifyObservers();
    }

    public ListaCurso getFilter() {
      return this.filter;
    }

    public void setFilter(ListaCurso filter) {
      this.filter = filter;
    }

    public TableListaCurso getListaCurso() {
      return this.listaCursos;
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
