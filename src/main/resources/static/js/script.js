// JavaScript Document

//Funcion generica para acomodar tablas por columna texto
function sortTableText(n) {
	var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	table = document.getElementById("tabla");
	switching = true;
	// Set the sorting direction to ascending:
	dir = "asc";
	/* Make a loop that will continue until
    no switching has been done: */
	while (switching) {
		// Start by saying: no switching is done:
		switching = false;
		rows = table.rows;
		/* Loop through all table rows (except the
        first, which contains table headers): */
		for (i = 1; i < (rows.length - 1); i++) {
			// Start by saying there should be no switching:
			shouldSwitch = false;
			/* Get the two elements you want to compare,
            one from current row and one from the next: */
			x = rows[i].getElementsByTagName("TD")[n];
			y = rows[i + 1].getElementsByTagName("TD")[n];
			/* Check if the two rows should switch place,
            based on the direction, asc or desc: */
			if (dir == "asc") {
				if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
					// If so, mark as a switch and break the loop:
					shouldSwitch = true;
					break;
				}
			} else if (dir == "desc") {
				if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
					// If so, mark as a switch and break the loop:
					shouldSwitch = true;
					break;
				}
			}
		}
		if (shouldSwitch) {
			/* If a switch has been marked, make the switch
            and mark that a switch has been done: */
			rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			switching = true;
			// Each time a switch is done, increase this count by 1:
			switchcount ++;
		} else {
			/* If no switching has been done AND the direction is "asc",
            set the direction to "desc" and run the while loop again. */
			if (switchcount == 0 && dir == "asc") {
				dir = "desc";
				switching = true;
			}
		}
	}
}
//Funcion generica para acomodar tablas por columna numerica
function sortTableNumber(n) {
	var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	table = document.getElementById("tabla");
	switching = true;
	// Set the sorting direction to ascending:
	dir = "asc";
	/* Make a loop that will continue until
    no switching has been done: */
	while (switching) {
		// Start by saying: no switching is done:
		switching = false;
		rows = table.rows;
		/* Loop through all table rows (except the
        first, which contains table headers): */
		for (i = 1; i < (rows.length - 1); i++) {
			// Start by saying there should be no switching:
			shouldSwitch = false;
			/* Get the two elements you want to compare,
            one from current row and one from the next: */
			x = rows[i].getElementsByTagName("TD")[n];
			y = rows[i + 1].getElementsByTagName("TD")[n];
			/* Check if the two rows should switch place,
            based on the direction, asc or desc: */
			if (dir == "asc") {
				if (Number(x.innerHTML) > Number(y.innerHTML)) {
					//if so, mark as a switch and break the loop:
					shouldSwitch = true;
					break;
				}
			} else if (dir == "desc") {
				if (Number(x.innerHTML) < Number(y.innerHTML)) {
					//if so, mark as a switch and break the loop:
					shouldSwitch = true;
					break;
				}
			}
		}
		if (shouldSwitch) {
			/* If a switch has been marked, make the switch
            and mark that a switch has been done: */
			rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			switching = true;
			// Each time a switch is done, increase this count by 1:
			switchcount ++;
		} else {
			/* If no switching has been done AND the direction is "asc",
            set the direction to "desc" and run the while loop again. */
			if (switchcount == 0 && dir == "asc") {
				dir = "desc";
				switching = true;
			}
		}
	}
}

//Funcion generica para acomodar tabla por columna fecha
function sortTableDate(n) {
	var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	table = document.getElementById("tabla");
	switching = true;
	// Set the sorting direction to ascending:
	dir = "asc";
	/* Make a loop that will continue until
    no switching has been done: */
	while (switching) {
		// Start by saying: no switching is done:
		switching = false;
		rows = table.rows;
		/* Loop through all table rows (except the
        first, which contains table headers): */
		for (i = 1; i < (rows.length - 1); i++) {
			// Start by saying there should be no switching:
			shouldSwitch = false;
			/* Get the two elements you want to compare,
            one from current row and one from the next: */
			x = rows[i].getElementsByTagName("TD")[n];
			y = rows[i + 1].getElementsByTagName("TD")[n];
			/* Check if the two rows should switch place,
            based on the direction, asc or desc: */
			if (dir == "asc") {
				if (Date(x.innerHTML) > Date(y.innerHTML)) {
					//if so, mark as a switch and break the loop:
					shouldSwitch = true;
					break;
				}
			} else if (dir == "desc") {
				if (Date(x.innerHTML) < Date(y.innerHTML)) {
					//if so, mark as a switch and break the loop:
					shouldSwitch = true;
					break;
				}
			}
		}
		if (shouldSwitch) {
			/* If a switch has been marked, make the switch
            and mark that a switch has been done: */
			rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			switching = true;
			// Each time a switch is done, increase this count by 1:
			switchcount ++;
		} else {
			/* If no switching has been done AND the direction is "asc",
            set the direction to "desc" and run the while loop again. */
			if (switchcount == 0 && dir == "asc") {
				dir = "desc";
				switching = true;
			}
		}
	}
}

function mostrar() {
	document.getElementById("message").show();
}

function ocultar() {
	document.getElementById("message").close();
}

//Te muestra un confirm para saber si quieres o no registrar una nueva categoría
function redirectCategoria(){
	if(confirm("No hay categorias registradas. ¿Deseas registrar una?")){
		//window.location = "../idManager/registrarCategoria";
		addCategoria();
	}
}

//Añade la categoria directamente desde un prompt
function addCategoria(){
	categoriaNombre = prompt("Ingresa el nombre de la categoria: ");
	if(categoriaNombre.trim() != "" || categoriaNombre != null){
		$.ajax({
			url: '/idManager/ajaxAddCategoria',
			type: 'POST',
			data: categoriaNombre,
			processData: false,
			contentType: 'application/json',
			beforeSend: function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(result){
				location.reload(true);
			},
			error: function(e){
				alert("Error: " + e);
			}
		});
		event.preventDefault();
	}else if(categoriaNombre.trim() == ""){
		//Si dejas el campo vacio te saldra un confirm, que si lo aceptas te mostrara el prompt de nuevo
		if(confirm("El campo no puede estar vacío."))
			addCategoria();
	}
}

//Añade el Nombre desde la pagina de registro de alimento.
//Toma en cuenta la categoría que esta en el SelectCategoria (SOLO EN VERSION CON RELACIONES)
function addNombre(){
	/*Quite esta parte por problemas a la hora de mostrarlos por la categoria seleccionada
	categoriaText = $('#selectCategoria option:selected').text();

	categoria = $('#selectCategoria').val();
	if($('#selectCategoria').val() != null) {
	nombreNombre = prompt("Categoria: " + categoriaText + "\nIngresa el nuevo Nombre: ");*/

	nombreNombre = prompt("Ingresa el nuevo Nombre: "); /*Eliminar si se desea utilizar la version con relaciones*/
	if(nombreNombre.trim() != "" || nombreNombre != null){
		$.ajax({
			url: '/idManager/ajaxAddNombre',
			type: 'POST',
			data: nombreNombre,
			processData: false,
			contentType: 'application/json',
			beforeSend: function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(result){
				location.reload(true);
			},
			error: function(e){
				alert("Error: " + e);
			}
		});
		event.preventDefault();
	}else if(nombreNombre.trim() == ""){
		//Si dejas el campo vacio te saldra un confirm, que si lo aceptas te mostrara el prompt de nuevo
		if(confirm("El campo no puede estar vacío."))
			addNombre(true);
	}
	/*}else{
		redirectCategoria();
	}*/

}

//Añade el Tamaño desde la pagina de registro de Alimento.
//Toma en cuenta la categoría que esta en el SelectCategoria (SOLO EN VERSION CON RELACIONES)
function addTamano(){
	/*Quite esta parte por problemas a la hora de mostrarlos por la categoria seleccionada
	categoriaText = $('#selectCategoria option:selected').text();
	categoria = $('#selectCategoria').val();
	alert(categoriaText + " " + categoria);
	if($('#selectCategoria').val() != null) {
	tamanoNombre = prompt("Categoria: " + categoria + "\nIngresa el nuevo Tamaño: ");*/

	tamanoNombre = prompt("Ingresa el nuevo Tamaño: ");/*Eliminar esta linea si se desea usar la version con relaciones*/
	if(tamanoNombre.trim() != "" || tamanoNombre != null){
		$.ajax({
			url: '/idManager/ajaxAddTamano',
			type: 'POST',
			data: tamanoNombre,
			processData: false,
			contentType: 'application/json',
			beforeSend: function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(result){
				location.reload();
			},
			error: function(e){
				alert("Error: " + e);
			}
		});
		event.preventDefault();
	}

	/*}else{
		redirectCategoria();
	}*/
}

function addAlimentoPromocion(){
	var url = '/promociones/addAlimento/' + $('#selectAlimento').val();

	$("#contenidoPromocionLoad").load(url);
}

function removeAlimentoPromocion(id){
	var url = '/promociones/removeAlimento/' + id;

	$("#contenidoPromocionLoad").load(url);
}

/*Quite esta parte por problemas a la hora de mostrarlos por la categoria seleccionada
//Llena los selects de nombres y tamaños basados en la categoría seleccionada
function fillSelects(){
	cat = $('#selectCategoria').val();
	location.assign("/alimentos/registrarAlimento?cat=" + cat);
}*/

function mostrar(id) {
	var ids = id.split("-");
	var idc = ids[0] + ids[1];
	document.getElementById(idc).style.visibility = "visible";
}

function ocultar(id) {
	var ids = id.split("*");
	var idc = ids[0] + ids[1];
	document.getElementById(idc).style.visibility = "hidden";
}

/*Funcion para validar la fecha, La fecha inical no debe ser mayor a la fecha final del campo vigencia*/
function validate() {
	if(document.getElementById('ff').value<document.getElementById('fi').value)
		document.getElementById('ff').setCustomValidity('Esta fecha debe ser mayor a la fecha inicial');
	else
		document.getElementById('ff').setCustomValidity('');
}