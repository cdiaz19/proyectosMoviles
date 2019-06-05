/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import AccesoADatos.ServicioCarrera;
import AccesoADatos.ServicioCiclo;
import AccesoADatos.ServicioCurso;
import AccesoADatos.ServicioProfesor;
import LogicaDeNegocio.Carrera;
import LogicaDeNegocio.Ciclo;
import LogicaDeNegocio.Curso;
import LogicaDeNegocio.Profesor;
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
@WebServlet(urlPatterns = {"/ConnTest","/listarCiclos",
    "/insertarCiclo","/modificarCiclo","/eliminarCiclo","/listarCarreras",
    "/insertarCarrera","/modificarCarrera","/eliminarCarrera","/listarCursos",
    "/insertarCurso","/modificarCurso",
    "/eliminarCurso","/listarProfesores",
    "/insertarProfesor","/modificarProfesor","/eliminarProfesor"})
public class ConnTest extends HttpServlet {
    private ServicioCiclo servicioCiclo;
    private ServicioCarrera servicioCarrera;
    private ServicioCurso servicioCurso;
    private ServicioProfesor servicioProfesor;
    private String cursosJsonString;
    private String carrerasJsonString;
     private String cicloJsonString;
     private String profesoresJsonString;
    

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
        String servletPath=request.getServletPath();
        switch(servletPath){
        case "/listarCiclos":
                this.doReadAllCiclo(request, response);
                break;
        case "/insertarCiclo":
                this.doInsertCiclo(request, response);
                break;
            
        case "/modificarCiclo":
                this.doModificaCiclo(request, response);
                break;    
        case "/eliminarCiclo":
            this.doEliminarCiclo(request,response);
            break;
        case "/listarCarreras":
             this.doReadAllCarreras(request, response);
                break;  
            
        case "/insertarCarrera":
               this.doInsertCarrera(request, response);
                break;   
            
        case "/modificarCarrera":
           this.doModificaCarrera(request, response);
                break;    
            case "/eliminarCarrera":
             this.doEliminarCarrera(request, response);
                break;
                
        case "/listarCursos":
                this.doReadAllCursos(request, response);
                break;
            
        case "/insertarCurso":
               this.doInsertCurso(request, response);
                break;
            
            case "/modificarCurso":
           this.doModificaCurso(request, response);
                break;
                
            case "/eliminarCurso":
             this.doEliminarCurso(request, response);
                break;
                
            case "/listarProfesores":
                this.doReadAllProfesores(request, response);
                break;
                
             case "/insertarProfesor":
               this.doInsertProfesor(request, response);
                break;  
                 
                 
              case "/modificarProfesor":
           this.doModificaProfesor(request, response);
                break;
                  
                  
           case "/eliminarProfesor":
             this.doEliminarProfesor(request, response);
                break;       
        
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
    
    
    protected void doReadAllProfesores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            servicioProfesor = ServicioProfesor.getInstancia();
            
            response.setContentType("application/json;charset=UTF-8");
            
            //ServletOutputStream out = resp.getOutputStream();
            LinkedList<Profesor> c1 = servicioProfesor.listarProfesores();
            
            
            Gson gson = new Gson();
            profesoresJsonString = gson.toJson(c1);
            PrintWriter out = response.getWriter();
            try {
                out.println(profesoresJsonString);
            } finally {
                out.close();
            }
        }   catch (GlobalException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    protected void doInsertProfesor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String cedula = request.getParameter("cedula");
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            int telefono = Integer.parseInt(request.getParameter("telefono"));
        
            
            
            
            
            
            Profesor profesor= new Profesor(cedula,nombre,email,telefono);
            
            
            servicioProfesor = ServicioProfesor.getInstancia();
            servicioProfesor.insertarProfesor(profesor);
           
            
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
    
    
    protected void doModificaProfesor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String cedula = request.getParameter("cedula");
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            int telefono = Integer.parseInt(request.getParameter("telefono"));
        
            
            
            
            
            
            Profesor profesor= new Profesor(cedula,nombre,email,telefono);
            
            
            servicioProfesor = ServicioProfesor.getInstancia();
            servicioProfesor.modificarProfesor(profesor);
           
            
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
    
    protected void doEliminarProfesor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String cedula = request.getParameter("cedula");
     
            
            
            servicioProfesor = ServicioProfesor.getInstancia();
            servicioProfesor.eliminarProfesor(cedula);
           
            
            PrintWriter out = response.getWriter();
            try {
                out.println("cedula");
            } finally {
                out.close();
            }
            
        }   catch (GlobalException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    protected void doReadAllCursos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            servicioCurso = ServicioCurso.getInstancia();
            
            response.setContentType("application/json;charset=UTF-8");
            
            //ServletOutputStream out = resp.getOutputStream();
            LinkedList<Curso> c1 = servicioCurso.listarCursos();
            
            
            Gson gson = new Gson();
            cursosJsonString = gson.toJson(c1);
            PrintWriter out = response.getWriter();
            try {
                out.println(cursosJsonString);
            } finally {
                out.close();
            }
        }   catch (GlobalException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void doReadAllCarreras(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            servicioCarrera = ServicioCarrera.getInstancia();
            
            response.setContentType("application/json;charset=UTF-8");
            
            //ServletOutputStream out = resp.getOutputStream();
            LinkedList<Carrera> c1 = servicioCarrera.listarCarreras();
            
            
            Gson gson = new Gson();
            carrerasJsonString = gson.toJson(c1);
            PrintWriter out = response.getWriter();
            try {
                out.println(carrerasJsonString);
            } finally {
                out.close();
            }
        }   catch (GlobalException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    protected void doInsertCurso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String codigo = request.getParameter("codigo");
            String nombre = request.getParameter("nombre");
            int creditos = Integer.parseInt(request.getParameter("creditos"));
            int horas = Integer.parseInt(request.getParameter("horas"));
            String codCar= request.getParameter("codCar");
            String nomCar=request.getParameter("nomCar");
            
            
            
            
            
            Carrera carrera= new Carrera(codCar,nomCar,null);
            Curso curso= new Curso(codigo,nombre,creditos,horas,carrera);
            
            servicioCurso=ServicioCurso.getInstancia();
            servicioCurso.insertarCurso(curso,carrera);
           
            
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
    
    protected void doInsertCarrera(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String codigo = request.getParameter("codigo");
            String nombre = request.getParameter("nombre");
            String titulo = request.getParameter("titulo");
            
            
            
            
            Carrera carrera= new Carrera(codigo,nombre,titulo);
            servicioCarrera=ServicioCarrera.getInstancia();
            servicioCarrera.insertarCarrera(carrera);
           
            
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
    
    
    protected void doModificaCurso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
           String codigo = request.getParameter("codigo");
            String nombre = request.getParameter("nombre");
            int creditos = Integer.parseInt(request.getParameter("creditos"));
            int horas = Integer.parseInt(request.getParameter("horas"));
            String codCar= request.getParameter("codCar");
            String nomCar=request.getParameter("nomCar");
            
            
            
            
            
            Carrera carrera= new Carrera(codCar,nomCar,null);
            Curso curso= new Curso(codigo,nombre,creditos,horas,carrera);
            
            servicioCurso=ServicioCurso.getInstancia();
            servicioCurso.modificarCurso(curso, carrera);
           
            
            PrintWriter out = response.getWriter();
            try {
                out.println("modificado");
            } finally {
                out.close();
            }
            
        }   catch (GlobalException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     protected void doModificaCarrera(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String codigo = request.getParameter("codigo");
            String nombre = request.getParameter("nombre");
            String titulo = request.getParameter("titulo");
            
            
            
            
            Carrera carrera= new Carrera(codigo,nombre,titulo);
            servicioCarrera=ServicioCarrera.getInstancia();
            servicioCarrera.modificarCarrera(carrera);
           
            
            PrintWriter out = response.getWriter();
            try {
                out.println("modificado");
            } finally {
                out.close();
            }
            
        }   catch (GlobalException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     
     protected void doEliminarCurso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String codigo = request.getParameter("codigo");
            
            
            
            
           
            servicioCurso=ServicioCurso.getInstancia();
            servicioCurso.eliminarCurso(codigo);
           
            
            PrintWriter out = response.getWriter();
            try {
                out.println("carrera eliminada");
            } finally {
                out.close();
            }
            
        }   catch (GlobalException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     
     protected void doEliminarCarrera(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String codigo = request.getParameter("codigo");
            
            
            
            
           
            servicioCarrera=ServicioCarrera.getInstancia();
            servicioCarrera.eliminarCarrera(codigo);
           
            
            PrintWriter out = response.getWriter();
            try {
                out.println("carrera eliminada");
            } finally {
                out.close();
            }
            
        }   catch (GlobalException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    
    protected void doInsertCiclo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String codigo = request.getParameter("codigo");
         
            int anno = Integer.parseInt(request.getParameter("anno"));
           
            int numero =Integer.parseInt(request.getParameter("numero"));
            
            String fechaInicio= request.getParameter("fechaInicio");
            
            String fechaFinal= request.getParameter("fechaFinal");
            
            
            
            
            Ciclo ciclo= new Ciclo(codigo,anno,numero,fechaInicio,fechaFinal);
            servicioCiclo=ServicioCiclo.getInstancia();
            servicioCiclo.insertarCiclo(ciclo);
           
            
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
    
    
    protected void doModificaCiclo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String codigo = request.getParameter("codigo");
           
            int anno = Integer.parseInt(request.getParameter("anno"));
            
            int numero =Integer.parseInt(request.getParameter("numero"));
            
            String fechaInicio= request.getParameter("fechaInicio");
            
            String fechaFinal= request.getParameter("fechaFinal");
            
            
            
            
            Ciclo ciclo= new Ciclo(codigo,anno,numero,fechaInicio,fechaFinal);
            servicioCiclo=ServicioCiclo.getInstancia();
            servicioCiclo.modificarCiclo(ciclo);
           
            
            PrintWriter out = response.getWriter();
            try {
                out.println("modificado");
            } finally {
                out.close();
            }
            
        }   catch (GlobalException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    protected void doEliminarCiclo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//        processRequest(request, response);
            response.setContentType("text/html");
            String codigo = request.getParameter("codigo");
            
            
            
            
           
            servicioCiclo=ServicioCiclo.getInstancia();
            servicioCiclo.eliminarCiclo(codigo);
           
            
            PrintWriter out = response.getWriter();
            try {
                out.println("ciclo eliminado");
            } finally {
                out.close();
            }
            
        }   catch (GlobalException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(ConnTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    
     protected void doReadAllCiclo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            servicioCiclo = ServicioCiclo.getInstancia();
            
            response.setContentType("application/json;charset=UTF-8");
            
            //ServletOutputStream out = resp.getOutputStream();
            LinkedList<Ciclo> c1 = servicioCiclo.listarCiclos();
            
            
            Gson gson = new Gson();
            cicloJsonString = gson.toJson(c1);
            PrintWriter out = response.getWriter();
            try {
                out.println(cicloJsonString);
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
