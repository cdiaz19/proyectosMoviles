/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import AccesoADatos.ServicioProfesor;
import LogicaDeNegocio.Profesor;

import Presentacion.PresentacionProfesor;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author cdiaz
 */
public class ControlPresentaProfesor {

    PresentacionProfesor view;

    public ControlPresentaProfesor() throws SQLException, GlobalException, NoDataException {
        this(new PresentacionProfesor());
        view.MostrarMenu();
    }

    public ControlPresentaProfesor(PresentacionProfesor view) {
        this.view = view;
    }

}
