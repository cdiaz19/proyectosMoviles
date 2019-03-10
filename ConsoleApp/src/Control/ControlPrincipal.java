/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author cdiaz
 */
public class ControlPrincipal {

    public ControlPrincipal() throws SQLException, GlobalException, NoDataException {
        DisplayMenu();
    }
    
    public static void DisplayMenu() throws SQLException, GlobalException, NoDataException {
        Scanner userInput = new Scanner(System.in);
        String READ_MENU;

        // Display menu graphics
        System.out.println("*****************************************");
        System.out.println("|   Mantenimientos Proyecto Moviles     |");
        System.out.println("*****************************************");
        System.out.println("| Opciones:                             |");
        System.out.println("|        1. Mantenimiento Profesores    |");
        System.out.println("|        2. Mantenimiento Ciclos        |");
        System.out.println("|        3. Mantenimiento Cursos        |");
        System.out.println("|        4. Mantenimiento Carreras      |");
        System.out.println("|        5. Cerrar                      |");
        System.out.println("*****************************************");

        System.out.print("Seleccione opcion: ");

        READ_MENU = userInput.next();

        switch (READ_MENU) {
            case "1":
                ControlProfesores controlProfesores = new ControlProfesores();
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
                System.exit(0);
                break;
            default:
                System.out.println("Invalid selection");
                break; 
        }
    }
}
