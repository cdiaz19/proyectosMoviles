package Presentacion;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import Control.ControlPrincipal;
import Control.ControlPresentaProfesor;
import Control.ControlProfesor;
import LogicaDeNegocio.Profesor;
import LogicaDeNegocio.Usuario;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author cdiaz
 */
public class PresentacionProfesor {

    ControlProfesor control = new ControlProfesor();

    public PresentacionProfesor() {
    }

    public void MostrarMenu() throws SQLException, GlobalException, NoDataException {
        Scanner userInput = new Scanner(System.in);
        String READ_MENU;

        // Display menu graphics
        System.out.println("*****************************************");
        System.out.println("|   Mantenimientos Profesores .         |");
        System.out.println("*****************************************");
        System.out.println("| Opciones:                             |");
        System.out.println("|        1. Listar Profesores           |");
        System.out.println("|        2. Buscar Profesor             |");
        System.out.println("|        3. Ingresar Profesor           |");
        System.out.println("|        4. Actualizar Profesor         |");
        System.out.println("|        5. Eliminar Profesores         |");
        System.out.println("|        6. Cerrar Mantenimiento        |");
        System.out.println("*****************************************");

        System.out.print("Seleccione opcion: ");

        READ_MENU = userInput.next();

        switch (READ_MENU) {
            case "1":
                mostrarProfesores();
                break;
            case "2":
                buscarPorCedula();
                break;
            case "3":
                ingresarNuevoProfesor();
                break;
            case "4":
                actualizarProfesor();
                break;
            case "5":
                eliminarProfesor();
                break;
            case "6":
                ControlPrincipal controlPrincipal = new ControlPrincipal();
                break;
            default:
                System.out.println("Invalid selection");
                break;
        }
    }

    public void actualizarProfesor() throws SQLException, GlobalException, NoDataException {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite el Id del Profesor que desea modificar: ");
        String id = entrada.nextLine();
        System.out.print("Digite la cedula del Profesor: ");
        String cedula = entrada.nextLine();
        System.out.print("Digite el nombre del Profesor: ");
        String nombre = entrada.nextLine();
        System.out.print("Digite el Correo del profesor del Profesor: ");
        String correo = entrada.nextLine();
        System.out.print("Digite el Telefono del profesor del Profesor: ");
        Integer telefono = Integer.parseInt(entrada.nextLine());
        System.out.print("Digite la contrasena del profesor del Profesor: ");
        String contrasena = entrada.nextLine();
        entrada.close();
        Usuario usuario = new Usuario(id, cedula, contrasena);
        Profesor profesor = new Profesor(id, nombre, correo, telefono, usuario);
        this.control.modificarProfesor(profesor, usuario);
        System.out.print("\n");
        MostrarMenu();
    }

    public void eliminarProfesor() throws SQLException, GlobalException, NoDataException {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite el Id del Profesor a Eliminar: ");
        String id = entrada.nextLine();
        entrada.close();
        this.control.eliminar(id);
        System.out.print("\n");
    }

    public void ingresarNuevoProfesor() throws SQLException {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite el Id del Profesor: ");
        String id = entrada.nextLine();
        System.out.print("Digite la cedula del Profesor: ");
        String cedula = entrada.nextLine();
        System.out.print("Digite el nombre del Profesor: ");
        String nombre = entrada.nextLine();
        System.out.print("Digite el Correo del profesor del Profesor: ");
        String correo = entrada.nextLine();
        System.out.print("Digite el Telefono del profesor del Profesor: ");
        Integer telefono = Integer.parseInt(entrada.nextLine());
        System.out.print("Digite la contrasena del profesor del Profesor: ");
        String contrasena = entrada.nextLine();
        entrada.close();
        Usuario usuario = new Usuario(id, cedula, contrasena);
        Profesor profesor = new Profesor(id, nombre, correo, telefono, usuario);
        
        this.control.insertarProfesor(profesor, usuario);
        System.out.print("\n");
    }

    public void mostrarProfesores() throws GlobalException, NoDataException, SQLException {
        try {
            LinkedList<Profesor> profesor = this.control.listar();
            int cont = 0;
            for (int i = 0; i < profesor.size(); i++) {
                cont++;
                System.out.print("--------------------------------\n");
                System.out.print("--------------------------------\n");
                System.out.print("Profesor Numero: " + cont + "\n");
                System.out.print("Id: " + profesor.get(i).getId() + "\n");
                System.out.print("Cedula: " + profesor.get(i).getUsuario().getCedula() + "\n");
                System.out.print("Nombre: " + profesor.get(i).getNombre() + "\n");
                System.out.print("Correo: " + profesor.get(i).getCorreo() + "\n");
                System.out.print("Telefono: " + profesor.get(i).getTelefono() + "\n");
                System.out.print("Contrasena: " + profesor.get(i).getUsuario().getContrasena() + "\n");
                System.out.print("\n");

            }
        } catch (GlobalException | NoDataException ex) {
            System.err.println(ex);
        }
        
        MostrarMenu();
    }

    public void buscarPorCedula() throws GlobalException, NoDataException, SQLException {
        System.out.print("Digite la cedula del profesor a Buscar: ");
        Scanner entrada = new Scanner(System.in);
        String cedula = entrada.nextLine();
        entrada.close();
        
        try {
            LinkedList<Profesor> profesor = this.control.buscarPorCedula(cedula);

            System.out.print("--------------------------------\n");
            System.out.print("--------------------------------\n");
            System.out.print("Id: " + profesor.get(0).getId() + "\n");
            System.out.print("Cedula: " + profesor.get(0).getUsuario().getCedula() + "\n");
            System.out.print("Nombre: " + profesor.get(0).getNombre() + "\n");
            System.out.print("Correo: " + profesor.get(0).getCorreo() + "\n");
            System.out.print("Telefono: " + profesor.get(0).getTelefono() + "\n");
            System.out.print("Contrasena: " + profesor.get(0).getUsuario().getContrasena() + "\n");
          
        } catch (GlobalException | NoDataException ex) {
            System.err.println(ex);
        }
    }
}
    
    
