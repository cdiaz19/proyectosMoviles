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
import AccesoADatos.ServicioOrder;
import AccesoADatos.ServicioVideojuego;
import LogicaDeNegocio.Categoria;
import LogicaDeNegocio.Client;
import LogicaDeNegocio.Order;
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
        ServicioOrder so= ServicioOrder.getInstancia();
        User usuario = new User("115790444",null,null,null);
       
        Client cliente=new Client(null,0, usuario);
        Videojuego videojuego=new Videojuego("GTAV",null,0,0,null,null);
        Order order=new Order(2,"2012",16,0,videojuego,cliente);
        //so.insertarPedido(order, cliente, videojuego);
        //so.modificarPedido(order, cliente, videojuego);
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
        LinkedList<Order> c1 = so.listarPedidos();
        System.out.print(c1);
        
        
        
        // TODO code application logic here
    }
    
}