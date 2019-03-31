<%-- 
    Document   : ModificarCarrera
    Created on : 25-mar-2019, 1:31:01
    Author     : Alejandro
--%>
<%@page import="AccesoADatos.ServicioCurso"%>
<%@page import="LogicaDeNegocio.Curso"%>
<%@ page import="java.util.LinkedList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Modificar Curso</title>
    </head>
    <body>
        <%
            ServicioCurso sc = ServicioCurso.getInstancia();
            LinkedList<Curso> cursoList = sc.buscarPorCodigo((String) request.getAttribute("id"));
        %>
        <form method="POST" action="ServletCurso" name="modifica">
            <input type="hidden" name="action" value="edit" /> 
            <input type="hidden" name="id" value="<%= cursoList.get(0).getId()%>" /> 
            <table class="tableModifier">
                <caption>Modificar Curso</caption>
                <tr>
                    <td class="l"> Id : </td>
                    <td class="r"><input type="text" name="id" disabled value="<%= cursoList.get(0).getId()%>"/></td>
                </tr>
                <tr>
                    <td class="l">Credito : </td>
                    <td class="r"><input type="text" name="codigo" value="<%= cursoList.get(0).getCodigo()%>"/></td>
                </tr>
                <tr>
                    <td class="l">Nombre : </td>
                    <td class="r"><input type="text" name="nombre" value="<%= cursoList.get(0).getNombre()%>" required/></td>
                </tr>
                <tr>
                    <td class="l">Creditos : </td>
                    <td class="r"><input type="number" name="creditos" value="<%= cursoList.get(0).getCreditos()%>" required/></td>
                </tr>
                <tr>
                    <td class="l">HorasSemanales : </td>
                    <td class="r"><input type="number" name="horasSemanales" value="<%= cursoList.get(0).getHorasSemanales()%>" required/></td>
                </tr>
                <tr>
                    <td class="l"><input type="submit" value="modifica" /></td>
                    <td class="r"><p><a href="javascript:history.back(-1)" >Volver</a></p></td>
                </tr>
            </table>
        </form>
    </body>
</html>