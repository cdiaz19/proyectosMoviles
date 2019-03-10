/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;

import LogicaDeNegocio.Carrera;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Alejandro
 */
public class ServicioCarrera extends Servicio {
    private static final String LISTARCARRERAS = "{?=call listarCarreras()}";
    private static final String INSERTARCARRERA = "{call insertarCarrera(?,?,?,?)}";
    private static final String MODIFICARCARRERA = "{call modificarCarrera(?,?,?,?)}";
    private static final String BUSCARPORCODE = "{?=call buscarCodeNombre(?)}";
    private static final String BUSCARPORNOMBRE = "{?=call buscarCarreraNombre(?)}";
    private static final String ELIMINARCARRERA = "{call eliminarCarrera(?)}";
    
    
    
    
    public static ServicioCarrera getInstancia() {
        return INSTANCIA == null ? (INSTANCIA = new ServicioCarrera()) : INSTANCIA;
    }
    
    public LinkedList<Carrera> listarCarreras()
            throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        LinkedList<Carrera> coleccion = new LinkedList();
        Carrera carrera= null;
        CallableStatement pstmt = null;
        try {
            pstmt = this.conexion.prepareCall(LISTARCARRERAS);
            pstmt.registerOutParameter(1, -10);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                carrera = new Carrera(
                            rs.getString("id"),
                            rs.getString("codigo"),
                            rs.getString("nombre"),
                            rs.getString("titulo") 
                            
                            );
                coleccion.add(carrera);
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
    public void insertarCarrera(Carrera carrera)
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
            pstmt = this.conexion.prepareCall(INSERTARCARRERA);
            pstmt.setString(1, carrera.getId());
            pstmt.setString(2, carrera.getCodigo());
            pstmt.setString(3, carrera.getNombre());
            pstmt.setString(4, carrera.getTitulo());
            
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
    public void modificarCarrera(Carrera carrera)
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
            pstmt = this.conexion.prepareStatement(MODIFICARCARRERA);
            pstmt.setString(1, carrera.getId());
            pstmt.setString(2, carrera.getCodigo());
            pstmt.setString(3, carrera.getNombre());
            pstmt.setString(4, carrera.getTitulo());
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
    public LinkedList<Carrera> buscarPorCodigo(String codigo)
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
        Carrera carrera = null;
        CallableStatement pstmt = null;
        try {
            pstmt = this.conexion.prepareCall(BUSCARPORCODE);
            pstmt.registerOutParameter(1, -10);
            pstmt.setString(2, codigo);
            pstmt.execute();
            
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                carrera = new Carrera(
                            rs.getString("id"),
                            rs.getString("codigo"),
                            rs.getString("nombre"),
                            rs.getString("titulo") 
                            
                            );
                coleccion.add(carrera);
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
    public LinkedList<Carrera> buscarPorNombre(String nombre)
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
        Carrera carrera = null;
        CallableStatement pstmt = null;
        try {
            pstmt = this.conexion.prepareCall(BUSCARPORNOMBRE);
            pstmt.registerOutParameter(1, -10);
            pstmt.setString(2, nombre);
            pstmt.execute();
            
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                carrera = new Carrera(
                            rs.getString("id"),
                            rs.getString("codigo"),
                            rs.getString("nombre"),
                            rs.getString("titulo") 
                            
                            );
                coleccion.add(carrera);
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
      pstmt = this.conexion.prepareStatement(ELIMINARCARRERA);
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
    
    
    private static ServicioCarrera INSTANCIA = null;
    
}
