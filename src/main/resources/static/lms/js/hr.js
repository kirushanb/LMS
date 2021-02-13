var z = 0;
var color = ["rgba(47,143,14,.60)","rgba(4,17,174,.60)"];
var myvar = setInterval(colors, 5000);

function colors(){
z++;
if(z == color.length)
z = 0;
document.getElementById("col").style.backgroundColor = color[z];
}

