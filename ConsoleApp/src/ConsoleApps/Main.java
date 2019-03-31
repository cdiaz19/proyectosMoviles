/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConsoleApps;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import Control.ControlPrincipal;
import java.sql.SQLException;

/**
 *
 * @author cdiaz
 */
public class Main {
    public static void main(String[] args) throws SQLException, GlobalException, NoDataException {
        ControlPrincipal controlPrincipal = new ControlPrincipal();
    }
}
