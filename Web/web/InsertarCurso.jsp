<%-- 
    Document   : InsertarCurso
    Created on : 24-mar-2019, 19:42:44
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
        <title>Insertar un Nuevo Curso</title>
    </head>
    <body>
        <form method="POST" action="ServletCurso" name="insertar">
            <input type="hidden" name="action" value="insert" />
            <table>
                <caption>Insertar un Nuevo Curso</caption>
                <tr>
                    <td><input type="text" name="id" placeholder="Id"/></td>
                </tr>
                <tr>
                    <td><input type="text" name="codigo" placeholder="codigo" /></td>
                </tr>
                <tr>
                    <td><input type="text" name="nombre"  placeholder="nombre" /></td>
                </tr>
                <tr>
                    <td><input type="number" name="creditos"  placeholder="creditos" /></td>
                </tr>
                <tr>
                    <td><input type="number" name="horasSemanales"  placeholder="horasSemanales" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="insertar" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="anular" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>
