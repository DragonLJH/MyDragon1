window.onload = function() {
	if(document.getElementById("success").innerHTML == "借书成功") {
		document.getElementById("nav-profile").classList.add("active");
		document.getElementById("nav-home").classList.remove("active");
		document.getElementById("profile").classList.add("active");
		document.getElementById("home").classList.remove("active");
	}
}