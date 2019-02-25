/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import AccesoADatos.Servicio;
import AccesoADatos.ServicioProfesor;
import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import LogicaDeNegocio.Profesor;
import java.util.LinkedList;

/**
 *
 * @author cdiaz
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws GlobalException, NoDataException {
        // TODO code application logic here
        ServicioProfesor sp= ServicioProfesor.getInstancia();
        LinkedList<Profesor> p1 = sp.listar();
        
    }
    
}
