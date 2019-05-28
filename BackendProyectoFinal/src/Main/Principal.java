/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author alejandro
 */
import AccesoADatos.ServicioCategoria;
import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import AccesoADatos.ServicioVideojuego;
import LogicaDeNegocio.Categoria;
import LogicaDeNegocio.Videojuego;
import java.util.LinkedList;
/**
 *
 * @author alejandro
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws GlobalException, NoDataException {
        Categoria categoria= new Categoria("SHO","Shooter");
        //ServicioCategoria sc = ServicioCategoria.getInstancia();
        ServicioVideojuego sc = ServicioVideojuego.getInstancia();
        //Videojuego videojuego= new Videojuego("CODBLACK","Call of Duty Black Ops 2",2,3500,"Blizzar",categoria);
        //sc.insertarVideojuego(categoria, videojuego);
        //sc.eliminarVideojuego("COkD");
        //sc.modificarCategoria(categoria);
        //sc.eliminarCategoria("RPG");
       // sc.eliminarCarrera("05");
        //sc.insertarCategoria(categoria);
        LinkedList<Videojuego> c1 = sc.listarVideojueos();
        System.out.print(c1);
        //sc.modificarCarrera(carrera1);
        //LinkedList<Carrera> c1 = sc.buscarPorCodigo("05");
        //System.out.print(c1);
        
        
        
        // TODO code application logic here
    }
    
}
