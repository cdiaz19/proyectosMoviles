/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;
import LogicaDeNegocio.Profesor;
import LogicaDeNegocio.Usuario;
import AccesoADatos.ServicioProfesor;
import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import LogicaDeNegocio.Profesor;
import java.util.LinkedList;

public class Principal
{
  public static void main(String[] args)
    throws GlobalException, NoDataException
  {
     Usuario usuario1= new Usuario("1","3126","33");
    Profesor profesor1=new Profesor("1","Alejandro Gamboa","aauf@gmail.com",55565,usuario1);
    ServicioProfesor sp = ServicioProfesor.getInstancia();
    //LinkedList<Profesor> p1 = sp.listarProfesores();
    sp.modificarProfesor(profesor1, usuario1);
    //   sp.eliminar("1");
    LinkedList<Profesor> p1 = sp.listarProfesores();
    System.out.print(p1);
  }
}
