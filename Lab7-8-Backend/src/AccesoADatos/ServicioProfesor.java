/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;

import LogicaDeNegocio.Profesor;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

/**
 *
 * @author alejandro
 */
public class ServicioProfesor extends Servicio {

    private static final String INSERTAR = "{call insertarprofesor(?,?,?,?)}";
    private static final String MODIFICAR = "{call actualizarprofesor(?,?,?,?)}";
    private static final String BUSCAR = "{?=call buscarprofesor(?)}";
    private static final String LISTAR = "{?=call listarprofesores()}";
    private static final String ELIMINAR = "{call eliminarprofesor(?)}";

    public void insertarProfesor(Profesor profesor)
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
            pstmt = this.conexion.prepareCall(INSERTAR);
            pstmt.setString(1, profesor.getCedula());
            pstmt.setString(2, profesor.getNombre());
            pstmt.setString(3, profesor.getEmail());
            pstmt.setInt(4, profesor.getTelefono());

            boolean resultado = pstmt.execute();
            if (resultado == true) {
                System.out.print("Agregado Correctamente");
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

    public void modificarProfesor(Profesor profesor)
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
            pstmt = this.conexion.prepareCall(MODIFICAR);
            pstmt.setString(1, profesor.getCedula());
            pstmt.setString(2, profesor.getNombre());
            pstmt.setString(3, profesor.getEmail());
            pstmt.setInt(4, profesor.getTelefono());

            boolean resultado = pstmt.execute();
            if (resultado == true) {
                System.out.print("Modificado!");
                // throw new NoDataException("No se realizo la inserci�n");
            }

            return;
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new GlobalException("Llave duplicada");
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

    public LinkedList<Profesor> buscarPorCedula(String cedula)
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
        Profesor profesor = null;
        CallableStatement pstmt = null;
        try {
            this.conexion.setAutoCommit(false);
            pstmt = this.conexion.prepareCall(BUSCAR);
            pstmt.registerOutParameter(1, Types.OTHER);
            pstmt.setString(2, cedula);
            pstmt.execute();

            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                profesor =new Profesor(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getInt("telefono")
                );
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

    public LinkedList<Profesor> listarProfesores()
            throws GlobalException, NoDataException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        LinkedList<Profesor> coleccion = new LinkedList();
        Profesor profesor = null;
        CallableStatement pstmt = null;
        try {

            this.conexion.setAutoCommit(false);

            pstmt = this.conexion.prepareCall(LISTAR);
            pstmt.setFetchSize(50);
            pstmt.registerOutParameter(1, Types.OTHER);

            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);

            ////
            while (rs.next()) {

                profesor = new Profesor(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getInt("telefono")
                );
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

    public void eliminarProfesor(String cedula)
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
            pstmt = this.conexion.prepareCall(ELIMINAR);
            pstmt.setString(1, cedula);

            boolean resultado = pstmt.execute();
            if (resultado == true) {
                System.out.print("Eliminado!");
            }

            return;
        } catch (SQLException e) {
            e.printStackTrace();
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

    public static ServicioProfesor getInstancia() {
        return INSTANCIA == null ? (INSTANCIA = new ServicioProfesor()) : INSTANCIA;
    }

    private static ServicioProfesor INSTANCIA = null;
}
