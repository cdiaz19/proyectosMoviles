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
				<td><input type="text" name="nombre"  placeholder="nombre" required/></td>
			</tr>
                        <tr>
				<td><input type="email" name="correo"  placeholder="correo" required/></td>
			</tr>
                        <tr>
				<td><input type="text" name="telefono"  placeholder="telefono" required/></td>
			</tr>
                        <tr>
				<td><input type="password" name="contrasena"  placeholder="contrasena" required/></td>
			</tr>
			<tr>
                            <td><input type="submit" value="insertar" /></td>
                            <td><p><a href="javascript:history.back(-1)" >Volver</a></p></td>
			</tr>
		</table>
	</form>
</body>
</html>
