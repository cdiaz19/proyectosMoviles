/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;

import LogicaDeNegocio.Ciclo;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

/**
 *
 * @author alejandro
 */
public class ServicioCiclo extends Servicio {
    private static final String INSERTAR = "{call insertarCiclo(?,?,?,?,?)}";
    private static final String MODIFICAR = "{call actualizarciclo(?,?,?,?,?)}";
    private static final String BUSCAR = "{?=call buscarciclo(?)}";
    private static final String LISTAR = "{?=call listarciclos()}";
    private static final String ELIMINAR = "{call eliminarciclo(?)}";
    
    
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
            pstmt = this.conexion.prepareCall(INSERTAR);
            pstmt.setString(1, ciclo.getCodigo());
            pstmt.setInt(2, ciclo.getAnno());
            pstmt.setInt(3, ciclo.getNumero());
            pstmt.setString(4, ciclo.getFechaIncio());
            pstmt.setString(5, ciclo.getFechaFinal());
            
   
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
    
    
    public void modificarCiclo(Ciclo ciclo)
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
            pstmt.setString(1, ciclo.getCodigo());
            pstmt.setInt(2, ciclo.getAnno());
            pstmt.setInt(3, ciclo.getNumero());
            pstmt.setString(4, ciclo.getFechaIncio());
            pstmt.setString(5, ciclo.getFechaFinal());
            
           
            
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
    
    public LinkedList<Ciclo> buscarPorCodigo(String codigo)
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
        Ciclo ciclo= null;
        CallableStatement pstmt = null;
        try {
            this.conexion.setAutoCommit(false);
            pstmt = this.conexion.prepareCall(BUSCAR);
            pstmt.registerOutParameter(1, Types.OTHER);
            pstmt.setString(2, codigo);
            pstmt.execute();
            
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                ciclo = new Ciclo(
                      
                            rs.getString("codigo"),
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
            
            this.conexion.setAutoCommit(false);
            
            pstmt = this.conexion.prepareCall(LISTAR);
            pstmt.setFetchSize(50);
            pstmt.registerOutParameter(1, Types.OTHER);            
            
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            
            
            ////
           
            
            while (rs.next()) {
                
                ciclo = new Ciclo(
                            rs.getString("codigo"),
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
    
    public void eliminarCiclo(String codigo)
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
    
    public static ServicioCiclo getInstancia() {
        return INSTANCIA == null ? (INSTANCIA = new ServicioCiclo()) : INSTANCIA;
    }

    private static ServicioCiclo INSTANCIA = null;
    
    
}
