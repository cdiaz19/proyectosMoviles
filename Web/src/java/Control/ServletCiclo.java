package Control;

import AccesoADatos.GlobalException;
import AccesoADatos.NoDataException;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LogicaDeNegocio.Ciclo;
import AccesoADatos.ServicioCiclo;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServletCiclo extends HttpServlet {

    private static final String UserRecord = null;
    private static String INSERT = "/InsertarCiclo.jsp";
    private static String EDIT = "/ModificarCiclo.jsp";
    private static String LISTARCICLOS = "/ListarCiclo.jsp";
    private ServicioCiclo dao;

    public ServletCiclo() {
        super();
        dao = ServicioCiclo.getInstancia();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String redirect = "";

        String uId = request.getParameter("id");

        String action = request.getParameter("action");

        if (uId != null && action.equalsIgnoreCase("insert")) {
            String an = request.getParameter("anno");
            String num = request.getParameter("numero");
            int anno = Integer.parseInt(an);
            int numero = Integer.parseInt(num);
            Ciclo ciclo = new Ciclo(request.getParameter("id"), anno, numero, request.getParameter("fechaInicio"), request.getParameter("fechaFin"));

            try {
                dao.insertarCiclo(ciclo);
            } catch (GlobalException | NoDataException ex) {
                Logger.getLogger(ServletCarrera.class.getName()).log(Level.SEVERE, null, ex);
            }
            redirect = LISTARCICLOS;

        } else if (action.equalsIgnoreCase("delete")) {
            String idAEliminar = request.getParameter("ID");
            try {
                dao.eliminar(idAEliminar);
            } catch (GlobalException | NoDataException ex) {
                Logger.getLogger(ServletCarrera.class.getName()).log(Level.SEVERE, null, ex);
            }
            redirect = LISTARCICLOS;

        } else if (action.equalsIgnoreCase("editForm")) {
            request.setAttribute("id", request.getParameter("ID"));
            redirect = EDIT;
        } else if (action.equalsIgnoreCase("edit")) {
            String an = request.getParameter("anno");
            String num = request.getParameter("numero");
            int anno = Integer.parseInt(an);
            int numero = Integer.parseInt(num);
            Ciclo ciclo = new Ciclo(request.getParameter("id"), anno, numero, request.getParameter("fechaInicio"), request.getParameter("fechaFin"));

            try {
                dao.modificarCiclo(ciclo);
            } catch (GlobalException | NoDataException ex) {
                Logger.getLogger(ServletCarrera.class.getName()).log(Level.SEVERE, null, ex);
            }

            redirect = LISTARCICLOS;

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
