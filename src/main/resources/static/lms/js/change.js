var x = 0;
//var images = ["old1.png","1.jpg","2.jpg","1.jpg","4.jpg","5.jpg","6.jpg"];
var images = new Array("lms/imges/n.png","lms/imges/aaa.png","lms/imges/1.png",);
var myVar = setInterval(changepic, 3000);

function changepic(){
x++;
if(x == images.length)
x=0;
image = document.getElementById("pic");
image.src = images[x];

} 
