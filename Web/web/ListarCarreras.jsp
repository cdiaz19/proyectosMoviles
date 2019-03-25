<%-- 
    Document   : ListarCarreras
    Created on : 24-mar-2019, 7:44:06
    Author     : Alejandro
--%>

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
<title>Lista De Carreras</title>
</head>
<body>
    <label>Buscar Carrera:</label>
     <input id="searchTerm" type="text" onkeyup="doSearch()"/>
<%
	ServicioCarrera sc = ServicioCarrera.getInstancia();
	LinkedList<Carrera> carreraList = sc.listarCarreras();
%>
<table id="datos" class="table">
    <caption>Lista De Carreras</caption>
	<tr>
		<th>Id</th>
		<th>Codigo</th>
		<th>Nombre</th>
                <th>Titulo</th>
		<th>Modificar</th>
		<th>Eliminar</th>
	</tr>
	<tr>
		<% 
		for (int i = 0; i < carreraList.size(); i++) {
                    
		%>
			<td><%= carreraList.get(i).getId()  %></td>
                        <td><%= carreraList.get(i).getCodigo()  %></td>
                        <td><%= carreraList.get(i).getNombre()  %></td>
                        <td><%= carreraList.get(i).getTitulo()  %></td>
                        
			<td>
                            <form method="POST" action="ServletCarrera">
                                <button class="edit"></button>
                                <input type="hidden" name="action" value="editForm" >
                                <input type="hidden" name="ID" value="<%= carreraList.get(i).getCodigo() %>" >
                            </form>
                            
                        </td>
			<td>
                            <form method="POST" action="ServletCarrera">
                                <button class="effacer" onclick="if(!confirm('Desea eliminar ?')) return false;"></button>
                                <input type="hidden" name="action" value="delete" >
                                <input type="hidden" name="ID" value="<%= carreraList.get(i).getId() %>" >
                            </form>
                            
                        </td>
		</tr>
		<%
			}
		%>	
</table>
<div class="lien">
    
    <p><a href="ServletCarrera?action=insert" >Insertar Carrera</a></p>
</div>
</body>
</html>
