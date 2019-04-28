/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import Presentacion.PresentacionCurso;
import java.sql.SQLException;

/**
 *
 * @author cdiaz
 */
public class ControlPresentaCurso {

    PresentacionCurso view;

    public ControlPresentaCurso() throws SQLException, GlobalException, NoDataException {
        this(new PresentacionCurso());
        view.MostrarMenu();
    }

    public ControlPresentaCurso(PresentacionCurso view) {
        this.view = view;
    }
}
