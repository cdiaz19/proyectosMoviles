/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import AccesoADatos.Servicio;
import AccesoADatos.ServicioCategoria;
import AccesoADatos.ServicioCliente;
import AccesoADatos.ServicioVideojuego;
import LogicaDeNegocio.Categoria;
import LogicaDeNegocio.Cliente;
import LogicaDeNegocio.Usuario;
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
@WebServlet(urlPatterns = {"/ConnTest","/listarVideojuegos","/insertarVid",
    "/editVid","/deleteVid","/listarCategorias","/insertarCategoria",
    "/editCategoria","/deleteCategoria","/listarClientes","/insertarCliente",
    "/modificarCliente","/eliminaCliente"})
public class ConnTest extends HttpServlet {
    private ServicioVideojuego daoVideojuego;
    private ServicioCategoria servicioCategoria;
    private ServicioCliente servicioCliente;
    private String clientesJsonString;
    private String videojuegosJsonString;
    private String categoriasJsonString;

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
                this.doReadAllVid(request, response);
                break;
        case "/listarCategorias":
                this.doReadAllCat(request, response);
                break;
            
        case "/editVid":
                this.doEditVid(request, response);
                break;    
        case "/insertarCategoria":
            this.doInsertCat(request,response);
            break;
        case "/deleteCategoria":
                this.doDeleteCat(request, response);
                break;    
            
        case "/editCategoria":
                this.doEditCat(request, response);
                break;    
            
        case "/deleteVid":
            this.doDeleteVid(request,response);
            break;
       case "/insertarVid":
                this.doCreate(request, response);
                break;
        case "/listarClientes":
                this.doReadAllClientes(request, response);
                break;
        case "/insertarCliente":
                this.doInsertCliente(request, response);
                break;
        case "/modificarCliente":
                this.doModificaCliente(request, response);
                break;
        case "/eliminaCliente":
            this.doDeleteCliente(request,response);
            break;
                
        
        }
        
    }
    
    
    
    protected void doReadAllClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            servicioCliente = ServicioCliente.getInstancia();
            
            response.setContentType("application/json;charset=UTF-8");
            
            //ServletOutputStream out = resp.getOutputStream();
            LinkedList<Cliente> c1 = servicioCliente.listarClientes();
            
            
            Gson gson = new Gson();
            clientesJsonString = gson.toJson(c1);
            PrintWriter out = response.getWriter();
            try {
                out.println(clientesJsonString);
            } finally {
                out.close();
            }
        }   catch (GlobalException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void doModificaCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String cedula = request.getParameter("cedula");
            String email= request.getParameter("email");
            String password=request.getParameter("password");
            String nombre=request.getParameter("nombre");
            int telefono =Integer.parseInt(request.getParameter("telefono"));
            
            Usuario usuario=new Usuario(cedula,email,password,"cliente");
            Cliente cliente= new Cliente(nombre,telefono,usuario);
            servicioCliente=ServicioCliente.getInstancia();
            
            
            servicioCliente.modificarCliente(cliente, usuario);
            
            
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
    
    protected void doInsertCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String cedula = request.getParameter("cedula");
            String email= request.getParameter("email");
            String password=request.getParameter("password");
            String nombre=request.getParameter("nombre");
            int telefono =Integer.parseInt(request.getParameter("telefono"));
            
            Usuario usuario=new Usuario(cedula,email,password,"cliente");
            Cliente cliente= new Cliente(nombre,telefono,usuario);
            servicioCliente=ServicioCliente.getInstancia();
            
            
            servicioCliente.insertarCliente(cliente, usuario);
            
            
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
    
    protected void doDeleteCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String cedula = request.getParameter("cedula");
            
           
           
            servicioCliente=ServicioCliente.getInstancia();
            servicioCliente.eliminarCliente(cedula);
            
            PrintWriter out = response.getWriter();
            try {
                out.println("eliminado");
            } finally {
                out.close();
            }
            
        }   catch (GlobalException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    protected void doDeleteVid(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String codigo = request.getParameter("codigoJuego");
            
           
           
            daoVideojuego = ServicioVideojuego.getInstancia();
            daoVideojuego.eliminarVideojuego(codigo);
            
            PrintWriter out = response.getWriter();
            try {
                out.println("eliminado");
            } finally {
                out.close();
            }
            
        }   catch (GlobalException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    protected void doEditCat(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String codigo = request.getParameter("codigo");
            String nombre= request.getParameter("nombre");
            
            
            Categoria categoria= new Categoria(codigo,nombre);
           
           
            servicioCategoria = ServicioCategoria.getInstancia();
            servicioCategoria.modificarCategoria(categoria);
            
            PrintWriter out = response.getWriter();
            try {
                out.println("editado");
            } finally {
                out.close();
            }
            
        }   catch (GlobalException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    protected void doDeleteCat(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String codigo = request.getParameter("codigo");
            
           
           
            servicioCategoria = ServicioCategoria.getInstancia();
            servicioCategoria.eliminarCategoria(codigo);
            
            PrintWriter out = response.getWriter();
            try {
                out.println("eliminado");
            } finally {
                out.close();
            }
            
        }   catch (GlobalException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    protected void doEditVid(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String codigoJuego = request.getParameter("codigoJuego");
            String nombre= request.getParameter("nombre");
            int cantidad= Integer.parseInt(request.getParameter("cantidad"));
            int precio =Integer.parseInt(request.getParameter("precio"));
            String empresa= request.getParameter("empresa");
            String categoriaid=request.getParameter("categoria_id");
            String nombrecategoria=request.getParameter("nombre_categoria");
            
            Categoria categoria= new Categoria(categoriaid,nombrecategoria);
            Videojuego videojuego= new Videojuego(codigoJuego,nombre,cantidad,precio,empresa,categoria);
           
            daoVideojuego = ServicioVideojuego.getInstancia();
            daoVideojuego.modificarVideojuego(categoria, videojuego);
            
            PrintWriter out = response.getWriter();
            try {
                out.println("editado");
            } finally {
                out.close();
            }
            
        }   catch (GlobalException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    protected void doInsertCat(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String codigo = request.getParameter("codigo");
            String nombre= request.getParameter("nombre");
            
            
            Categoria categoria= new Categoria(codigo,nombre);
           
           
            servicioCategoria = ServicioCategoria.getInstancia();
            servicioCategoria.insertarCategoria(categoria);
            
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
    
    
    protected void doReadAllCat(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            servicioCategoria = ServicioCategoria.getInstancia();
            
            response.setContentType("application/json;charset=UTF-8");
            
            //ServletOutputStream out = resp.getOutputStream();
            LinkedList<Categoria> c1 = servicioCategoria.listarCategorias();
            
            
            Gson gson = new Gson();
            categoriasJsonString = gson.toJson(c1);
            PrintWriter out = response.getWriter();
            try {
                out.println(categoriasJsonString);
            } finally {
                out.close();
            }
        }   catch (GlobalException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void doReadAllVid(HttpServletRequest request, HttpServletResponse response)
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
            String empresa= request.getParameter("empresa");
            String categoriaid=request.getParameter("categoria_id");
            String nombrecategoria=request.getParameter("nombre_categoria");
            
            Categoria categoria= new Categoria(categoriaid,nombrecategoria);
            Videojuego videojuego= new Videojuego(codigoJuego,nombre,cantidad,precio,empresa,categoria);
           
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