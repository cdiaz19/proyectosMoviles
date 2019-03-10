/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import LogicaDeNegocio.Ciclo;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author cdiaz
 */

public class ModelCiclo extends Observable {
    Ciclo filter;
    TableCiclo ciclos;
    HashMap<String, String> errores;
    String mensaje;

    public void init() {
      this.filter = new Ciclo();
      LinkedList<Ciclo> rows = new LinkedList();
      setCiclos(rows);
      clearErrors();
    }

    public void setCiclos(LinkedList<Ciclo> ciclos) {
      int[] cols = { 0, 1, 2, 3, 4, 5 };
      this.ciclos = new TableCiclo(cols, ciclos);
      setChanged();
      notifyObservers();
    }

    public Ciclo getFilter() {
      return this.filter;
    }

    public void setFilter(Ciclo filter) {
      this.filter = filter;
    }

    public TableCiclo getCiclos() {
      return this.ciclos;
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
