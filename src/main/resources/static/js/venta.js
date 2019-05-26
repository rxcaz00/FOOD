/**
 * Script AJAX para obtener un alimento a partir del alimento seleccionado en el SELECT #selectAlimento
 * @author Andrés
 * */
function addAlimento() {
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
}

/**
 * Script AJAX para obtener una promocion y sus alimentos a partir de la promocion seleccionada en el SELECT #selectPromocion
 * @author Andrés
 * */
function addPromocion(){
    $.ajax({
       url: '/venta/addPromocion',
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
}


/**
 * Script AJAX para obtener un alimento a partir del alimento seleccionado en el SELECT #selectAlimento
 * @author Diana
 * Es para promociones
 * */
function addAlimento1() {
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
}


function mostrarMaldad() {

            alert(JSON.stringify($('#selectAlimento1').val()));
}