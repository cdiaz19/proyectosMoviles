/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import LogicaDeNegocio.Carrera;
import LogicaDeNegocio.ListaCurso;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author cdiaz
 */

public class TableListaCurso extends AbstractTableModel {
    
    String[] colNames = new String[11];
    LinkedList<ListaCurso> filas;
    int[] columnas;

    public static final int ID = 0;
    public static final int ANNO = 1;
    public static final int CARRERAID = 3;
    public static final int CURSOID = 4;
    public static final int CICLOID = 5;

    public TableListaCurso(int[] cols, LinkedList<ListaCurso> rows) {
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

    public ListaCurso getRowAt(int row) {
        return (ListaCurso) this.filas.get(row);
    }

    public Object getValueAt(int row, int col) {
        ListaCurso listaCurso = (ListaCurso) this.filas.get(row);
        
        switch (this.columnas[col]) {
            case 0:
                return listaCurso.getId();
            case 1:
                return listaCurso.getAnno();
            case 2:
                return listaCurso.getCarreraId();
            case 3:
                return listaCurso.getCursoId();
            case 4:
                return listaCurso.getCicloId();
        }
        return "";
    }
    
    private void initColNames() {
        this.colNames[0] = "ID";
        this.colNames[1] = "anno";
        this.colNames[2] = "carreraId";
        this.colNames[3] = "cursoId";
        this.colNames[4] = "cicloId";
    }
    
    public LinkedList<ListaCurso> buscar(String id, LinkedList<ListaCurso> original) {
        LinkedList<ListaCurso> aux = new LinkedList();
        for (ListaCurso e : original) {
            if (e.getId().equals(id)) {
                aux.add(e);
            }
        }
        return aux;
    }  
}
