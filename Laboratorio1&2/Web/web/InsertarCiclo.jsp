<%-- 
    Document   : InsertarCiclo
    Created on : 30-mar-2019, 6:08:13
    Author     : Alejandro
--%>

<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/style.css" type="text/css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insertar un nuevo Ciclo</title>
</head>
<body>
	<form method="POST" action="ServletCiclo" name="insertar">
		<input type="hidden" name="action" value="insert" />
		<table>
                        <caption>Insertar un nuevo Ciclo</caption>
			<tr>
                                <td><input type="text" name="id" placeholder="Id" required/></td>
			</tr>
			<tr>
                                <td><input type="text" name="anno" placeholder="anno" required/></td>
			</tr>
			<tr>
				<td><input type="text" name="numero"  placeholder="numero" required/></td>
			</tr>
                        <tr>
				<td><input type="date" name="fechaInicio"  placeholder="fechaInicio" required/></td>
			</tr>
                        <tr>
				<td><input type="date" name="fechaFin"  placeholder="fechaFin" required/></td>
			</tr>
			<tr>
                            <td><input type="submit" value="insertar" /></td>
                            <td><p><a href="javascript:history.back(-1)" >Volver</a></p></td>
			</tr>
		</table>
	</form>
</body>
</html>

