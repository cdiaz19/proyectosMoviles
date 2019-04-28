<%-- 
    Document   : ModificarProfesor
    Created on : 26-mar-2019, 2:36:51
    Author     : Alejandro
--%>

<%@page import="AccesoADatos.ServicioProfesor"%>
<%@page import="LogicaDeNegocio.Profesor"%>
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
            ServicioProfesor sc = ServicioProfesor.getInstancia();
            LinkedList<Profesor> profesorList = sc.buscarProfesor((String) request.getAttribute("id"));

        %>
        <form method="POST" action="ServletProfesor" name="modifica">
            <input type="hidden" name="action" value="edit" /> 
            <input type="hidden" name="id" value="<%= profesorList.get(0).getId()%>" /> 
            <table class="tableModifier">
                <caption>Modificar Carrera</caption>
                <tr>
                    <td class="l"> Id : </td>
                    <td class="r"><input type="text" name="id" disabled value="<%= profesorList.get(0).getId()%>"/></td>
                </tr>
                <tr>
                    <td class="l">Cedula : </td>
                    <td class="r"><input type="text" name="cedula" value="<%= profesorList.get(0).getUsuario().getCedula()%>" required/></td>
                </tr>
                <tr>
                    <td class="l">Nombre : </td>
                    <td class="r"><input type="text" name="nombre" value="<%= profesorList.get(0).getNombre()%>" required/></td>
                </tr>
                <tr>
                    <td class="l">Telefono : </td>
                    <td class="r"><input type="text" name="telefono" value="<%= profesorList.get(0).getTelefono()%>" required/></td>
                </tr>
                <tr>
                    <td class="l">Correo : </td>
                    <td class="r"><input type="email" name="correo" value="<%= profesorList.get(0).getCorreo()%>" required/></td>
                </tr>
                <tr>
                    <td class="l">Contrasena : </td>
                    <td class="r"><input type="password" name="contrasena" value="<%= profesorList.get(0).getUsuario().getContrasena()%>" required/></td>
                </tr>
                <tr>
                    <td class="l"><input type="submit" value="modifica" /></td>
                    <td class="r"><p><a href="javascript:history.back(-1)" >Volver</a></p></td>
                </tr>
            </table>
        </form>
    </body>
</html>