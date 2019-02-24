/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;

import LogicaDeNegocio.Ciclo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author cdiaz
 */
public class ServicioCiclo extends Servicio {
     
    private static final String INSERTARCICLO = "{call insertarCiclo(?,?,?,?,?)}";
    private static final String BUSCARCICLO = "{?=call buscarCiclo(?)}";
    private static final String ELIMINARCICLO = "{?=call eliminarCiclo(?)}";
    private static final String ACTUALIZARCICLO ="{call modificarCiclo(?,?,?,?,?)}";    
   
    public ServicioCiclo() {
    }
    
    public boolean insertarCiclo(Ciclo ciclo) throws GlobalException, NoDataException {
        boolean resp = true;
        
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            resp=false;
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            resp=false;
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        
        CallableStatement pstmt = null;  
                                
        try {
            pstmt = conexion.prepareCall(INSERTARCICLO);                                    
            pstmt.setInt(1,ciclo.getId());
            pstmt.setInt(2,ciclo.getAnno());
            pstmt.setInt(3,ciclo.getNumero());
            pstmt.setString(5,ciclo.getFechaInicio());
            pstmt.setString(6,ciclo.getFechaFinal());
            
            boolean resultado = pstmt.execute();
            if (resultado == true) {
                resp=false;
                throw new NoDataException ("No se Inserto");
            } 
            
        } catch (SQLException e) {
            e.printStackTrace();
            resp=false;
            throw new GlobalException("Llave duplicada");
        } 
         finally {
            try {
                if (pstmt != null) {
                    pstmt.close();                    
                }
                desconectar();
            } catch (SQLException e) {
                resp=false;
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
         return resp;
    }
    
    public void actualizarCiclo(Ciclo ciclo ) throws GlobalException, NoDataException  {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conexion.prepareStatement(ACTUALIZARCICLO);
            pstmt.setInt(1,ciclo.getId());
            pstmt.setInt(2,ciclo.getAnno());
            pstmt.setInt(3,ciclo.getNumero());
            pstmt.setString(5,ciclo.getFechaInicio());
            pstmt.setString(6,ciclo.getFechaFinal());

            int resultado = pstmt.executeUpdate();
            
            if (resultado != 0) {
                throw new NoDataException ("No se actualizo");
            }
            else{
               System.out.println("\nModificacion Satisfactoria!");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }     
    
    public Ciclo buscarCiclo(String id ) throws GlobalException, NoDataException  {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Ciclo ciclo = null;
        CallableStatement pstmt=null; 
        
        try {            
            pstmt = conexion.prepareCall(BUSCARCICLO);        
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);         
            pstmt.setString(2,id);      
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            
            while (rs.next()) {
                ciclo = new Ciclo(rs.getInt("id"),
                                rs.getInt("anno"),
                                rs.getInt("numero"),
                                rs.getString("fechaInicio"),
                                rs.getString("fechaFinal"));
                coleccion.add(ciclo);
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
        
        if (coleccion == null || coleccion.size() == 0) {
            throw new NoDataException("No hay datos");
        }
        return ciclo;
    }
    
    public Ciclo eliminarCiclo(String id ) throws GlobalException, NoDataException  {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
          throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
          throw new NoDataException("La base de datos no se encuentra disponible");
        }
        
        ResultSet rs = null;     
        ArrayList coleccion = new ArrayList();
        Ciclo ciclo = null;
        CallableStatement pstmt=null;
        
        try {            
            pstmt = conexion.prepareCall(ELIMINARCICLO);            
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);            
            pstmt.setString(2,id);            
            pstmt.execute();

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
        if (coleccion == null || coleccion.size() == 0) {
            throw new NoDataException("No hay datos");
        }
        return ciclo;
    }
}