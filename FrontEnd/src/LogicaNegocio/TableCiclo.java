/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import LogicaDeNegocio.Ciclo;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Cdiaz
 */

public class TableCiclo extends AbstractTableModel {
    
    String[] colNames = new String[11];
    LinkedList<Ciclo> filas;
    int[] columnas;

    public static final int ID = 0;
    public static final int ANNO = 1;
    public static final int NUMERO = 2;
    public static final int FECHAINICIO = 3;
    public static final int FECHAFINAL = 4;

    public TableCiclo(int[] cols, LinkedList<Ciclo> rows) {
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

    public Ciclo getRowAt(int row) {
        return (Ciclo) this.filas.get(row);
    }

    public Object getValueAt(int row, int col) {
        Ciclo ciclo = (Ciclo) this.filas.get(row);
        switch (this.columnas[col]) {
            case 0:
                return ciclo.getId();
            case 1:
                return ciclo.getAnno();
            case 2:
                return ciclo.getNumero();
            case 3:
                return ciclo.getFechaInicio();
            case 4:
                return ciclo.getFechaFinal();
        }
        return "";
    }

    
    private void initColNames() {
        this.colNames[0] = "ID";
        this.colNames[1] = "Anno";
        this.colNames[2] = "Numero";
        this.colNames[3] = "Fecha Inicio";
        this.colNames[4] = "Fecha Final";
    }
}
