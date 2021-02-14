var y = 0;

var images2 = ["lms/imges/l.png","lms/imges/m.png","lms/imges/s.png"];
//var images2 = new Array("nsbm1.jpg","nsbm.jpg");
var myVar2 = setInterval(changepic2, 800);

function changepic2(){
y++;
if(y == images2.length)
y = 0;
image2 = document.getElementById("nsbm");
image2.src = images2[y];
}

