/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author alejandro
 */
public class Servicio {

    protected Connection conexion = null;

    public Servicio() {
    }

    protected void conectar() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        this.conexion = DriverManager.getConnection("jdbc:postgresql://localhost/matricula", "matricula_user", "root");
    }

    protected void desconectar() throws SQLException {
        if (!this.conexion.isClosed()) {
            this.conexion.close();
        }
    }

    private Connection getJdbcMydbsource() throws NamingException {
        Context c = new InitialContext();
        try {
            return ((DataSource) c.lookup("jdbc/Mydbsource")).getConnection();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
