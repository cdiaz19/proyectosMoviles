<%-- 
    Document   : ModificarCiclo
    Created on : 30-mar-2019, 5:55:59
    Author     : Alejandro
--%>

<%-- 
    Document   : ModificarProfesor
    Created on : 26-mar-2019, 2:36:51
    Author     : Alejandro
--%>

<%@page import="AccesoADatos.ServicioCiclo"%>
<%@page import="LogicaDeNegocio.Ciclo"%>
<%@ page import="java.util.LinkedList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" href="css/style.css" type="text/css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Modificar Ciclo</title>
    </head>
    <body>
        <%
            ServicioCiclo sc = ServicioCiclo.getInstancia();
            LinkedList<Ciclo> cicloList = sc.buscarPorCiclo((String)request.getAttribute("id"));
            
        %>
	<form method="POST" action="ServletCiclo" name="modifica">
		<input type="hidden" name="action" value="edit" /> 
                <input type="hidden" name="id" value="<%= cicloList.get(0).getId() %>" /> 
                <table class="tableModifier">
                        <caption>Modificar Ciclo</caption>
			<tr>
				<td class="l"> Id : </td>
                                <td class="r"><input type="text" name="id" disabled value="<%= cicloList.get(0).getId() %>"/></td>
			</tr>
			<tr>
				<td class="l">Anno : </td>
                                <td class="r"><input type="text" name="anno" value="<%= cicloList.get(0).getAnno() %>"/></td>
			</tr>
			<tr>
				<td class="l">Numero : </td>
				<td class="r"><input type="text" name="numero" value="<%= cicloList.get(0).getNumero() %>"/></td>
			</tr>
                        <tr>
				<td class="l">Fecha Inicio : </td>
				<td class="r"><input type="text" name="fechaInicio" value="<%= cicloList.get(0).getFechaInicio() %>"/></td>
			</tr>
                         <tr>
				<td class="l">Fecha Final : </td>
				<td class="r"><input type="text" name="fechaFin" value="<%= cicloList.get(0).getFechaFinal() %>"/></td>
			</tr>
                       
			<tr>
				<td class="l"><input type="submit" value="modifica" /></td>
				<td class="r"><input type="reset" value="cancel" /></td>
			</tr>
		</table>
	</form>
    </body>
</html>
