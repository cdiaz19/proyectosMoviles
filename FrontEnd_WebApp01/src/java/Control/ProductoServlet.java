/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import AccesoBD.DAOProducto;
import AccesoBD.GlobalException;
import AccesoBD.NoDataException;
import ModeloNegocio.Modelo;
import ModeloNegocio.Producto;
import ModeloNegocio.Tipo;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
public class ProductoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String json;
            Producto p = new Producto();

            //Se crea el objeto de la logica de negocio
            DAOProducto pBL = new DAOProducto();

            String accion = request.getParameter("accion");
            switch (accion) {
                case "consultarProducto":
                    LinkedList<Producto> lista = pBL.listar();
                    System.out.print(lista);
                    json = new Gson().toJson(lista);
                    out.print(json);
                    break;
                case "agregarProducto":   
                    p.setCodigo(request.getParameter("codigo"));
                    p.setNombre(request.getParameter("nombre"));
                    p.setImportado(Integer.parseInt(request.getParameter("importado")));
                    p.setPrecio(Float.parseFloat(request.getParameter("precio")));
                    p.setNombre(request.getParameter("tipo"));
                    pBL.insertar(p);
                   
                    break;
                case "eliminarProducto":
                    break;
                case "consultarProductoByNombre":
                    p.setNombre(request.getParameter("nombre"));
                    LinkedList<Producto> lista2 = pBL.buscarNombres(p.getNombre());
                    json = new Gson().toJson(lista2);
                    out.print(json);
                    break;
                case "consultarProductoByTipo":
                    p.setTipo(new Tipo(request.getParameter("tipo")));
                    LinkedList<Producto> lista3 = pBL.buscarTipos(p.getTipo().getDescripcion());
                    json = new Gson().toJson(lista3);
                    out.print(json);
                    break;
                case "modificarProducto":
                    break;
                default:
                    out.print("E~No se indico la acción que se desea realizare");
                    break;
            }

        } catch (NumberFormatException | GlobalException | NoDataException e) {
            out.print("E~" + e.getMessage());
        }
    }

    DAOProducto domainModel;
    Modelo model;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
