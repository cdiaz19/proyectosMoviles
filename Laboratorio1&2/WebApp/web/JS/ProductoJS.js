$(document).ready(function () {
    consultarProducto();
});
$("#Agregar").click(function () {
        enviar();
    });
$("#BuscarId").click(function () {
        consultarProductoByTipo($("#tipo").val("tipoProducto"));
    });
$("#buscarNombre").click(function () {
        consultarProductoByTipo($("#buscarNombret").val());
    });

function consultarProducto() {
    $.ajax({
        url: 'ProductoServlet',
        data: {
            accion: "consultarProducto"
        },
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información de productos en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            alert("Listo");
            dibujarTabla(data);
            // se oculta el modal esta funcion se encuentra en el utils.js
            //ocultarModal("myModal");

        },
        type: 'POST',
        dataType: "json"
    });
}

function agregarProducto() {
    $.ajax({
        url: 'ProductoServlet',
        data: {
            accion: "agregarProducto"
        },
        error: function () { //si existe un error en la respuesta del ajax
            alert("Se presento un error a la hora de cargar la información de productos en la base de datos");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            alert("Listo");
           // dibujarTabla(data);
            // se oculta el modal esta funcion se encuentra en el utils.js
            //ocultarModal("myModal");

        },
        type: 'POST',
        dataType: "json"
    });
}

function dibujarTabla(dataJson) {
    //limpia la información que tiene la tabla
    $("#tablaProductos").html("");

    //muestra el enzabezado de la tabla
    var head = $("<thead />");
    var row = $("<tr />");
    head.append(row);
    $("#tablaProductos").append(head);
    row.append($("<th><b>CODIGO</b></th>"));
    row.append($("<th><b>NOMBRE</b></th>"));
    row.append($("<th><b>IMPORTADO</b></th>"));
    row.append($("<th><b>PRECIO</b></th>"));
    row.append($("<th><b>TIPO</b></th>"));
    row.append($("<th><b>PORCENTAJE</b></th>"));
    row.append($("<th><b>IMPUESTO</b></th>"));
    row.append($("<th><b>PRECIOFINAL</th>"));

    //carga la tabla con el json devuelto
    for (var i = 0; i < dataJson.length; i++) {
        dibujarFila(dataJson[i]);
    }
}

function dibujarFila(rowData) {

    var row = $("<tr />");
    $("#tablaProductos").append(row);
    row.append($("<td>" + rowData.codigo + "</td>"));
    row.append($("<td>" + rowData.nombre + "</td>"));///////////////////////////////////////////
    row.append($("<td>" + rowData.importado + "</td>"));
    row.append($("<td>" + rowData.precio + "</td>"));
    row.append($("<td>" + rowData.tipo.getDescripcion() + "</td>"));
    row.append($("<td>" + rowData.porcentaje + "</td>"));
    row.append($("<td>" + rowData.impuesto + "</td>"));
    row.append($("<td>" + rowData.precioFinal + "</td>"));

}

function enviar() {
    if (validar()) {
        //Se envia la información por ajax
        $.ajax({
            url: 'ProductoServlet',
            data: {
                accion: "agregarProducto",
                codigo: $("#codigo").val(),
                nombre:$("#nombre").val(), /////////////////////////////////////////
                importado: $("#impuesto").val(),
                precio: $("#precio").val(),
                tipo: $("#tipo").val()
            },
            error: function () { //si existe un error en la respuesta del ajax
                mostrarMensaje("alert alert-danger", "Se genero un error, contacte al administrador (Error del ajax)", "Error!");
            },
            success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
                var respuestaTxt = data.substring(2);
                var tipoRespuesta = data.substring(0, 2);
                if (tipoRespuesta === "C~") {
                    mostrarMensaje("alert alert-success", respuestaTxt, "Correcto!");
                    $("#myModalFormulario").modal("hide");
                    consultarProducto();
                } else {
                    if (tipoRespuesta === "E~") {
                        mostrarMensaje("alert alert-danger", respuestaTxt, "Error!");
                    } else {
                        mostrarMensaje("alert alert-danger", "Se genero un error, contacte al administrador", "Error!");
                    }
                }

            },
            type: 'POST'
        });
    } else {
        mostrarMensaje("alert alert-danger", "Debe digitar los campos del formulario", "Error!");
    }
}

function validar() {
    var validacion = true;

    //Elimina estilo de error en los css
    //notese que es sobre el grupo que contienen el input
    $("#groupCodigo").removeClass("has-error");
    $("#groupNombre").removeClass("has-error");
    $("#groupImportado").removeClass("has-error");
    $("#groupPrecio").removeClass("has-error");
    $("#groupTipo").removeClass("has-error");
    $("#groupPorcentaje").removeClass("has-error");
    $("#groupImpuesto").removeClass("has-error");
    $("#groupPrecioFinal").removeClass("has-error");

    //valida cada uno de los campos del formulario
    //Nota: Solo si fueron digitados
    if ($("#codigo").val() === "") {
        $("#groupCodigo").addClass("has-error");
        validacion = false;////////////////////////////////////////////////////////////////////////////////////////
    }
    if ($("#nombre").val() === "") {
        $("#groupNombre").addClass("has-error");
        validacion = false;
    }
    if ($("#importado").val() === "") {
        $("#groupImportado").addClass("has-error");
        validacion = false;
    }
    if ($("#precio").val() === "") {
        $("#groupPrecio").addClass("has-error");
        validacion = false;
    }
    if ($("#tipo").val() === "") {
        $("#groupTipo").addClass("has-error");
        validacion = false;
    }
    if ($("#porcentaje").val() === "") {
        $("#groupPorcentaje").addClass("has-error");
        validacion = false;
    }
    if ($("#impuesto").val() === "") {
        $("#groupImpuesto").addClass("has-error");
        validacion = false;
    }
    if ($("#precioFinal").val() === "") {
        $("#groupPrecioFinal").addClass("has-error");
        validacion = false;
    }
   


    return validacion;
}

function consultarProductoByNombre(nombre) {
    mostrarModal("myModal", "Espere por favor..", "Consultando el vehiculo seleccionado");
    //Se envia la información por ajax
    $.ajax({
        url: 'ProductoServlet',
        data: {
            accion: "consultarProductoByNombre",
            nombre: nombre
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("myModal", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            // se oculta el mensaje de espera
            ocultarModal("myModal");
            //consultarChoferes();
            limpiarForm();

            //se muestra el formulario
            $("#myModalFormulario").modal();

            //************************************************************************
            //carga información de la persona en el formulario
            //************************************************************************
            //se indicar que la cédula es solo readOnly
            $("#codigo").attr('readonly', 'readonly');
            $("#nombre").attr('readonly', 'readonly');
            //se modificar el hidden que indicar el tipo de accion que se esta realizando
            $("#productoAction").val("modificarProducto");

            //se carga la información en el formulario
            $("#codigo").val(data.codigo);
            $("#nombre").val(data.nombre);
            $("#importado").val(data.importado);
            $("#precio").val(data.precio);
            $("#tipo").val(data.tipo.descripcion);
            $("#porcentaje").val(data.porcentaje());
             $("#impuesto").val(data.impuesto());
              $("#precioFinal").val(data.pecioFinal());



        },
        type: 'POST',
        dataType: "json"
    });
}

function consultarProductoByTipo(tipo) {
    mostrarModal("myModal", "Espere por favor..", "Consultando el vehiculo seleccionado");
    //Se envia la información por ajax
    $.ajax({
        url: 'ProductoServlet',
        data: {
            accion: "consultarProductoByTipo",
            tipo: tipo
        },
        error: function () { //si existe un error en la respuesta del ajax
            cambiarMensajeModal("myModal", "Resultado acción", "Se presento un error, contactar al administador");
        },
        success: function (data) { //si todo esta correcto en la respuesta del ajax, la respuesta queda en el data
            // se oculta el mensaje de espera
            ocultarModal("myModal");
            //consultarChoferes();
            limpiarForm();

            //se muestra el formulario
            $("#myModalFormulario").modal();

            //************************************************************************
            //carga información de la persona en el formulario
            //************************************************************************
            //se indicar que la cédula es solo readOnly
            $("#codigo").attr('readonly', 'readonly');
            $("#nombre").attr('readonly', 'readonly');
            //se modificar el hidden que indicar el tipo de accion que se esta realizando
            $("#productoAction").val("modificarProducto");

            //se carga la información en el formulario
            $("#codigo").val(data.codigo);
            $("#nombre").val(data.nombre);
            $("#importado").val(data.importado);
            $("#precio").val(data.precio);
            $("#tipo").val(data.tipo.descripcion);
            $("#porcentaje").val(data.porcentaje());
             $("#impuesto").val(data.impuesto());
              $("#precioFinal").val(data.pecioFinal());



        },
        type: 'POST',
        dataType: "json"
    });
}


function mostrarMensaje(classCss, msg, neg) {
    //se le eliminan los estilos al mensaje
    $("#mesajeResult").removeClass();

    //se setean los estilos
    $("#mesajeResult").addClass(classCss);

    //se muestra la capa del mensaje con los parametros del metodo
    $("#mesajeResult").fadeIn("slow");
    $("#mesajeResultNeg").html(neg);
    $("#mesajeResultText").html(msg);
    $("#mesajeResultText").html(msg);
}

//******************************************************************************
//******************************************************************************

function limpiarForm() {
    //setea el focus del formulario
    $('#codigo').focus();
    $("#codigo").removeAttr("readonly"); //elimina el atributo de solo lectura

    //se cambia la accion por agregarUsuario
//    $("#productoAction").val("agregarProducto");

    //esconde el div del mensaje
    mostrarMensaje("hiddenDiv", "", "");

    //Resetear el formulario
    $('#formProducto').trigger("reset");
}