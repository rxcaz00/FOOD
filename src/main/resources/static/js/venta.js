/**
 * Script AJAX para obtener un alimento a partir del alimento seleccionado en el SELECT #selectAlimento
 * @author Andrés
 * */
function addAlimento() {
    //$('#addAlimento').click(function(event) {
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
    //});
}