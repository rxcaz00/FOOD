/**
 * REEMPLAZADO POR UNA SOLUCION SIN JSON
 * Script AJAX para obtener un alimento a partir del alimento seleccionado en el SELECT #selectAlimento
 * @author Andrés
 * */
/*function addAlimento() {
        $.ajax({
            url: '/venta/addAlimento',
            type: "POST",
            data: JSON.stringify($('#selectAlimento').val()),
            processData: false,
            contentType: 'application/json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (result) {//Mensaje de Exito
                alert(result.id + "\n" + result.nombre + "\n" + result.categoria + "\n" + result.tamano + "\n$" + result.precio);
            },
            error: function (e) {//Mensaje de Error
                alert("Error: " + e);
            }
        });
        event.preventDefault();
}*/

/**REEMPLAZADO POR UNA SOLUCION SIN JSON
 * Script AJAX para obtener una promocion y sus alimentos a partir de la promocion seleccionada en el SELECT #selectPromocion
 * @author Andrés
 * */
/*function addPromocion(){
    $.ajax({
       url: '/venta/findPromocion',
       type: 'POST',
       data: JSON.stringify($('#selectPromocion').val()),
       processData: false,
       contentType: 'application/json',
       beforeSend: function(xhr){
           xhr.setRequestHeader("Accept", "application/json");
           xhr.setRequestHeader("Content-Type", "application/json");
       },
       success: function(result){
           alert("Promocion registrada");
       },
       error: function(e){
           alert("Error: " + e);
       }
    });
    event.preventDefault();
}*/

/**REEMPLAZADO POR UNA SOLUCION SIN JSON
 * Script AJAX para obtener un alimento a partir del alimento seleccionado en el SELECT #selectAlimento
 * @author Diana
 * Es para promociones
 * */
/*function addAlimento1() {
    $.ajax({
        url: '/promociones/addAlimento',
        type: "POST",
        data: JSON.stringify($('#selectAlimento1').val()),
        processData: false,
        contentType: 'application/json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (result) {//Mensaje de Exito
            alert(result.id + "\n" + result.nombre + "\n" + result.categoria + "\n" + result.tamano + "\n$" + result.precio);
        },
        error: function (e) {//Mensaje de Error
            alert("Error: " + e);
        }
    });
    event.preventDefault();
}*/

/**
 * Revisa cuando le dan click a un radio button y si es el grupo con el nombre orden. Esconde todos los elemento
 * con la clase hiddenDiv y solo muestra el elemento que tenga el ID indicado.
 *
 * @author Andrés
 * */
$(document).ready(function(){

    $('input[name="orden"]').on("changed", function () {
        var inputValue = $(this).val();
        $("div.hiddenDiv").hide();
        var targetDiv = "#show" + inputValue;
        $(targetDiv).show();
    });

    $("#checkDir").change(function(){
        if($(this).is(':checked')) {
            $("#dirEnvio").attr('readonly',"");
            $("#dirEnvio").val("");
        }else
            $("#dirEnvio").removeAttr('readonly');
    });

    $("#checkRec").change(function () {
        if($(this).is(':checked')){
            $('#recNombre').attr('readonly',"");
            $('#recNombre').val("");
        }else
            $("#recNombre").removeAttr('readonly');
    });

});

/**
 * Toma el valor del input con el ID #telefono. Si no esta vacio concatena el telefono al URL.
 * Recarga el contenedor que tiene el ID clienteLoad con el resultado del URL.
 *
 * @author Andrés
 * */
function updateCliente() {
    var url = '/venta/findCliente';

    if ($('#telefono').val() != '') {
        url = url + '/' + $('#telefono').val();
    }

    //Al parecer se deben de mandar los datos al contenedor del form, no al form
    $("#clienteLoad").load(url);
}

function findCliente(id){
    var url = '/venta/findCliente/' + id;

    $("#clienteLoad").load(url);
}

/**
 * Concatena el valor del SELECT con el ID selectAlimento al URL.
 * Carga el resultado del URL en el contenedor con el ID contenidosReciboLoad
 *
 * @author Andrés
 * */
function addAlimento(){
    var url = '/venta/addAlimento/' + $('#selectAlimento').val();

    $("#contenidosReciboLoad").load(url);
}

/**
 * Concatena el valor del ID del elemento del fragment contenidoRecibo seleccionado.
 * Carga el resultado del URL en el contenedor con el ID contenidosReciboLoad.
 *
 * @author Andrés
 * */
function removeAlimento(id){
    var url = '/venta/removeAlimento/' + id;

    $("#contenidosReciboLoad").load(url);
}

/**
 * Concatena el valor del SELECT con el ID selectPromocion con el URL.
 * Carga el resultado del URL en el contenedor con el ID contenidosReciboLoad
 *
 * @author Andrés
 * */
function addPromocion(){
    var url = '/venta/addPromocion/' + $('#selectPromocion').val();

    $("#contenidosReciboLoad").load(url);
}

/**
 * Concatena el parametro ID (Que corresponde al ID del elemento ContenidoPromocion seleccionado)
 * Carga el resultado del URL en el contenedor con el ID contenidosReciboLoad
 *
 * @author Andrés
 * */
function removePromocion(id){
    var url = '/venta/removePromocion/' + id;

    $("#contenidosReciboLoad").load(url);
}


