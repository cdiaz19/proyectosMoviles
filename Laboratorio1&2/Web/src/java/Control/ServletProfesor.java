package Control;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LogicaDeNegocio.Profesor;
import LogicaDeNegocio.Usuario;
import AccesoADatos.ServicioProfesor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServletProfesor extends HttpServlet {

    private static final String UserRecord = null;
    private static String INSERT = "/InsertarProfesor.jsp";
    private static String EDIT = "/ModificarProfesor.jsp";
    private static String LISTAPROFESORES = "/ListarProfesores.jsp";
    private ServicioProfesor dao;

    public ServletProfesor() {
        super();
        dao = ServicioProfesor.getInstancia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String redirect = "";

        String uId = request.getParameter("id");

        String action = request.getParameter("action");

        if (uId != null && action.equalsIgnoreCase("insert")) {
            String Tel = request.getParameter("telefono");
            int telefono = Integer.parseInt(Tel);
            Usuario usuario = new Usuario(request.getParameter("id"), request.getParameter("cedula"), request.getParameter("contrasena"));

            Profesor profesor = new Profesor(request.getParameter("id"), request.getParameter("nombre"), request.getParameter("correo"), telefono, usuario);
            try {
                dao.insertarProfesor(profesor, usuario);
            } catch (GlobalException | NoDataException ex) {
                Logger.getLogger(ServletCarrera.class.getName()).log(Level.SEVERE, null, ex);
            }
            redirect = LISTAPROFESORES;

        } else if (action.equalsIgnoreCase("delete")) {
            String idAEliminar = request.getParameter("ID");
            try {
                dao.eliminar(idAEliminar);
            } catch (GlobalException | NoDataException ex) {
                Logger.getLogger(ServletCarrera.class.getName()).log(Level.SEVERE, null, ex);
            }
            redirect = LISTAPROFESORES;

        } else if (action.equalsIgnoreCase("editForm")) {
            request.setAttribute("id", request.getParameter("ID"));
            redirect = EDIT;
        } else if (action.equalsIgnoreCase("edit")) {
            String Tel = request.getParameter("telefono");
            int telefono = Integer.parseInt(Tel);
            Usuario usuario = new Usuario(request.getParameter("id"), request.getParameter("cedula"), request.getParameter("contrasena"));

            Profesor profesor = new Profesor(request.getParameter("id"), request.getParameter("nombre"), request.getParameter("correo"), telefono, usuario);

            try {
                dao.modificarProfesor(profesor, usuario);
            } catch (GlobalException | NoDataException ex) {
                Logger.getLogger(ServletCarrera.class.getName()).log(Level.SEVERE, null, ex);
            }

            redirect = LISTAPROFESORES;

        } else {
            redirect = INSERT;
        }

        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
