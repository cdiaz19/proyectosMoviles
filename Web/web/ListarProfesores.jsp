<%-- 
    Document   : ListarProfesores
    Created on : 26-mar-2019, 2:23:35
    Author     : Alejandro
--%>

<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="AccesoADatos.ServicioProfesor" %>
<%@ page import="LogicaDeNegocio.Profesor" %>
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
    <label>Buscar Profesor:</label>
     <input id="searchTerm" type="text" onkeyup="doSearch()"/>
<%
	ServicioProfesor sp = ServicioProfesor.getInstancia();
	LinkedList<Profesor> profesorList = sp.listarProfesores();
%>
<table id="datos" class="table">
    <caption>Lista De Carreras</caption>
	<tr>
		<th>Id</th>
		<th>Nombre</th>
		<th>Correo</th>
                <th>Telefono</th>
                <th>Cedula</th>
                <th>Contrasena</th>
		<th>Modificar</th>
		<th>Eliminar</th>
	</tr>
	<tr>
		<% 
		for (int i = 0; i < profesorList.size(); i++) {
                    
		%>
			<td><%= profesorList.get(i).getId()  %></td>
                        <td><%= profesorList.get(i).getNombre()  %></td>
                        <td><%= profesorList.get(i).getCorreo()  %></td>
                        <td><%= profesorList.get(i).getTelefono()  %></td>
                        <td><%= profesorList.get(i).getUsuario().getCedula()  %></td>
                        <td><%= profesorList.get(i).getUsuario().getContrasena()  %></td>
                        
			<td>
                            <form method="POST" action="ServletProfesor">
                                <button class="edit"></button>
                                <input type="hidden" name="action" value="editForm" >
                                <input type="hidden" name="ID" value="<%= profesorList.get(i).getId() %>" >
                            </form>
                            
                        </td>
			<td>
                            <form method="POST" action="ServletProfesor">
                                <button class="effacer" onclick="if(!confirm('Desea eliminar ?')) return false;"></button>
                                <input type="hidden" name="action" value="delete" >
                                <input type="hidden" name="ID" value="<%= profesorList.get(i).getId() %>" >
                            </form>
                            
                        </td>
		</tr>
		<%
			}
		%>	
</table>
<div class="lien">
    
    <p><a href="ServletProfesor?action=insert" >Insertar Carrera</a></p>
</div>
</body>
</html>

