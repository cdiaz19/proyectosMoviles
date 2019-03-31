<%-- 
    Document   : ListarCarreras
    Created on : 24-mar-2019, 7:44:06
    Author     : Alejandro
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="LogicaDeNegocio.Curso"%>
<%@page import="AccesoADatos.ServicioCurso"%>
<%@page import="LogicaDeNegocio.ListaCurso"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/BuscadorTabla.js"  type="text/javascript"></script>
        <title>Lista De Cursos</title>
    </head>
    <body>
        <%
            ServicioCurso sc = ServicioCurso.getInstancia();
            LinkedList<Curso> cursoList = sc.listarCursos();
        %>
        <table id="datos" class="table">
            <caption>Lista De Cursos</caption>
            <tr>
                <th>Id</th>
                <th>Codigo</th>
                <th>Nombre</th>
                <th>Credito</th>
                <th>Horas Semanales</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
            <tr>
                <% for (int i = 0; i < cursoList.size(); i++) {%>
                <td><%= cursoList.get(i).getId()%></td>
                <td><%= cursoList.get(i).getCodigo()%></td>
                <td><%= cursoList.get(i).getNombre()%></td>
                <td><%= cursoList.get(i).getCreditos()%></td>
                <td><%= cursoList.get(i).getHorasSemanales()%></td>
                <td>
                    <form method="POST" action="ServletCurso">
                        <button class="edit"></button>
                        <input type="hidden" name="action" value="editForm" >
                        <input type="hidden" name="ID" value="<%= cursoList.get(i).getCodigo()%>" >
                    </form>

                </td>
                <td>
                    <form method="POST" action="ServletCurso">
                        <button class="effacer" onclick="if (!confirm('Desea eliminar ?'))
                                    return false;"></button>
                        <input type="hidden" name="action" value="delete" >
                        <input type="hidden" name="ID" value="<%= cursoList.get(i).getId()%>" >
                    </form>
                </td>
            </tr>
            <%
                }
            %>	
        </table>
        <div class="lien">
            <p><a href="ServletCurso?action=insert" >Insertar Curso</a></p>
            <p><a href="javascript:history.back(-1)" >Volver</a></p>
        </div>
    </body>
</html>
