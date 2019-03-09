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
import LogicaDeNegocio.Ciclo;
import LogicaDeNegocio.Curso;
import java.util.LinkedList;

public class Principal
{
  public static void main(String[] args)
    throws GlobalException, NoDataException
  {
   //Ciclo ciclo=new Ciclo("1",4,2019,"19/06/2017","16/06/2018");
    ServicioCurso sc = ServicioCurso.getInstancia();
    //LinkedList<Profesor> p1 = sp.listarProfesores();
    LinkedList<Curso> c1 = sc.listarCursos();
    System.out.print(c1);
  }
}
