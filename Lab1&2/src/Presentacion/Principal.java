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
     Usuario usuario1= new Usuario("123","115","444");
    Profesor profesor1=new Profesor("123","Cristian","aaaf@gmail.com",5555,usuario1);
    ServicioProfesor sp = ServicioProfesor.getInstancia();
    //LinkedList<Profesor> p1 = sp.listarProfesores();
    sp.insertarProfesor(profesor1, usuario1);
    LinkedList<Profesor> p1 = sp.buscarNombres("Georges");
    System.out.print(p1);
  }
}
