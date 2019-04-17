// JavaScript Document

function show(texto,flecha) {
	x = document.getElementById(texto);
	y = document.getElementById(flecha);
	if(x.style.visibility == "hidden") {
		x.style.visibility = "visible";
		x.style.height = "auto";
		y.style.transform = "rotate(180deg)";
	}
	else {
		x.style.visibility = "hidden";
		x.style.height = "10px";
		y.style.transform = "none";
	}
}