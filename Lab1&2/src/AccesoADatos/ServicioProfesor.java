/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;

import LogicaDeNegocio.Profesor;
import java.io.PrintStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
/**
 *
 * @author Alejandro
 */
public class ServicioProfesor extends Servicio {
    private static final String INSERTARPROFESOR = "{call insertarProfesor(?,?,?,?,?,?)}";
  private static final String LISTARPROFESORES = "{?=call listarProfesores()}";
  private static final String BUSCARPROFESOR = "{?=call buscarProfesor(?)}";
  private static final String MODIFICARPROFESOR = "{call modificarProfesor(?,?,?,?,?,?)}";
  private static final String ELIMINARPROFESOR = "{call eliminarProfesor(?)}";
  private static final String BUSCARNOMBRES = "{call buscarNombres(?)}";
  private static final String BUSCARTIPOS = "{call buscarTipos(?)}";
  
  public static ServicioProfesor getInstancia(){
    return INSTANCIA == null ? (INSTANCIA = new ServicioProfesor()) : INSTANCIA;
  }
  
  public LinkedList<Profesor> listar()
    throws GlobalException, NoDataException {
    try {
      conectar();
    }
    catch (ClassNotFoundException ex) {
      throw new GlobalException("No se ha localizado el Driver");
    }
    catch (SQLException e) {
      throw new NoDataException("La base de datos no se encuentra disponible");
    }
    
    ResultSet rs = null;
    LinkedList<Profesor> coleccion = new LinkedList();
    Profesor profesor = null;
    CallableStatement pstmt = null;
    try{
      pstmt = this.conexion.prepareCall(LISTARPROFESORES);
      pstmt.registerOutParameter(1, -10);
      pstmt.execute();
      rs = (ResultSet)pstmt.getObject(1);
      while (rs.next()) {
        profesor = new Profesor(rs.getString("id"), rs.getString("cedula"),rs.getString("nombre"),rs.getString("email"),rs.getString("contrasena"),rs.getInt("telefono"));
        coleccion.add(profesor);
      }
      try {
        if (rs != null) {
          rs.close();
        }
        if (pstmt != null) {
          pstmt.close();
        }
        desconectar();
      }
      catch (SQLException e) {
        throw new GlobalException("Estatutos invalidos o nulos");
      }
      if (coleccion == null) {
        //break label275;
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
      
      throw new GlobalException("Sentencia no valida");
    }
    finally {
      try {
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
  
  public void insertar(Profesor profesor)
    throws GlobalException, NoDataException {
    try {
      conectar();
    }
    catch (ClassNotFoundException e) {
      throw new GlobalException("No se ha localizado el driver");
    }
    catch (SQLException e) {
      throw new NoDataException("La base de datos no se encuentra disponible");
    }
    CallableStatement pstmt = null;
    
    try {
      pstmt = this.conexion.prepareCall(INSERTARPROFESOR);
      pstmt.setString(1, profesor.getId());
      pstmt.setString(2, profesor.getCedula());
      pstmt.setString(3, profesor.getNombre());
      pstmt.setString(4, profesor.getEmail());
      pstmt.setString(5, profesor.getContrasena());
      pstmt.setInt(6, profesor.getTelefono());
      boolean resultado = pstmt.execute();
      if (resultado == true) {
        throw new NoDataException("No se realizo la inserci�n");
      }
      return;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      throw new GlobalException("Llave duplicada");
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
  
  public void modificar(Profesor profesor)
    throws GlobalException, NoDataException {
    try {
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
    
    try {
      pstmt = this.conexion.prepareStatement(MODIFICARPROFESOR);
      pstmt.setString(1, profesor.getId());
      pstmt.setString(2, profesor.getCedula());
      pstmt.setString(3, profesor.getNombre());
      pstmt.setString(4, profesor.getEmail());
      pstmt.setString(5, profesor.getContrasena());
      pstmt.setInt(6, profesor.getTelefono());
      int resultado = pstmt.executeUpdate();
      if (resultado != 0) {
        throw new NoDataException("No se realizo la actualizaci�n");
      }
      System.out.println("\nModificaci�n Satisfactoria!"); return;
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
  
  public void eliminar(String codigo)
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
      pstmt = this.conexion.prepareStatement(ELIMINARPROFESOR);
      pstmt.setString(1, codigo);
      
      int resultado = pstmt.executeUpdate();
      if (resultado != 0) {
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
  
  public LinkedList<Profesor> buscar(String codigo)
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
    ResultSet rs = null;
    LinkedList coleccion = new LinkedList();
    Profesor profesor = null;
    CallableStatement pstmt = null;
    try
    {
      pstmt = this.conexion.prepareCall(BUSCARPROFESOR);
      pstmt.registerOutParameter(1, -10);
      pstmt.setString(2, codigo);
      pstmt.execute();
      rs = (ResultSet)pstmt.getObject(1);
      while (rs.next())
      {
        profesor = new Profesor(rs.getString("id"), rs.getString("cedula"), rs.getString("nombre"),rs.getString("email"),rs.getString("contrasena"), rs.getInt("telefono"));
        coleccion.add(profesor);
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
        //break label287;
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
      label287:
      throw new NoDataException("No hay datos");
    }
    return coleccion;
  }
  
  public LinkedList<Profesor> buscarNombres(String nombres)
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
    ResultSet rs = null;
    LinkedList coleccion = new LinkedList();
    Profesor profesor = null;
    CallableStatement pstmt = null;
    try
    {
      pstmt = this.conexion.prepareCall(BUSCARNOMBRES);
      pstmt.registerOutParameter(1, -10);
      pstmt.setString(2, nombres);
      pstmt.execute();
      rs = (ResultSet)pstmt.getObject(1);
      while (rs.next())
      {
        profesor = new Profesor(rs.getString("id"), rs.getString("cedula"), rs.getString("nombre"),rs.getString("email"),rs.getString("contrasena"), rs.getInt("telefono"));
        coleccion.add(profesor);
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
        //break label287;
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
      label287:
      throw new NoDataException("No hay datos");
    }
    return coleccion;
  }
  
  private static ServicioProfesor INSTANCIA = null;
}



