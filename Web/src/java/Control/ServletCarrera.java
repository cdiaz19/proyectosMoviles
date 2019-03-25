package Control;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LogicaDeNegocio.Carrera;
import AccesoADatos.ServicioCarrera;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ServletCarrera extends HttpServlet {
	private static final String UserRecord = null;
	private static String INSERT = "/InsertarCarrera.jsp";
	private static String edit = "/ModificarCarrera.jsp";
	private static String liste = "/ListarCarreras.jsp";
	private ServicioCarrera dao;
		public ServletCarrera() {
			super();
			dao = ServicioCarrera.getInstancia();	
		}
		protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
			
			String redirect = "";
			
			String uId = request.getParameter("id");
			
			String action = request.getParameter("action");
				
			if(  uId != null && action.equalsIgnoreCase("insert")){
			
				
				
                                Carrera carrera=new Carrera(request.getParameter("id"),request.getParameter("codigo"),request.getParameter("nombre"),request.getParameter("titulo"));
                            try {
                                
                                dao.insertarCarrera(carrera);
                            } catch (GlobalException ex) {
                                Logger.getLogger(ServletCarrera.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (NoDataException ex) {
                                Logger.getLogger(ServletCarrera.class.getName()).log(Level.SEVERE, null, ex);
                            }
			  redirect = liste;
				
			}
                        
			else if(action.equalsIgnoreCase("delete")){
				String idAEliminar = request.getParameter("ID");
                            try {
                                dao.eliminar(idAEliminar);
                            } catch (GlobalException ex) {
                                Logger.getLogger(ServletCarrera.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (NoDataException ex) {
                                Logger.getLogger(ServletCarrera.class.getName()).log(Level.SEVERE, null, ex);
                            }
				redirect = liste;
				
			}
			
			else if(action.equalsIgnoreCase("editForm")){
                                request.setAttribute("id", request.getParameter("ID"));
                                redirect = edit;
			}
			
			else if(action.equalsIgnoreCase("edit")){
				
                                 Carrera carrera=new Carrera(request.getParameter("id"),request.getParameter("codigo"),request.getParameter("nombre"),request.getParameter("titulo"));
                                
                            try {
                                dao.modificarCarrera(carrera);
                            } catch (GlobalException ex) {
                                Logger.getLogger(ServletCarrera.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (NoDataException ex) {
                                Logger.getLogger(ServletCarrera.class.getName()).log(Level.SEVERE, null, ex);
                            }
					
					redirect = liste;
					
			}
			
			else{
				redirect = INSERT;
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(redirect);
							  rd.forward(request, response);
		}
		protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
			doGet(request,response);
		}
}

























