/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import LogicaDeNegocio.Profesor;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alejandro
 */

public class TableProfesor extends AbstractTableModel {

    public static final int ID = 0;
    public static final int NOMBRE = 1;
    public static final int CEDULA = 2;
    public static final int EMAIL = 3;
    public static final int CONTRASENA = 4;
    public static final int TELEFONO = 5;

    public TableProfesor(int[] cols, LinkedList<Profesor> rows) {
        this.columnas = cols;
        this.filas = rows;
        initColNames();
    }

    public int getColumnCount() {
        return this.columnas.length;
    }

    public String getColumnName(int col) {
        return this.colNames[this.columnas[col]];
    }

//    public Class<?> getColumnClass(int col) {
//        switch (this.columnas[col]) {
//            case 4:
//                return Icon.class;
//        }
//        return super.getColumnClass(col);
//    }

    public int getRowCount() {
        return this.filas.size();
    }

    public Profesor getRowAt(int row) {
        return (Profesor) this.filas.get(row);
    }

    public Object getValueAt(int row, int col) {
        Profesor profesor = (Profesor) this.filas.get(row);
        switch (this.columnas[col]) {
            case 0:
                return profesor.getId();
            case 1:
                return profesor.getUsuario().getCedula();
            case 2:
                return profesor.getNombre();
            case 3:
                return profesor.getCorreo();
            case 4:
                return profesor.getUsuario().getContrasena();
            case 5:
                return Integer.valueOf(profesor.getTelefono());
        }
        return "";
    }

    String[] colNames = new String[11];
    LinkedList<Profesor> filas;
    int[] columnas;

    private void initColNames() {
        this.colNames[0] = "ID";
        this.colNames[1] = "Cedula";
        this.colNames[2] = "Nombre";
        this.colNames[3] = "Correo";
        this.colNames[4] = "Contrasena";
        this.colNames[5] = "Telefono";
    }

    public LinkedList<Profesor> buscar(String cedula, LinkedList<Profesor> original) {
        LinkedList<Profesor> aux = new LinkedList();
        for (Profesor e : original) {
            if (e.getUsuario().getCedula().equals(cedula)) {
                aux.add(e);
            }
        }
        return aux;
    }
}
