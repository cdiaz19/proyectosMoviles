/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;

import LogicaDeNegocio.Categoria;
import LogicaDeNegocio.Videojuego;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

/**
 *
 * @author alejandro
 */
public class ServicioVideojuego extends Servicio {
    
    private static final String INSERTAR = "{call insertarvideojuego(?,?,?,?,?,?,?,?)}";
    private static final String MODIFICAR = "{call actualizarvideojuego(?,?,?,?,?,?,?,?)}";
    private static final String BUSCARPORCODIGO = "{?=call buscarvideojuego(?)}";
    private static final String BUSCARPORCATEGORIA = "{?=call buscarvideojuegoporcategoria(?)}";
    private static final String LISTAR = "{?=call listarvideojuegos()}";
    private static final String ELIMINAR = "{call eliminarvideojuego(?)}";
    
    public void insertarVideojuego(Categoria categoria,Videojuego videojuego)
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
            pstmt.setString(1, videojuego.getCodigoJuego());
            pstmt.setString(2, videojuego.getNombre());
            pstmt.setInt(3, videojuego.getCantidad());
            pstmt.setInt(4, videojuego.getCantidad());
            pstmt.setString(5, videojuego.getRentor());
            pstmt.setString(6, videojuego.getPlazo());
            pstmt.setString(7, videojuego.getEmpresa());
            pstmt.setString(8, categoria.getCodigo());
            
            

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
    
    public void modificarVideojuego(Categoria categoria,Videojuego videojuego)
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
            pstmt.setString(1, videojuego.getCodigoJuego());
            pstmt.setString(2, videojuego.getNombre());
            pstmt.setInt(3, videojuego.getCantidad());
            pstmt.setInt(4, videojuego.getCantidad());
            pstmt.setString(5, videojuego.getRentor());
            pstmt.setString(6, videojuego.getPlazo());
            pstmt.setString(7, videojuego.getEmpresa());
            pstmt.setString(8, categoria.getCodigo());
            
            
           
            
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
    
    public LinkedList<Videojuego> buscarPorCodigo(String codigo_juego)
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
        Videojuego videojuego = null;
        Categoria categoria= null;
        CallableStatement pstmt = null;
        try {
            this.conexion.setAutoCommit(false);
            pstmt = this.conexion.prepareCall(BUSCARPORCODIGO);
            pstmt.registerOutParameter(1, Types.OTHER);
            pstmt.setString(2, codigo_juego);
            pstmt.execute();
            
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                categoria=new Categoria(rs.getString("categoria_id"),rs.getString("nombre_categoria"));
                videojuego = new Videojuego(
                      
                            rs.getString("codigo_juego"),
                            rs.getString("nombre"),
                            rs.getInt("cantidad"),
                            rs.getInt("precio"),
                            rs.getString("rentor"),
                            rs.getString("plazo"),
                            rs.getString("empresa"),
                            categoria
                            
                            
                            );
                coleccion.add(videojuego);
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
    
    public LinkedList<Videojuego> listarVideojueos()
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
        Videojuego videojuego = null;
        Categoria categoria= null;
        CallableStatement pstmt = null;
        try {
            this.conexion.setAutoCommit(false);
            pstmt = this.conexion.prepareCall(LISTAR);
            pstmt.registerOutParameter(1, Types.OTHER);
            pstmt.execute();
            
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                categoria=new Categoria(rs.getString("categoria_id"),rs.getString("nombre_categoria"));
                videojuego = new Videojuego(
                      
                            rs.getString("codigo_juego"),
                            rs.getString("nombre"),
                            rs.getInt("cantidad"),
                            rs.getInt("precio"),
                            rs.getString("rentor"),
                            rs.getString("plazo"),
                            rs.getString("empresa"),
                            categoria
                            
                            
                            );
                coleccion.add(videojuego);
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
    
    public LinkedList<Videojuego> buscarPorCategoria(String categoria_juego)
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
        Videojuego videojuego = null;
        Categoria categoria= null;
        CallableStatement pstmt = null;
        try {
            this.conexion.setAutoCommit(false);
            pstmt = this.conexion.prepareCall(BUSCARPORCATEGORIA);
            pstmt.registerOutParameter(1, Types.OTHER);
            pstmt.setString(2, categoria_juego);
            pstmt.execute();
            
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                categoria=new Categoria(rs.getString("categoria_id"),rs.getString("nombre_categoria"));
                videojuego = new Videojuego(
                      
                            rs.getString("codigo_juego"),
                            rs.getString("nombre"),
                            rs.getInt("cantidad"),
                            rs.getInt("precio"),
                            rs.getString("rentor"),
                            rs.getString("plazo"),
                            rs.getString("empresa"),
                            categoria
                            
                            
                            );
                coleccion.add(videojuego);
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
    
    public void eliminarVideojuego(String codigo_juego)
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
            pstmt.setString(1, codigo_juego);
           
            
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
    
    public static ServicioVideojuego getInstancia() {
        return INSTANCIA == null ? (INSTANCIA = new ServicioVideojuego()) : INSTANCIA;
    }

    private static ServicioVideojuego INSTANCIA = null;
    
}
