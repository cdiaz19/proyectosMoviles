/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import Presentacion.PresentacionCarrera;
import java.sql.SQLException;

/**
 *
 * @author cdiaz
 */
public class ControlPresentaCarrera {

    PresentacionCarrera view;

    public ControlPresentaCarrera() throws SQLException, GlobalException, NoDataException {
        this(new PresentacionCarrera());
        view.MostrarMenu();
    }

    public ControlPresentaCarrera(PresentacionCarrera view) {
        this.view = view;
    }
}