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
import AccesoADatos.ServicioCliente;
import AccesoADatos.ServicioVideojuego;
import LogicaDeNegocio.Categoria;
import LogicaDeNegocio.Client;
import LogicaDeNegocio.User;
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
        Categoria categoria= new Categoria("SHO21","Shooter12");
        ServicioCategoria sc1 = ServicioCategoria.getInstancia();
        ServicioCliente ssc= ServicioCliente.getInstancia();
        User usuario = new User("1126580","eeee4e@gmail.com","12345","cliente");
       
        Client cliente=new Client("Cristian hy",22920045, usuario);
        //ssc.eliminarCliente("115790444");
        //ssc.modificarCliente(cliente, usuario);
        //ssc.modificarCliente(cliente);
        //ServicioVideojuego sc = ServicioVideojuego.getInstancia();
        //sc1.insertarCategoria(categoria);
        //Videojuego videojuego= new Videojuego("CODBLACK","Call of Duty Black Ops 2",2,3500,"Blizzar",categoria);
        //sc.insertarVideojuego(categoria, videojuego);
        //sc.eliminarVideojuego("COkD");
        //sc.modificarCategoria(categoria);
        //sc.eliminarCategoria("RPG");
       // sc.eliminarCarrera("05");
        //sc.insertarCategoria(categoria);
        //LinkedList<Videojuego> c1 = sc.listarVideojueos();
        //System.out.print(c1);
        //sc.modificarCarrera(carrera1);
        LinkedList<Client> c1 = ssc.listarClientes();
        System.out.print(c1);
        
        
        
        // TODO code application logic here
    }
    
}