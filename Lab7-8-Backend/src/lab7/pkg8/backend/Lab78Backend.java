/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7.pkg8.backend;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import AccesoADatos.ServicioCarrera;
import AccesoADatos.ServicioCiclo;
import AccesoADatos.ServicioCurso;
import AccesoADatos.ServicioProfesor;
import LogicaDeNegocio.Carrera;
import LogicaDeNegocio.Ciclo;
import LogicaDeNegocio.Curso;
import LogicaDeNegocio.Profesor;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author alejandro
 */
public class Lab78Backend {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws GlobalException, NoDataException {
        ServicioCiclo sc= ServicioCiclo.getInstancia();
        ServicioProfesor sp= ServicioProfesor.getInstancia();
        ServicioCurso scur=ServicioCurso.getInstancia();
        ServicioCarrera scar=ServicioCarrera.getInstancia();
        Carrera carrera= new Carrera("CP","CIEN","Bachillerato");
        //scar.modificarCarrera(carrera);
        Curso curso= new Curso("EIF2014","PROGRA mierda",3,6,carrera);
        scur.modificarCurso(curso, carrera);
        //Ciclo ciclo1= new Ciclo("1",2015,1,"12/15/2015","21/02/2017");
        //Profesor profesor= new Profesor("11579044","Alejandro1","@ALE567",2222554);
        //sc.insertarCiclo(ciclo1);
        //sp.insertarProfesor(profesor);
        //sp.modificarProfesor(profesor);
        //sp.eliminarProfesor("11579044");
        //scur.eliminarCurso("MATE1");
        LinkedList<Carrera> c1 = scar.listarCarreras();
                System.out.print(c1);
        // TODO code application logic here
    }
    
}
