/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;
import LogicaDeNegocio.Curso;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
/**
 *
 * @author Alejandro
 */
public class ServicioCurso extends Servicio {
    
    private static final String LISTARCURSOS = "{?=call listarCursos()}";
     public static ServicioCurso getInstancia() {
        return INSTANCIA == null ? (INSTANCIA = new ServicioCurso()) : INSTANCIA;
    }
      
      public LinkedList<Curso> listarCursos()
            throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        LinkedList<Curso> coleccion = new LinkedList();
        Curso curso= null;
        CallableStatement pstmt = null;
        try {
            pstmt = this.conexion.prepareCall(LISTARCURSOS);
            pstmt.registerOutParameter(1, -10);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                curso = new Curso(
                            rs.getString("id"),
                            rs.getString("codigo"),
                            rs.getString("nombre"),
                            rs.getInt("creditos"),
                            rs.getInt("horasSemanales") 
                            
                            );
                coleccion.add(curso);
            }
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
            if (coleccion == null) {
                //break label275;
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion.size() == 0) {
            label275:
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }
      
      private static ServicioCurso INSTANCIA = null;
}
