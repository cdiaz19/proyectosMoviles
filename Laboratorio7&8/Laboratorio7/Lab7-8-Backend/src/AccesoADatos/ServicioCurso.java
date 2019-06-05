/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;

import LogicaDeNegocio.Carrera;
import LogicaDeNegocio.Curso;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

/**
 *
 * @author alejandro
 */
public class ServicioCurso extends Servicio {
    private static final String INSERTAR = "{call insertarcurso(?,?,?,?,?)}";
    private static final String MODIFICAR = "{call actualizarcurso(?,?,?,?,?)}";
    private static final String BUSCAR = "{?=call buscarcurso(?)}";
    private static final String LISTAR = "{?=call listarcursos()}";
    private static final String ELIMINAR = "{call eliminarcurso(?)}";
    
    
    public void insertarCurso(Curso curso, Carrera carrera)
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
            pstmt.setString(1, curso.getCodigo());
            pstmt.setString(2, curso.getNombre());
            pstmt.setInt(3, curso.getCreditos());
            pstmt.setInt(4, curso.getHoras());
            pstmt.setString(5, carrera.getCodigo());
            
   
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
    
    
    public void modificarCurso(Curso curso,Carrera carrera)
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
            pstmt.setString(1, curso.getCodigo());
            pstmt.setString(2, curso.getNombre());
            pstmt.setInt(3, curso.getCreditos());
            pstmt.setInt(4, curso.getHoras());
            pstmt.setString(5, carrera.getCodigo());
            

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
    
    
    public LinkedList<Curso> buscarPorCodigo(String codigo)
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
        Curso curso = null;
        Carrera carrera= null;
        CallableStatement pstmt = null;
        try {
            this.conexion.setAutoCommit(false);
            pstmt = this.conexion.prepareCall(BUSCAR);
            pstmt.registerOutParameter(1, Types.OTHER);
            pstmt.setString(2, codigo);
            pstmt.execute();

            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                carrera=new Carrera(rs.getString("codigo_carrera"),rs.getString("nombre_carrera"),null);
                curso = new Curso(
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getInt("creditos"),
                        rs.getInt("horas"),
                        carrera
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
        Curso curso = null;
        Carrera carrera= null;
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
             carrera=new Carrera(rs.getString("codigo_carrera"),rs.getString("nombre_carrera"),null);
                curso = new Curso(
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getInt("creditos"),
                        rs.getInt("horas"),
                        carrera
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
    
    
    public void eliminarCurso(String codigo)
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
            pstmt.setString(1, codigo);

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
    
    
    
    public static ServicioCurso getInstancia() {
        return INSTANCIA == null ? (INSTANCIA = new ServicioCurso()) : INSTANCIA;
    }

    private static ServicioCurso INSTANCIA = null;
    
    
    
}
