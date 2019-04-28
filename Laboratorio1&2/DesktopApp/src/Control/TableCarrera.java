/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import LogicaDeNegocio.Carrera;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author cdiaz
 */

public class TableCarrera extends AbstractTableModel {
    
    String[] colNames = new String[11];
    LinkedList<Carrera> filas;
    int[] columnas;

    public static final int ID = 0;
    public static final int CODIGO = 1;
    public static final int NOMBRE = 2;
    public static final int TITULO = 3;

    public TableCarrera(int[] cols, LinkedList<Carrera> rows) {
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

    public int getRowCount() {
        return this.filas.size();
    }

    public Carrera getRowAt(int row) {
        return (Carrera) this.filas.get(row);
    }

    public Object getValueAt(int row, int col) {
        Carrera carrera = (Carrera) this.filas.get(row);
        switch (this.columnas[col]) {
            case 0:
                return carrera.getId();
            case 1:
                return carrera.getCodigo();
            case 2:
                return carrera.getNombre();
            case 3:
                return carrera.getTitulo();
        }
        return "";
    }
    
    private void initColNames() {
        this.colNames[0] = "ID";
        this.colNames[1] = "Codigo";
        this.colNames[2] = "Nombre";
        this.colNames[3] = "Titulo";
    }
    
    public LinkedList<Carrera> buscar(String id, LinkedList<Carrera> original) {
        LinkedList<Carrera> aux = new LinkedList();
        for (Carrera e : original) {
            if (e.getId().equals(id)) {
                aux.add(e);
            }
        }
        return aux;
    }
    public LinkedList<Carrera> buscarPorNombre(String nombreCarrera, LinkedList<Carrera> original) {
        LinkedList<Carrera> aux = new LinkedList();
        for (Carrera e : original) {
            if (e.getNombre().equals(nombreCarrera)) {
                aux.add(e);
            }
        }
        return aux;
    }
}
