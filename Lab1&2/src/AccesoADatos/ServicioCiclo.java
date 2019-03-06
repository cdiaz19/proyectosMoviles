/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;


import LogicaDeNegocio.Ciclo;
import LogicaDeNegocio.Usuario;
import java.io.PrintStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ServicioCiclo
  extends Servicio
{
  
  private static final String LISTARCICLOS = "{?=call listarCiclos()}";
    private static final String INSERTARPROFESOR = "{call insertarProfesor(?,?,?,?,?,?,?)}";
    private static final String MODIFICARPROFESOR = "{call modificarProfesor(?,?,?,?,?,?,?)}";
    private static final String BUSCARPROFESOR = "{?=call buscarProfesor(?)}";
    private static final String BUSCARNOMBRES = "{call buscarNombres(?)}";
    private static final String BUSCARCEDULAPROFESOR = "{call buscarCedulaProfesor(?)}";
  
  public static ServicioCiclo getInstancia()
  {
    return INSTANCIA == null ? (INSTANCIA = new ServicioCiclo()) : INSTANCIA;
  }
  
  public LinkedList<Ciclo> listarCiclos()
    throws GlobalException, NoDataException
  {
    try
    {
      conectar();
    }
    catch (ClassNotFoundException ex)
    {
      throw new GlobalException("No se ha localizado el Driver");
    }
    catch (SQLException e)
    {
      throw new NoDataException("La base de datos no se encuentra disponible");
    }
    ResultSet rs = null;
    LinkedList<Ciclo> coleccion = new LinkedList();
    Ciclo ciclo = null;
    CallableStatement pstmt = null;
    try
    {
      pstmt = this.conexion.prepareCall("{?=call listarCiclos()}");
      pstmt.registerOutParameter(1, -10);
      pstmt.execute();
      rs = (ResultSet)pstmt.getObject(1);
      while (rs.next())
      {
        ciclo = new Ciclo(rs.getString("id"),rs.getInt("anno"), rs.getInt("numero"), rs.getString("fechaInicio"), rs.getString("fechaFinal"));
        coleccion.add(ciclo);
      }
      try
      {
        if (rs != null) {
          rs.close();
        }
        if (pstmt != null) {
          pstmt.close();
        }
        desconectar();
      }
      catch (SQLException e)
      {
        throw new GlobalException("Estatutos invalidos o nulos");
      }
      if (coleccion == null) {
        //break label275;
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      
      throw new GlobalException("Sentencia no valida");
    }
    finally
    {
      try
      {
        if (rs != null) {
          rs.close();
        }
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
    if (coleccion.size() == 0) {
      label275:
      throw new NoDataException("No hay datos");
    }
    return coleccion;
  }
  
  //  public void insertar(Producto producto)
//    throws GlobalException, NoDataException
//  {
//    try
//    {
//      conectar();
//    }
//    catch (ClassNotFoundException e)
//    {
//      throw new GlobalException("No se ha localizado el driver");
//    }
//    catch (SQLException e)
//    {
//      throw new NoDataException("La base de datos no se encuentra disponible");
//    }
//    CallableStatement pstmt = null;
//    try
//    {
//      pstmt = this.conexion.prepareCall("{call insertarProducto (?,?,?,?,?)}");
//      pstmt.setString(1, producto.getCodigo());
//      pstmt.setString(2, producto.getNombre());
//      pstmt.setInt(3, producto.getImportado());
//      pstmt.setFloat(4, producto.getPrecio());
//      pstmt.setString(5, producto.getTipo().getDescripcion());
//      
//      boolean resultado = pstmt.execute();
//      if (resultado == true) {
//        throw new NoDataException("No se realizo la inserci�n");
//      }
//      return;
//    }
//    catch (SQLException e)
//    {
//      e.printStackTrace();
//      throw new GlobalException("Llave duplicada");
//    }
//    finally
//    {
//      try
//      {
//        if (pstmt != null) {
//          pstmt.close();
//        }
//        desconectar();
//      }
//      catch (SQLException e)
//      {
//        throw new GlobalException("Estatutos invalidos o nulos");
//      }
//    }
//  }
//  
//  public void modificar(Producto producto)
//    throws GlobalException, NoDataException
//  {
//    try
//    {
//      conectar();
//    }
//    catch (ClassNotFoundException e)
//    {
//      throw new GlobalException("No se ha localizado el driver");
//    }
//    catch (SQLException e)
//    {
//      throw new NoDataException("La base de datos no se encuentra disponible");
//    }
//    PreparedStatement pstmt = null;
//    try
//    {
//      pstmt = this.conexion.prepareStatement("{call modificarProducto (?,?,?,?,?)}");
//      pstmt.setString(1, producto.getCodigo());
//      pstmt.setString(2, producto.getNombre());
//      pstmt.setInt(3, producto.getImportado());
//      pstmt.setFloat(4, producto.getPrecio());
//      pstmt.setString(5, producto.getTipo().getDescripcion());
//      int resultado = pstmt.executeUpdate();
//      if (resultado != 0) {
//        throw new NoDataException("No se realizo la actualizaci�n");
//      }
//      System.out.println("\nModificaci�n Satisfactoria!"); return;
//    }
//    catch (SQLException e)
//    {
//      throw new GlobalException("Sentencia no valida");
//    }
//    finally
//    {
//      try
//      {
//        if (pstmt != null) {
//          pstmt.close();
//        }
//        desconectar();
//      }
//      catch (SQLException e)
//      {
//        throw new GlobalException("Estatutos invalidos o nulos");
//      }
//    }
//  }
//  
//  public void eliminar(String codigo)
//    throws GlobalException, NoDataException
//  {
//    try
//    {
//      conectar();
//    }
//    catch (ClassNotFoundException e)
//    {
//      throw new GlobalException("No se ha localizado el driver");
//    }
//    catch (SQLException e)
//    {
//      throw new NoDataException("La base de datos no se encuentra disponible");
//    }
//    PreparedStatement pstmt = null;
//    try
//    {
//      pstmt = this.conexion.prepareStatement("{call eliminarProducto(?)}");
//      pstmt.setString(1, codigo);
//      
//      int resultado = pstmt.executeUpdate();
//      if (resultado != 0) {
//        throw new NoDataException("No se realizo el borrado");
//      }
//      System.out.println("\nEliminaci�n Satisfactoria!"); return;
//    }
//    catch (SQLException e)
//    {
//      throw new GlobalException("Sentencia no valida");
//    }
//    finally
//    {
//      try
//      {
//        if (pstmt != null) {
//          pstmt.close();
//        }
//        desconectar();
//      }
//      catch (SQLException e)
//      {
//        throw new GlobalException("Estatutos invalidos o nulos");
//      }
//    }
//  }
//  
//  public LinkedList<Producto> buscar(String codigo)
//    throws GlobalException, NoDataException
//  {
//    try
//    {
//      conectar();
//    }
//    catch (ClassNotFoundException e)
//    {
//      throw new GlobalException("No se ha localizado el driver");
//    }
//    catch (SQLException e)
//    {
//      throw new NoDataException("La base de datos no se encuentra disponible");
//    }
//    ResultSet rs = null;
//    LinkedList coleccion = new LinkedList();
//    Producto producto = null;
//    CallableStatement pstmt = null;
//    try
//    {
//      pstmt = this.conexion.prepareCall("{?=call buscarProducto(?)}");
//      pstmt.registerOutParameter(1, -10);
//      pstmt.setString(2, codigo);
//      pstmt.execute();
//      rs = (ResultSet)pstmt.getObject(1);
//      while (rs.next())
//      {
//        producto = new Producto(rs.getString("codigo"), rs.getString("nombre"), rs.getInt("importado"), rs.getFloat("precio"), new Tipo(rs.getString("tipo")));
//        coleccion.add(producto);
//      }
//      try
//      {
//        if (rs != null) {
//          rs.close();
//        }
//        if (pstmt != null) {
//          pstmt.close();
//        }
//        desconectar();
//      }
//      catch (SQLException e)
//      {
//        throw new GlobalException("Estatutos invalidos o nulos");
//      }
//      if (coleccion == null) {
//        //break label287;
//      }
//    }
//    catch (SQLException e)
//    {
//      e.printStackTrace();
//      
//      throw new GlobalException("Sentencia no valida");
//    }
//    finally
//    {
//      try
//      {
//        if (rs != null) {
//          rs.close();
//        }
//        if (pstmt != null) {
//          pstmt.close();
//        }
//        desconectar();
//      }
//      catch (SQLException e)
//      {
//        throw new GlobalException("Estatutos invalidos o nulos");
//      }
//    }
//    if (coleccion.size() == 0) {
//      label287:
//      throw new NoDataException("No hay datos");
//    }
//    return coleccion;
//  }
//  
//  public LinkedList<Producto> buscarNombres(String nombres)
//    throws GlobalException, NoDataException
//  {
//    try
//    {
//      conectar();
//    }
//    catch (ClassNotFoundException e)
//    {
//      throw new GlobalException("No se ha localizado el driver");
//    }
//    catch (SQLException e)
//    {
//      throw new NoDataException("La base de datos no se encuentra disponible");
//    }
//    ResultSet rs = null;
//    LinkedList coleccion = new LinkedList();
//    Producto producto = null;
//    CallableStatement pstmt = null;
//    try
//    {
//      pstmt = this.conexion.prepareCall("{call buscarNombres(?)}");
//      pstmt.registerOutParameter(1, -10);
//      pstmt.setString(2, nombres);
//      pstmt.execute();
//      rs = (ResultSet)pstmt.getObject(1);
//      while (rs.next())
//      {
//        producto = new Producto(rs.getString("codigo"), rs.getString("nombre"), rs.getInt("importado"), rs.getFloat("precio"), new Tipo(rs.getString("tipo")));
//        coleccion.add(producto);
//      }
//      try
//      {
//        if (rs != null) {
//          rs.close();
//        }
//        if (pstmt != null) {
//          pstmt.close();
//        }
//        desconectar();
//      }
//      catch (SQLException e)
//      {
//        throw new GlobalException("Estatutos invalidos o nulos");
//      }
//      if (coleccion == null) {
//        //break label287;
//      }
//    }
//    catch (SQLException e)
//    {
//      e.printStackTrace();
//      
//      throw new GlobalException("Sentencia no valida");
//    }
//    finally
//    {
//      try
//      {
//        if (rs != null) {
//          rs.close();
//        }
//        if (pstmt != null) {
//          pstmt.close();
//        }
//        desconectar();
//      }
//      catch (SQLException e)
//      {
//        throw new GlobalException("Estatutos invalidos o nulos");
//      }
//    }
//    if (coleccion.size() == 0) {
//      label287:
//      throw new NoDataException("No hay datos");
//    }
//    return coleccion;
//  }
//  
//  public LinkedList<Producto> buscarTipos(String tipos)
//    throws GlobalException, NoDataException
//  {
//    try
//    {
//      conectar();
//    }
//    catch (ClassNotFoundException e)
//    {
//      throw new GlobalException("No se ha localizado el driver");
//    }
//    catch (SQLException e)
//    {
//      throw new NoDataException("La base de datos no se encuentra disponible");
//    }
//    ResultSet rs = null;
//    LinkedList coleccion = new LinkedList();
//    Producto producto = null;
//    CallableStatement pstmt = null;
//    try
//    {
//      pstmt = this.conexion.prepareCall("{call buscarTipos(?)}");
//      pstmt.registerOutParameter(1, -10);
//      pstmt.setString(2, tipos);
//      pstmt.execute();
//      rs = (ResultSet)pstmt.getObject(1);
//      while (rs.next())
//      {
//        producto = new Producto(rs.getString("codigo"), rs.getString("nombre"), rs.getInt("importado"), rs.getFloat("precio"), new Tipo(rs.getString("tipo")));
//        coleccion.add(producto);
//      }
//      try
//      {
//        if (rs != null) {
//          rs.close();
//        }
//        if (pstmt != null) {
//          pstmt.close();
//        }
//        desconectar();
//      }
//      catch (SQLException e)
//      {
//        throw new GlobalException("Estatutos invalidos o nulos");
//      }
//      if (coleccion == null) {
//        //break label287;
//      }
//    }
//    catch (SQLException e)
//    {
//      e.printStackTrace();
//      
//      throw new GlobalException("Sentencia no valida");
//    }
//    finally
//    {
//      try
//      {
//        if (rs != null) {
//          rs.close();
//        }
//        if (pstmt != null) {
//          pstmt.close();
//        }
//        desconectar();
//      }
//      catch (SQLException e)
//      {
//        throw new GlobalException("Estatutos invalidos o nulos");
//      }
//    }
//    if (coleccion.size() == 0) {
//      label287:
//      throw new NoDataException("No hay datos");
//    }
//    return coleccion;
//  }
  
 private static ServicioCiclo INSTANCIA = null;
  }