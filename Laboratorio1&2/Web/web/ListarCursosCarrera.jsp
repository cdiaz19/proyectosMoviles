<%-- 
    Document   : ListarCarreras
    Created on : 24-mar-2019, 7:44:06
    Author     : Alejandro
--%>

<%@page import="LogicaDeNegocio.ListaCurso"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="AccesoADatos.ServicioCarrera" %>
<%@ page import="LogicaDeNegocio.Carrera" %>
<%@ page import="java.util.LinkedList" %>
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
        ServicioCarrera sc = ServicioCarrera.getInstancia();
        LinkedList<ListaCurso> carreraList = sc.listarCursosCarrera((String) request.getAttribute("id"));
    %>
<table id="datos" class="table">
    <caption>Lista De Cursos</caption>
	<tr>
            <th>Id</th>
            <th>Ano</th>
            <th>Carrera</th>
            <th>Curso</th>
            <th>Ciclo</th>
	</tr>
	<tr>
            <% for (int i = 0; i < carreraList.size(); i++) {%>
            <td><%= carreraList.get(i).getId()%></td>
            <td><%= carreraList.get(i).getAnno()%></td>
            <td><%= carreraList.get(i).getCarreraId()%></td>
            <td><%= carreraList.get(i).getCursoId()%></td>
            <td><%= carreraList.get(i).getCicloId()%></td> 
        </tr>
            <%
		}
            %>	
</table>
<div class="lien">
    <p><a href="javascript:history.back(-1)" >Volver a Listar Carreras</a></p>
    <p><a href="javascript:history.back(-1)" >Volver</a></p>
</div>
</body>
</html>
