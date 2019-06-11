/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;

import LogicaDeNegocio.Client;
import LogicaDeNegocio.Order;
import LogicaDeNegocio.User;
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
public class ServicioOrder extends Servicio {
    private static final String INSERTAR = "{call insertarpedido(?,?,?,?)}";
    private static final String MODIFICAR = "{call actualizarpedido(?,?,?,?,?)}";
    private static final String LISTAR = "{?=call listarpedidos()}";
    private static final String ELIMINAR = "{call eliminarpedido(?)}";
    
    
    public void insertarPedido(Order order, Client cliente,Videojuego videojuego)
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
            pstmt.setString(1, order.getFecha());
            pstmt.setInt(2, order.getCantidad());
            pstmt.setString(3, cliente.getUser().getCedula());
            pstmt.setString(4, videojuego.getCodigoJuego());
            
            

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
    
    
     public void modificarPedido(Order order, Client cliente,Videojuego videojuego)
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
             pstmt.setInt(1, order.getId());
            pstmt.setString(2, order.getFecha());
            pstmt.setInt(3, order.getCantidad());
            pstmt.setString(4, cliente.getUser().getCedula());
            pstmt.setString(5, videojuego.getCodigoJuego());
            
            
           
            
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
     
     public LinkedList<Order> listarPedidos()
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
        User usuario = null;
        Client cliente= null;
        Videojuego videojuego=null;
        Order order=null;
        CallableStatement pstmt = null;
        try {
            this.conexion.setAutoCommit(false);
            pstmt = this.conexion.prepareCall(LISTAR);
            pstmt.registerOutParameter(1, Types.OTHER);
            pstmt.execute();
            
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                usuario=new User(rs.getString("cliente_id"),null,null,null);
                cliente= new Client(null,0,usuario);
                videojuego= new Videojuego(rs.getString("videojuego_id"),null,0,0,null,null);
                
                order = new Order(
                            rs.getInt("id"),
                            rs.getString("fecha"),
                            rs.getInt("cantidad"),
                            rs.getInt("total"),
                            videojuego,
                            cliente
                            
                            
                            );
                coleccion.add(order);
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
     
     public void eliminarpedido(int codigopedido)
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
            pstmt.setInt(1, codigopedido);
           
            
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
    
    
    public static ServicioOrder getInstancia() {
        return INSTANCIA == null ? (INSTANCIA = new ServicioOrder()) : INSTANCIA;
    }

    private static ServicioOrder INSTANCIA = null;
}
