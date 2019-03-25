/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import LogicaDeNegocio.Curso;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Alejandro
 */
public class ModelCurso extends Observable {
  Curso filter;
  TableCurso cursos;
  HashMap<String, String> errores;
  String mensaje;
  
  public void init() {
    this.filter = new Curso();
    LinkedList<Curso> rows = new LinkedList();
    setCursos(rows);
    clearErrors();
  }
  
  public void setCursos(LinkedList<Curso> cursos) {
    int[] cols = { 0, 1, 2, 3, 4 };
    this.cursos = new TableCurso(cols, cursos);
    setChanged();
    notifyObservers();
  }
  
  public Curso getFilter() {
    return this.filter;
  }
  
  public void setFilter(Curso filter) {
    this.filter = filter;
  }
  
  public TableCurso getCursos() {
    return this.cursos;
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
