/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import Presentacion.PresentacionCiclo;
import java.sql.SQLException;

/**
 *
 * @author cdiaz
 */
public class ControlPresentaCiclo {

    PresentacionCiclo view;

    public ControlPresentaCiclo() throws SQLException, GlobalException, NoDataException {
        this(new PresentacionCiclo());
        view.MostrarMenu();
    }

    public ControlPresentaCiclo(PresentacionCiclo view) {
        this.view = view;
    }
}
