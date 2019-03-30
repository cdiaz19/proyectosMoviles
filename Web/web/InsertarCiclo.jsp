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
                                <td><input type="text" name="id" placeholder="Id"/></td>
			</tr>
			<tr>
                                <td><input type="text" name="anno" placeholder="anno" /></td>
			</tr>
			<tr>
				<td><input type="text" name="numero"  placeholder="numero" /></td>
			</tr>
                        <tr>
				<td><input type="text" name="fechaInicio"  placeholder="fechaInicio" /></td>
			</tr>
                        <tr>
				<td><input type="text" name="fechaFin"  placeholder="fechaFin" /></td>
			</tr>
			<tr>
                            <td><input type="submit" value="insertar" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="anular" /></td>
			</tr>
		</table>
	</form>
</body>
</html>

