/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import Control.ControlCurso;
import Control.ControlPrincipal;
import LogicaDeNegocio.Curso;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Alejandro
 */
public class PresentacionCurso {

    ControlCurso control = new ControlCurso();

    public PresentacionCurso() {
    }

    public void MostrarMenu() throws SQLException, GlobalException, NoDataException {
        Scanner userInput = new Scanner(System.in);
        String READ_MENU;

        // Display menu graphics
        System.out.println("*****************************************");
        System.out.println("|   Mantenimientos Cursos .             |");
        System.out.println("*****************************************");
        System.out.println("| Opciones:                             |");
        System.out.println("|        1. Listar Cursos               |");
        System.out.println("|        2. Buscar Curso por Codigo     |");
        System.out.println("|        3. Ingresar Curso              |");
        System.out.println("|        4. Actualizar Curso            |");
        System.out.println("|        5. Eliminar Curso              |");
        System.out.println("|        6. Cerrar Mantenimiento        |");
        System.out.println("*****************************************");

        System.out.print("Seleccione opcion: ");

        READ_MENU = userInput.next();

        switch (READ_MENU) {
            case "1":
                mostrarCursos();
                break;
            case "2":
                buscarPorCodigo();
                break;
            case "3":
                ingresarNuevoCurso();
                break;
            case "4":
                actualizarCurso();
                break;
            case "5":
                eliminarCurso();
                break;
            case "6":
                ControlPrincipal controlPrincipal = new ControlPrincipal();
                break;
            default:
                System.out.println("Invalid selection");
                break;
        }
    }

    public void actualizarCurso() throws SQLException, GlobalException, NoDataException {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite el Id del Curso que desea modificar: ");
        String id = entrada.nextLine();
        System.out.print("Digite el codigo del curso: ");
        String codigo = entrada.nextLine();
        System.out.print("Digite el nombre del curso: ");
        String nombre = entrada.nextLine();
        System.out.print("Digite los creditos que vale este curso: ");
        Integer creditos = Integer.parseInt(entrada.nextLine());
        System.out.print("Digite la cantidad de horas semanales que posee este curso: ");
        Integer horas = Integer.parseInt(entrada.nextLine());
        entrada.close();
        Curso curso = new Curso(id, codigo, nombre, creditos, horas);
        this.control.modificarCurso(curso);
        System.out.print("\n");
    }

    public void eliminarCurso() throws SQLException, GlobalException, NoDataException {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite el Id del Curso a Eliminar: ");
        String id = entrada.nextLine();
        entrada.close();
        this.control.eliminar(id);
        System.out.print("\n");

    }

    public void ingresarNuevoCurso() throws SQLException, GlobalException, NoDataException {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite el Id del Curso que desea agregar: ");
        String id = entrada.nextLine();
        System.out.print("Digite el codigo del curso: ");
        String codigo = entrada.nextLine();
        System.out.print("Digite el nombre del curso: ");
        String nombre = entrada.nextLine();
        System.out.print("Digite los creditos que vale este curso: ");
        Integer creditos = Integer.parseInt(entrada.nextLine());
        System.out.print("Digite la cantidad de horas semanales que posee este curso: ");
        Integer horas = Integer.parseInt(entrada.nextLine());
        entrada.close();
        Curso curso = new Curso(id, codigo, nombre, creditos, horas);
        this.control.insertarCurso(curso);
        System.out.print("\n");

    }

    public void mostrarCursos() throws GlobalException, NoDataException, SQLException {
        LinkedList<Curso> curso = this.control.listar();
        int cont = 0;
        for (int i = 0; i < curso.size(); i++) {
            cont++;
            System.out.print("--------------------------------\n");
            System.out.print("--------------------------------\n");
            System.out.print("Curso Numero: " + cont + "\n");
            System.out.print("Id: " + curso.get(i).getId() + "\n");
            System.out.print("Codigo: " + curso.get(i).getCodigo() + "\n");
            System.out.print("Nombre: " + curso.get(i).getNombre() + "\n");
            System.out.print("Creditos: " + curso.get(i).getCreditos() + "\n");
            System.out.print("Horas semanales: " + curso.get(i).getHorasSemanales() + "\n");
            System.out.print("\n");

        }
        MostrarMenu();
    }

    public void buscarPorCodigo() throws GlobalException, NoDataException, SQLException {
        System.out.print("Digite el codigo del Curso a Buscar: ");
        Scanner entrada = new Scanner(System.in);
        String codigo = entrada.nextLine();
        entrada.close();
        LinkedList<Curso> curso = this.control.buscarPorCodigo(codigo);

        System.out.print("--------------------------------\n");
        System.out.print("--------------------------------\n");
        System.out.print("Id: " + curso.get(0).getId() + "\n");
        System.out.print("Codigo: " + curso.get(0).getCodigo() + "\n");
        System.out.print("Nombre: " + curso.get(0).getNombre() + "\n");
        System.out.print("Creditos: " + curso.get(0).getCreditos() + "\n");
        System.out.print("Horas semanales: " + curso.get(0).getHorasSemanales() + "\n");
        System.out.print("\n");
    }

}
