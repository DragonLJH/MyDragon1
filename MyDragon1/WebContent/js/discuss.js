function doOnclick() {
	document.getElementById("discussInput").style.display = "none";
	document.getElementById("discussTextarea").style.display = "block";
	document.getElementById("discussTextarea").focus();
}

function doOnblur() {
	document.getElementById("discussInput").style.display = "block";
	document.getElementById("discussTextarea").style.display = "none";
}
