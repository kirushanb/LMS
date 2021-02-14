var i = 0;
var color = ["rgba(47,143,14,.60)","rgba(4,17,174,.60)"];
var myvar = setInterval(colors, 5000);

function colors(){
i++;
if(i == color.length)
i = 0;
document.getElementById("col2").style.backgroundColor = color[i];
}

