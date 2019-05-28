/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import AccesoADatos.ServicioVideojuego;
import LogicaDeNegocio.Categoria;
import LogicaDeNegocio.Videojuego;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alejandro
 */
@WebServlet(urlPatterns = {"/ConnTest","/listarVideojuegos","/mierda"})
public class ConnTest extends HttpServlet {
    private ServicioVideojuego daoVideojuego;
    private String videojuegosJsonString;

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
        String servletPath=request.getServletPath();
        switch(servletPath){
        case "/listarVideojuegos":
                this.doReadAll(request, response);
                break;
            case "/mierda":
                this.doCreate(request, response);
                break;
        
        }
        
    }
    
    protected void doReadAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            daoVideojuego = ServicioVideojuego.getInstancia();
            
            response.setContentType("application/json;charset=UTF-8");
            
            //ServletOutputStream out = resp.getOutputStream();
            LinkedList<Videojuego> c1 = daoVideojuego.listarVideojueos();
            
            
            Gson gson = new Gson();
            videojuegosJsonString = gson.toJson(c1);
            PrintWriter out = response.getWriter();
            try {
                out.println(videojuegosJsonString);
            } finally {
                out.close();
            }
        }   catch (GlobalException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
    
    protected void doCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String codigoJuego = request.getParameter("codigoJuego");
            String nombre= request.getParameter("nombre");
            int cantidad= Integer.parseInt(request.getParameter("cantidad"));
            int precio =Integer.parseInt(request.getParameter("precio"));
            String rentor= request.getParameter("rentor");
            String plazo= request.getParameter("plazo");
            String empresa= request.getParameter("empresa");
            String categoriaid=request.getParameter("categoria_id");
            String nombrecategoria=request.getParameter("nombre_categoria");
            
            Categoria categoria= new Categoria(categoriaid,nombrecategoria);
            Videojuego videojuego= new Videojuego(codigoJuego,nombre,cantidad,precio,rentor,plazo,empresa,categoria);
            
            
            daoVideojuego = ServicioVideojuego.getInstancia();
            daoVideojuego.insertarVideojuego(categoria, videojuego);
            
            PrintWriter out = response.getWriter();
            try {
                out.println("insertado");
            } finally {
                out.close();
            }
            
        }   catch (GlobalException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }

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
