<%-- 
    Document   : Principal
    Created on : 27-feb-2018, 16:13:14
    Author     : Oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

        <script src="JS/ProductoJS.js" type="text/javascript"></script>

        <link href="CSS/css.css" rel="stylesheet" type="text/css"/>

        <title>Producto</title>
    </head>
    <body>
        <div class="container" id="contenedorPrincipal">


            <div class="panel panel-primary">
                <div class="panel-heading"><h3>Productos</h3></div>
                <div class="panel-body">

                    <div id="divBusqueda">
                        <div class="col-sm-12">
                            <form role="form" onsubmit="return false;" id="formUsuario" class="form-horizontal centered">
                                <div class="form-group" id="groupBuscarNombre">
                                    <div class="col-sm-1" style="text-align: right; vertical-align: middle;">
                                        <p><b>Nombre</b></p>
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" id="buscarNombret" placeholder="Nombre">
                                    </div>
                                    <div class="col-sm-2">
                                        <button type="button" class="btn btn-info centered" data-toggle="modal" data-target="" id="buscarNombre">
                                            Buscar <span class="glyphicon glyphicon-search"></span>
                                        </button>
                                    </div>

                                    <div class="form-group" id="groupBuscarTipo">
                                        <div class="col-sm-2" style="text-align: right; vertical-align: middle;">
                                            <p><b>Tipo</b></p>
                                        </div>
                                        <div class="col-sm-2">
                                            <select name="tipoProducto" >
                                                <option value="canastaBasica">Canasta Básica</option>
                                                <option value="popular">Popular</option>
                                                <option value="suntuario">Suntuario</option>                                
                                            </select>   
                                        </div>
                                        <div class="col-sm-2">
                                            <button type="button" class="btn btn-info centered" data-toggle="modal" data-target="" id="BuscarId">
                                                Buscar <span class="glyphicon glyphicon-search"></span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>  
                    </div>
                    <div id="divTabla">
                        <table class="table table-hover table-condensed" id="tablaProductos"></table>
                        <!--<table class="table table-hover table-condensed" id="tablaProductos">
                            <tr>
                                <td><b>Codigo</b></td>
                                <td><b>Nombre</b></td>
                                <td><b>Importado</b></td>
                                <td><b>Precio</b></td>
                                <td><b>Tipo</b></td>
                                <td><b>Porcentaje</b></td>
                                <td><b>Impuesto</b></td>
                                <td><b>Precio Final</b></td>
                            </tr>
                        </table>-->
                    </div>

                    <div id="divAgregarProductos">
                        <form>
                            <div id="divCodigo">                                
                                <b>Codigo </b><input type="text" class="" id="codigo" >
                            </div>

                            <div id="divNombre">                               
                                <b>Nombre </b><input type="text" class="" id="nombre" >                      
                            </div>

                            <div id="divPrecio">                                
                                <b>Precio </b><input type="text" class="" id="precio" >                      
                            </div>

                            <div id="divImportado">                                
                                <b>Importado </b><input type="checkbox" class="" id="importado" >                      
                            </div>

                            <div id="divTipo">
                                <b>Tipo </b><select name="tipoProducto" >
                                    <option value="canastaBasica">Canasta Básica</option>
                                    <option value="popular">Popular</option>
                                    <option value="suntuario">Suntuario</option>                                
                                </select>   
                                <input type="submit" class="btn btn-info centered" id="Agregar" value="Agregar">
                            </div>

                        </form>
                    </div>

                </div>
            </div>
        </div>

    </body>
</html>
