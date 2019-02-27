/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import AccesoADatos.ServicioCiclo;
import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import LogicaDeNegocio.Ciclo;
import java.util.LinkedList;

public class Principal
{
  public static void main(String[] args)
    throws GlobalException, NoDataException
  {
    ServicioCiclo sp = ServicioCiclo.getInstancia();
    LinkedList<Ciclo> p1 = sp.listar();
    System.out.print(p1);
  }
}
