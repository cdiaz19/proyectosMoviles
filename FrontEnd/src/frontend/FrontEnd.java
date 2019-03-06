/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import Control.ControlCiclos;
import Control.ControlProfesores;

/**
 *
 * @author Alejandro
 */
public class FrontEnd {

    public static void main(String[] args) throws GlobalException, NoDataException {
        // TODO code application logic here
        ControlCiclos control = new ControlCiclos();
        
    }
    public static final int MODO_AGREGAR = 0;
    public static final int MODO_EDITAR = 1;
}
