window.onload = function() {
	var aLoginSubmit = document.getElementById("a-loginSubmit");
	aLoginSubmit.onclick = doFormLoginSubnit;

	function doFormLoginSubnit() {
		if(document.getElementById("input-login").value != "" &&
			document.getElementById("inputPassword").value != "") {
			document.getElementById("formS").submit();
		}
	}
}