//window.onload = function() {
//
//	var searchButton = document.getElementById("searchButton");
//	var searchForm = document.getElementById("searchForm");
//	searchButton.onclick = onSearchButton;
//
//	function onSearchButton() {
//		searchForm.style.display = "block";
//		searchButton.style.display = "none";
//	}
//
//	var searchFormButton = document.getElementById("searchFormButton");
//	searchFormButton.onclick = onSearchFormButton;
//
//	function onSearchFormButton() {
//		if(document.getElementById("selectName").value == "") {
//			searchForm.style.display = "none";
//			searchButton.style.display = "block";
//		}
//	}
//}
window.onload = function() {
	var images = document.getElementById("images");
	images.onclick = imagesOn;
	function imagesOn() {
		window.location.href = "management";
	}
}