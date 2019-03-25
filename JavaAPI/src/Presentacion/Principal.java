/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;
import LogicaDeNegocio.Profesor;
import LogicaDeNegocio.Usuario;
import AccesoADatos.ServicioCurso;
import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import AccesoADatos.ServicioCarrera;
import LogicaDeNegocio.Carrera;
import LogicaDeNegocio.Ciclo;
import LogicaDeNegocio.Curso;
import LogicaDeNegocio.ListaCurso;
import java.util.LinkedList;

public class Principal
{
  public static void main(String[] args)
    throws GlobalException, NoDataException
  {
   //Curso curso=new Curso("1","175","MATEM",1,8);
    ServicioCarrera sc = ServicioCarrera.getInstancia();
    //Carrera carrera=new Carrera("3","FIC","FISICA","BACHI");
    //sc.modificarCarrera(carrera);
    //sc.eliminar("1");
    //LinkedList<Profesor> p1 = sp.listarProfesores();
    LinkedList<Carrera> c1 = sc.listarCarreras();
    System.out.print(c1);
  }
}
