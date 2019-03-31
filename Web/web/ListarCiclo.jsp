<%-- 
    Document   : ListarCiclo
    Created on : 30-mar-2019, 5:29:02
    Author     : Alejandro
--%>

<%@ page language="java" contentType="text/html; charset=windows-1256"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="AccesoADatos.ServicioCiclo" %>
<%@ page import="LogicaDeNegocio.Ciclo" %>
<%@ page import="java.util.LinkedList" %>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/BuscadorTabla.js"  type="text/javascript"></script>
        <title>Lista De Ciclos</title>
    </head>
    <body>
        <label>Buscar Profesor:</label>
        <input id="searchTerm" type="text" onkeyup="doSearch()"/>
        <%
            ServicioCiclo sc = ServicioCiclo.getInstancia();
            LinkedList<Ciclo> cicloList = sc.listarCiclos();
        %>
        <table id="datos" class="table">
            <caption>Lista De Ciclos</caption>
            <tr>
                <th>Id</th>
                <th>Anno</th>
                <th>Numero</th>
                <th>Fecha de Inicio</th>
                <th>Fecha Final</th>
                <th>Modificar</th>
                <th>Eliminar</th>
            </tr>
            <tr>
                <% for (int i = 0; i < cicloList.size(); i++) {%>
                <td><%= cicloList.get(i).getId()%></td>
                <td><%= cicloList.get(i).getAnno()%></td>
                <td><%= cicloList.get(i).getNumero()%></td>
                <td><%= cicloList.get(i).getFechaInicio()%></td>
                <td><%= cicloList.get(i).getFechaFinal()%></td>

                <td>
                    <form method="POST" action="ServletCiclo">
                        <button class="edit"></button>
                        <input type="hidden" name="action" value="editForm" >
                        <input type="hidden" name="ID" value="<%= cicloList.get(i).getId()%>" >
                    </form>
                </td>
                <td>
                    <form method="POST" action="ServletCiclo">
                        <button class="effacer" onclick="if (!confirm('Desea eliminar ?'))
                                            return false;"></button>
                        <input type="hidden" name="action" value="delete" >
                        <input type="hidden" name="ID" value="<%= cicloList.get(i).getId()%>" >
                    </form>
                </td>
            </tr>
            <%
                }
            %>	
        </table>
        <div class="lien">
            <p><a href="ServletCiclo?action=insert" >Insertar Ciclo</a></p>
            <p><a href="javascript:history.back(-1)" >Volver</a></p>
        </div>
    </body>
</html>
