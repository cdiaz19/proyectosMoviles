package Control;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import AccesoADatos.ServicioCurso;
import LogicaDeNegocio.Curso;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServletCurso extends HttpServlet {

    private static final String UserRecord = null;
    private static String INSERTA = "/InsertarCurso.jsp";
    private static String EDITA = "/ModificarCurso.jsp";
    private static String LISTARCURSOS = "/ListarCursos.jsp";
    private ServicioCurso dao;

    public ServletCurso() {
        super();
        dao = ServicioCurso.getInstancia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String redirect = "";

        String uId = request.getParameter("id");

        String action = request.getParameter("action");

        if (uId != null && action.equalsIgnoreCase("insert")) {

            Curso curso = new Curso(
                    request.getParameter("id"), 
                    request.getParameter("codigo"), 
                    request.getParameter("nombre"), 
                    Integer.parseInt(request.getParameter("creditos")),
                    Integer.parseInt(request.getParameter("horasSemanales")));
            try {
                dao.insertarCurso(curso);
            } catch (GlobalException | NoDataException ex) {
                Logger.getLogger(ServletCurso.class.getName()).log(Level.SEVERE, null, ex);
            }
            redirect = LISTARCURSOS;

        } else if (action.equalsIgnoreCase("delete")) {
            String idAEliminar = request.getParameter("ID");
            try {
                dao.eliminar(idAEliminar);
            } catch (GlobalException | NoDataException ex) {
                Logger.getLogger(ServletCurso.class.getName()).log(Level.SEVERE, null, ex);
            }
            redirect = LISTARCURSOS;

        } else if (action.equalsIgnoreCase("editForm")) {
            request.setAttribute("id", request.getParameter("ID"));
            redirect = EDITA;
        } else if (action.equalsIgnoreCase("edit")) {
            Curso curso = new Curso(
                    request.getParameter("id"), 
                    request.getParameter("codigo"), 
                    request.getParameter("nombre"), 
                    Integer.parseInt(request.getParameter("creditos")),
                    Integer.parseInt(request.getParameter("horasSemanales")));
            try {
                dao.modificarCurso(curso);
            } catch (GlobalException | NoDataException ex) {
                Logger.getLogger(ServletCurso.class.getName()).log(Level.SEVERE, null, ex);
            }

            redirect = LISTARCURSOS;

        } else {
            redirect = INSERTA;
        }

        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
