/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import LogicaDeNegocio.Curso;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alejandro
 */

public class TableCurso extends AbstractTableModel {
    
    String[] colNames = new String[11];
    LinkedList<Curso> filas;
    int[] columnas;

    public static final int ID = 0;
    public static final int CODIGO = 1;
    public static final int NOMBRE = 2;
    public static final int CREDITOS = 3;
    public static final int HORAS = 4;

    public TableCurso(int[] cols, LinkedList<Curso> rows) {
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

    public Curso getRowAt(int row) {
        return (Curso) this.filas.get(row);
    }

    public Object getValueAt(int row, int col) {
        Curso curso = (Curso) this.filas.get(row);
        switch (this.columnas[col]) {
            case 0:
                return curso.getId();
            case 1:
                return curso.getCodigo();
            case 2:
                return curso.getNombre();
            case 3:
                return curso.getCreditos();
            case 4:
                return curso.getHorasSemanales();
        }
        return "";
    }

    private void initColNames() {
        this.colNames[0] = "ID";
        this.colNames[1] = "CODIGO";
        this.colNames[2] = "NOMBRE";
        this.colNames[3] = "CREDITOS";
        this.colNames[4] = "HORAS";
    }

    public LinkedList<Curso> buscar(String id, LinkedList<Curso> original) {
        LinkedList<Curso> aux = new LinkedList();
        for (Curso e : original) {
            if (e.getId().equals(id)) {
                aux.add(e);
            }
        }
        return aux;
    }
}
