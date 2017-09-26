/*function name(element) {
	
var searchParams = new URLSearchParams(window.location.search); 
var username = searchParams.get("username");
document.getElementById("user").value = userName;
document.getElementById("demo").innerHTML =username;
}*/
							document.getElementById("fun").value = new URLSearchParams(window.location.search).get("username");
