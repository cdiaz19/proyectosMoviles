/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import Control.ControlPrincipal;
import Control.ControlProfesores;
import LogicaDeNegocio.Profesor;
import LogicaNegocio.ModelProfesor;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author cdiaz
 */

public class VistaProfesor {

    public VistaProfesor() {
    }
    
    public static void MostrarMenu() throws SQLException, GlobalException, NoDataException { 
        Scanner userInput = new Scanner(System.in);
        String READ_MENU;

        // Display menu graphics
        System.out.println("*****************************************");
        System.out.println("|   Mantenimientos Profesores .         |");
        System.out.println("*****************************************");
        System.out.println("| Opciones:                             |");
        System.out.println("|        1. Listar Profesores           |");
        System.out.println("|        2. Ingresar Profesor           |");
        System.out.println("|        3. Actualizar Profesor         |");
        System.out.println("|        4. Eliminar Profesores         |");
        System.out.println("|        5. Cerrar Mantenimiento        |");
        System.out.println("*****************************************");

        System.out.print("Seleccione opcion: ");

        READ_MENU = userInput.next();

        switch (READ_MENU) {
            case "1":
                MostrarProfesores();
                break;
            case "2":
//                Read read = new Read();
                break;
            case "3":
//                Update update = new Update();
                break;
            case "4":
//                Delete delete = new Delete();
                break;
            case "5":
                ControlPrincipal controlPrincipal = new ControlPrincipal();
                break;
            default:
                System.out.println("Invalid selection");
                break; 
        }
    }
    
    public static void MostrarProfesores() throws SQLException, GlobalException, NoDataException {
        ControlProfesores controlProfesor = new ControlProfesores();
        ModelProfesor modelProfesor = new ModelProfesor();
        System.out.print("Lista de Profesores \n");
        String columnas[] = { "Id", "Cedula", "Nombre", "Correo", "Contrasena", "Telefono" };
        for(int i=0; i<=columnas.length+1; i++) {
            System.out.println(columnas[i]);
        }
        System.out.println();
        MostrarMenu();
//                controlProfesor.iniciar();
//                System.out.print("Lista de Profesores \n");
//                System.out.println(modelProfesor.getProfesores().toString());
//                System.out.println();
//                MostrarProfesores(modelProfesor.getProfesores());
//        String columnas[] = { "Id", "Cedula", "Nombre", "Correo", "Contrasena", "Telefono" };
//        System.out.println(columnas.toString());
//        System.out.println(listaProfesores.toString());
//        System.out.println();
    }
}
