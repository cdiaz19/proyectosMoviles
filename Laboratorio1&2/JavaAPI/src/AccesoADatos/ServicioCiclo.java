/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;

import LogicaDeNegocio.Ciclo;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Alejandro
 */
public class ServicioCiclo extends Servicio {

    private static final String LISTARCICLOS = "{?=call listarCiclos()}";
    private static final String INSERTARCICLOS = "{call insertarCiclo(?,?,?,?,?)}";
    private static final String MODIFICARCICLO = "{call modificarCiclo(?,?,?,?,?)}";
    private static final String BUSCARPORCICLO = "{?=call buscarCiclo(?)}";
    private static final String BUSCARPORANNO = "{?=call buscarCicloPorAnno(?)}";
    private static final String ELIMINARCICLO = "{call eliminarCiclo(?)}";

    public static ServicioCiclo getInstancia() {
        return INSTANCIA == null ? (INSTANCIA = new ServicioCiclo()) : INSTANCIA;
    }

    public LinkedList<Ciclo> listarCiclos()
            throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        LinkedList<Ciclo> coleccion = new LinkedList();
        Ciclo ciclo= null;
        CallableStatement pstmt = null;
        try {
            pstmt = this.conexion.prepareCall(LISTARCICLOS);
            pstmt.registerOutParameter(1, -10);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                ciclo = new Ciclo(
                            rs.getString("id"), 
                            rs.getInt("anno"),
                            rs.getInt("numero"), 
                            rs.getString("fechaInicio"),
                            rs.getString("fechaFinal")
                            );
                coleccion.add(ciclo);
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

    public void insertarCiclo(Ciclo ciclo)
            throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;

        try {
            pstmt = this.conexion.prepareCall(INSERTARCICLOS);
            pstmt.setString(1, ciclo.getId());
            pstmt.setInt(2, ciclo.getAnno());
            pstmt.setInt(3, ciclo.getNumero());
            pstmt.setString(4, ciclo.getFechaInicio());
            pstmt.setString(5, ciclo.getFechaInicio());
            
            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la inserci�n");
            }
            return;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Llave duplicada");
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

    public void modificarCiclo(Ciclo ciclo)
            throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;

        try {
            pstmt = this.conexion.prepareStatement(MODIFICARCICLO);
            pstmt.setString(1, ciclo.getId());
            pstmt.setInt(2, ciclo.getAnno());
            pstmt.setInt(3, ciclo.getNumero());
            pstmt.setString(4, ciclo.getFechaInicio());
            pstmt.setString(5, ciclo.getFechaInicio());
            int resultado = pstmt.executeUpdate();
            if (resultado != 1) {
                throw new NoDataException("No se realizo la actualizaci�n");
            }
            System.out.println("\nModificaci�n Satisfactoria!");
            return;
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


    public LinkedList<Ciclo> buscarPorCiclo(String id)
            throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        LinkedList coleccion = new LinkedList();
        Ciclo ciclo = null;
        CallableStatement pstmt = null;
        try {
            pstmt = this.conexion.prepareCall(BUSCARPORCICLO);
            pstmt.registerOutParameter(1, -10);
            pstmt.setString(2, id);
            pstmt.execute();
            
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                ciclo = new Ciclo(
                            rs.getString("id"), 
                            rs.getInt("anno"),
                            rs.getInt("numero"), 
                            rs.getString("fechaInicio"),
                            rs.getString("fechaFinal")
                            );
                coleccion.add(ciclo);
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
                //break label287;
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
            label287:
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }
    
    public LinkedList<Ciclo> buscarPorAnno(String anno)
            throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        LinkedList coleccion = new LinkedList();
        Ciclo ciclo = null;
        CallableStatement pstmt = null;
        try {
            pstmt = this.conexion.prepareCall(BUSCARPORANNO);
            pstmt.registerOutParameter(1, -10);
            pstmt.setString(2, anno);
            pstmt.execute();
            
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                ciclo = new Ciclo(
                            rs.getString("id"), 
                            rs.getInt("anno"),
                            rs.getInt("numero"), 
                            rs.getString("fechaInicio"),
                            rs.getString("fechaFinal")
                            );
                coleccion.add(ciclo);
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
                //break label287;
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
            label287:
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }
    
    public void eliminar(String id)
    throws GlobalException, NoDataException
  {
    try
    {
      conectar();
    }
    catch (ClassNotFoundException e)
    {
      throw new GlobalException("No se ha localizado el driver");
    }
    catch (SQLException e)
    {
      throw new NoDataException("La base de datos no se encuentra disponible");
    }
    PreparedStatement pstmt = null;
    try
    {
      pstmt = this.conexion.prepareStatement(ELIMINARCICLO);
      pstmt.setString(1, id);
      
      int resultado = pstmt.executeUpdate();
      if (resultado != 1) {
        throw new NoDataException("No se realizo el borrado");
      }
      System.out.println("\nEliminaci�n Satisfactoria!"); return;
    }
    catch (SQLException e)
    {
      throw new GlobalException("Sentencia no valida");
    }
    finally
    {
      try
      {
        if (pstmt != null) {
          pstmt.close();
        }
        desconectar();
      }
      catch (SQLException e)
      {
        throw new GlobalException("Estatutos invalidos o nulos");
      }
    }
  }

    private static ServicioCiclo INSTANCIA = null;
}
