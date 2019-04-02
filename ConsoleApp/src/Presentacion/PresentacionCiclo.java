/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import Control.ControlPrincipal;
import Control.ControlCiclo;
import LogicaDeNegocio.Ciclo;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Alejandro
 */
public class PresentacionCiclo {

    ControlCiclo control = new ControlCiclo();

    public PresentacionCiclo() {
    }

    public void MostrarMenu() throws SQLException, GlobalException, NoDataException {
        Scanner userInput = new Scanner(System.in);
        String READ_MENU;

        // Display menu graphics
        System.out.println("*****************************************");
        System.out.println("|   Mantenimientos Ciclos .             |");
        System.out.println("*****************************************");
        System.out.println("| Opciones:                             |");
        System.out.println("|        1. Listar Ciclos               |");
        System.out.println("|        2. Buscar Ciclo                |");
        System.out.println("|        3. Ingresar Ciclo              |");
        System.out.println("|        4. Actualizar Ciclo            |");
        System.out.println("|        5. Eliminar Ciclo              |");
        System.out.println("|        6. Cerrar Mantenimiento        |");
        System.out.println("*****************************************");

        System.out.print("Seleccione opcion: ");

        READ_MENU = userInput.next();

        switch (READ_MENU) {
            case "1":
                mostrarCiclos();
                break;
            case "2":
                buscarPorId();
                break;
            case "3":
                ingresarNuevoCiclo();
                break;
            case "4":
                actualizarCiclo();
                break;
            case "5":
                eliminarCiclo();
                break;
            case "6":
                ControlPrincipal controlPrincipal = new ControlPrincipal();
                break;
            default:
                System.out.println("Invalid selection");
                break;
        }
    }

    public void actualizarCiclo() throws SQLException, GlobalException, NoDataException {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite el Id del Ciclo que desea modificar: ");
        String id = entrada.nextLine();
        System.out.print("Digite anno del ciclo: ");
        Integer anno = Integer.parseInt(entrada.nextLine());
        System.out.print("Digite el numero del ciclo: ");
        Integer numero = Integer.parseInt(entrada.nextLine());
        System.out.print("Digite la fecha de inicio del ciclo: ");
        String fechaInicio = entrada.nextLine();
        System.out.print("Digite la fecha de finalizacion del ciclo: ");
        String fechaFin = entrada.nextLine();
        entrada.close();
        Ciclo ciclo = new Ciclo(id, anno, numero, fechaInicio, fechaFin);
        this.control.modificarCiclo(ciclo);
        System.out.print("\n");
    }

    public void eliminarCiclo() throws SQLException, GlobalException, NoDataException {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite el Id del Ciclo a Eliminar: ");
        String id = entrada.nextLine();
        entrada.close();
        this.control.eliminar(id);
        System.out.print("\n");

    }

    public void ingresarNuevoCiclo() throws SQLException, GlobalException, NoDataException {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite el Id del Ciclo que desea ingresar: ");
        String id = entrada.nextLine();
        System.out.print("Digite anno del ciclo: ");
        Integer anno = Integer.parseInt(entrada.nextLine());
        System.out.print("Digite el numero del ciclo: ");
        Integer numero = Integer.parseInt(entrada.nextLine());
        System.out.print("Digite la fecha de inicio del ciclo: ");
        String fechaInicio = entrada.nextLine();
        System.out.print("Digite la fecha de finalizacion del ciclo: ");
        String fechaFin = entrada.nextLine();
        entrada.close();
        Ciclo ciclo = new Ciclo(id, anno, numero, fechaInicio, fechaFin);
        this.control.insertarCiclo(ciclo);
        System.out.print("\n");
        
    }

    public void mostrarCiclos() throws GlobalException, NoDataException, SQLException {
        LinkedList<Ciclo> ciclo = this.control.listar();
        int cont = 0;
        for (int i = 0; i < ciclo.size(); i++) {
            cont++;
            System.out.print("--------------------------------\n");
            System.out.print("--------------------------------\n");
            System.out.print("Ciclo Numero: " + cont + "\n");
            System.out.print("Id: " + ciclo.get(i).getId() + "\n");
            System.out.print("Anno: " + ciclo.get(i).getAnno() + "\n");
            System.out.print("Numero: " + ciclo.get(i).getNumero() + "\n");
            System.out.print("Fecha de Inicio: " + ciclo.get(i).getFechaInicio() + "\n");
            System.out.print("Fecha Final: " + ciclo.get(i).getFechaFinal() + "\n");
            System.out.print("\n");

        }
        MostrarMenu();
    }

    public void buscarPorId() throws GlobalException, NoDataException, SQLException {
        System.out.print("Digite el id del Ciclo a Buscar: ");
        Scanner entrada = new Scanner(System.in);
        String id = entrada.nextLine();
        entrada.close();
        LinkedList<Ciclo> ciclo = this.control.buscarPorId(id);

        System.out.print("--------------------------------\n");
        System.out.print("--------------------------------\n");
        System.out.print("Id: " + ciclo.get(0).getId() + "\n");
        System.out.print("Anno: " + ciclo.get(0).getAnno() + "\n");
        System.out.print("Numero: " + ciclo.get(0).getNumero() + "\n");
        System.out.print("Fecha de Inicio: " + ciclo.get(0).getFechaInicio() + "\n");
        System.out.print("Fecha Final: " + ciclo.get(0).getFechaFinal() + "\n");
        System.out.print("\n");
    }

}
