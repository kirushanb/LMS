function validation(){
var u = document.forms['acnt']['un'].value.length;
if(u < 1){
document.getElementById("un1").innerHTML="Please fill this";
return false;
}




var p = document.forms['acnt']['pw'].value.length;
if(p < 8){
document.getElementById("pw1").innerHTML="Password must have at least 8 characters including upper-case,lower-case letters,numbers,alphanumeric characters";
return false;
}



var p = document.forms['acnt']['pw'].value;
var r = document.forms['acnt']['repw'].value;
if(p != r){
document.getElementById("re1").innerHTML="Must have same characters as the password above";
return false;
}



var e = document.forms['acnt']['em'].value.length;
var atpos = e.indexOf("@");
var dotpos = e.lastIndexOf(".");
if(atpos<1 || dotpos<atpos+2 || dotpos+2>=e.length) {
document.getElementById("em1").innerHTML="Valid e-mail address needed";
return false;
}



var i = document.forms['acnt']['in'].value.length;
if(i < 1){
document.getElementById("in1").innerHTML="Please fill Index number";
return false;
}
return true;
}
