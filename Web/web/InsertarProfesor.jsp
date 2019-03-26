<%-- 
    Document   : InsertarProfesor
    Created on : 26-mar-2019, 2:45:40
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
<title>Insertar un nuevo Profesora</title>
</head>
<body>
	<form method="POST" action="ServletProfesor" name="insertar">
		<input type="hidden" name="action" value="insert" />
		<table>
                        <caption>Insertar un nuevo Profesor</caption>
			<tr>
                                <td><input type="text" name="id" placeholder="Id"/></td>
			</tr>
			<tr>
                                <td><input type="text" name="cedula" placeholder="cedula" /></td>
			</tr>
			<tr>
				<td><input type="text" name="nombre"  placeholder="nombre" /></td>
			</tr>
                        <tr>
				<td><input type="email" name="correo"  placeholder="correo" /></td>
			</tr>
                        <tr>
				<td><input type="text" name="telefono"  placeholder="telefono" /></td>
			</tr>
                        <tr>
				<td><input type="text" name="contrasena"  placeholder="contrasena" /></td>
			</tr>
			<tr>
                            <td><input type="submit" value="insertar" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="anular" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
