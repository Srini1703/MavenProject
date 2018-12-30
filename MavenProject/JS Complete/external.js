function validate(){
	var ele = document.getElementById('myTable');
	 if (ele.style.display == "block") {
		ele.style.display = "none";
		document.getElementById('para3').style.backgroundColor="";
	} else {
		ele.style.display = "block";
		document.getElementById('para3').style.backgroundColor="Orange";
	}
}
