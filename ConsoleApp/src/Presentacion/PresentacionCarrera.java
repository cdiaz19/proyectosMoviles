/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import Control.ControlCarrera;
import Control.ControlPrincipal;
import LogicaDeNegocio.Carrera;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Estudiante
 */
public class PresentacionCarrera {
    ControlCarrera control = new ControlCarrera();

    public PresentacionCarrera() {
    }

    public void MostrarMenu() throws SQLException, GlobalException, NoDataException {
        Scanner userInput = new Scanner(System.in);
        String READ_MENU;

        // Display menu graphics
        System.out.println("*****************************************");
        System.out.println("|   Mantenimientos Carreras .           |");
        System.out.println("*****************************************");
        System.out.println("| Opciones:                             |");
        System.out.println("|        1. Listar Carreras             |");
        System.out.println("|        2. Buscar Carrera por Codigo   |");
        System.out.println("|        3. Ingresar Carrera            |");
        System.out.println("|        4. Actualizar Carrera          |");
        System.out.println("|        5. Eliminar Carrera            |");
        System.out.println("|        6. Cerrar Mantenimiento        |");
        System.out.println("*****************************************");

        System.out.print("Seleccione opcion: ");

        READ_MENU = userInput.next();

        switch (READ_MENU) {
            case "1":
                mostrarCarreras();
                break;
            case "2":
                buscarPorCodigo();
                break;
            case "3":
                ingresarNuevaCarrera();
                break;
            case "4":
                actualizarCarrera();
                break;
            case "5":
                eliminarCarrera();
                break;
            case "6":
                ControlPrincipal controlPrincipal = new ControlPrincipal();
                break;
            default:
                System.out.println("Invalid selection");
                break;
        }
    }
    //id, codigo, nombre, titulo
    
    public void mostrarCarreras() throws GlobalException, NoDataException, SQLException {
        LinkedList<Carrera> carrera = this.control.listar();
        int cont = 0;
        for (int i = 0; i < carrera.size(); i++) {
            cont++;
            System.out.print("--------------------------------\n");
            System.out.print("--------------------------------\n");
            System.out.print("Carrera Numero: " + cont + "\n");
            System.out.print("Id: " + carrera.get(i).getId() + "\n");
            System.out.print("Codigo: " + carrera.get(i).getCodigo() + "\n");
            System.out.print("Nombre: " + carrera.get(i).getNombre() + "\n");
            System.out.print("Titulo: " + carrera.get(i).getTitulo() + "\n");
            System.out.print("\n");

        }
        MostrarMenu();
    }
    public void buscarPorCodigo() throws GlobalException, NoDataException, SQLException {
        System.out.print("Digite el Codigo de la carrera a Buscar: ");
        Scanner entrada = new Scanner(System.in);
        String codigo = entrada.nextLine();
        entrada.close();
        LinkedList<Carrera> carrera = this.control.buscarPorCodigo(codigo);

        System.out.print("--------------------------------\n");
        System.out.print("--------------------------------\n");
        System.out.print("Id: " + carrera.get(0).getId() + "\n");
            System.out.print("Codigo: " + carrera.get(0).getCodigo() + "\n");
            System.out.print("Nombre: " + carrera.get(0).getNombre() + "\n");
            System.out.print("Titulo: " + carrera.get(0).getTitulo() + "\n");
        System.out.print("\n");
    }
    public void ingresarNuevaCarrera() throws SQLException, GlobalException, NoDataException {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite el Id de la Carrera que desea ingresar: ");
        String id = entrada.nextLine();
        System.out.print("Digite Codigo de la carrera: ");
        String codigo = entrada.nextLine();
        System.out.print("Digite el nombre de la carrera: ");
        String nombre = entrada.nextLine();
        System.out.print("Digite el titulo de la carrera: ");
        String titulo = entrada.nextLine();
        entrada.close();
        Carrera carrera = new Carrera(id, codigo, nombre, titulo);
        this.control.insertarCarrera(carrera);
        System.out.print("\n");
        
    }

    public void actualizarCarrera() throws SQLException, GlobalException, NoDataException {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite el Id de la Carrera que desea modificar: ");
        String id = entrada.nextLine();
        System.out.print("Digite Codigo de la carrera: ");
        String codigo = entrada.nextLine();
        System.out.print("Digite el nombre de la carrera: ");
        String nombre = entrada.nextLine();
        System.out.print("Digite el titulo de la carrera: ");
        String titulo = entrada.nextLine();
        entrada.close();
        Carrera carrera = new Carrera(id, codigo, nombre, titulo);
        this.control.modificarCarrera(carrera);
        System.out.print("\n");
    }
    
    public void eliminarCarrera() throws SQLException, GlobalException, NoDataException {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite el Id de la carrera a Eliminar: ");
        String id = entrada.nextLine();
        entrada.close();
        this.control.eliminar(id);
        System.out.print("\n");

    }
    
    
}
