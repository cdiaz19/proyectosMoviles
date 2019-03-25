<%-- 
    Document   : ModificarCarrera
    Created on : 25-mar-2019, 1:31:01
    Author     : Alejandro
--%>
<%@page import="AccesoADatos.ServicioCarrera"%>
<%@page import="LogicaDeNegocio.Carrera"%>
<%@ page import="java.util.LinkedList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" href="css/style.css" type="text/css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Modificar Carrera</title>
    </head>
    <body>
        <%
            ServicioCarrera sc = ServicioCarrera.getInstancia();
            LinkedList<Carrera> carreraList = sc.buscarPorCodigo((String)request.getAttribute("id"));
            
        %>
	<form method="POST" action="ServletCarrera" name="modifica">
		<input type="hidden" name="action" value="edit" /> 
                <input type="hidden" name="id" value="<%= carreraList.get(0).getCodigo() %>" /> 
                <table class="tableModifier">
                        <caption>Modificar Carrera</caption>
			<tr>
				<td class="l"> Id : </td>
                                <td class="r"><input type="text" name="id" disabled value="<%= carreraList.get(0).getId() %>"/></td>
			</tr>
			<tr>
				<td class="l">Codigo : </td>
                                <td class="r"><input type="text" name="codigo" value="<%= carreraList.get(0).getCodigo() %>"/></td>
			</tr>
			<tr>
				<td class="l">Nombre : </td>
				<td class="r"><input type="text" name="nombre" value="<%= carreraList.get(0).getNombre() %>"/></td>
			</tr>
                        <tr>
				<td class="l">Titulo : </td>
				<td class="r"><input type="text" name="titulo" value="<%= carreraList.get(0).getTitulo() %>"/></td>
			</tr>
			<tr>
				<td class="l"><input type="submit" value="edit" /></td>
				<td class="r"><input type="reset" value="cancel" /></td>
			</tr>
		</table>
	</form>
    </body>
</html>