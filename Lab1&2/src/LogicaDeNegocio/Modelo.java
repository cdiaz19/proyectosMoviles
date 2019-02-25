/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaDeNegocio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Alejandro
 */
public class Modelo extends Observable {
   Profesor filter;
  TableModelProfesor profesores;
  HashMap<String, String> errores;
  String mensaje;
  
  public void init()
  {
    this.filter = new Profesor();
    LinkedList<Profesor> rows = new LinkedList();
    setProfesores(rows);
    clearErrors();
  }
  
  public void setProfesores(LinkedList<Profesor> profesores)
  {
    int[] cols = { 0, 1, 2, 3, 4, 5 };
    this.profesores = new TableModelProfesor(cols, profesores);
    setChanged();
    notifyObservers();
  }
  
  public Profesor getFilter()
  {
    return this.filter;
  }
  
  public void setFilter(Profesor filter)
  {
    this.filter = filter;
  }
  
  public TableModelProfesor getProfesores()
  {
    return this.profesores;
  }
  
  public void addObserver(Observer o)
  {
    super.addObserver(o);
    setChanged();
    notifyObservers();
  }
  
  public String getMensaje()
  {
    return this.mensaje;
  }
  
  public void setMensaje(String mensaje)
  {
    this.mensaje = mensaje;
  }
  
  public HashMap<String, String> getErrores()
  {
    return this.errores;
  }
  
  public void setErrores(HashMap<String, String> errores)
  {
    this.errores = errores;
  }
  
  public void clearErrors()
  {
    setErrores(new HashMap());
    setMensaje("");
  } 
}
