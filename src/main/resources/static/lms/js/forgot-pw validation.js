function fpw_validation(){
var x = document.getElementById("un").value.length;
var y = document.getElementById("pw").value.length;
var valid = true;

if(x<1 && y<1){
valid = false;
document.getElementById("un1").innerHTML = "Please fill these fields and send"; 
document.getElementById("pw1").innerHTML = "Please fill these fields and send";
}
return valid;
}