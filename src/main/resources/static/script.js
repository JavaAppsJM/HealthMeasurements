// Health Measurement Javascript
// Versie: 0.1

// Globals
//

window.onload = function(){
var eDivJavascript = document.getElementById('javascript');
var eElemNode = document.createElement('blockquote');
eElemNode.setAttribute("id","ideetje");
eElemNode.setAttribute("name","nametje");
eElemNode.setAttribute("class","classtje");
eElemNode.setAttribute("style","color:white");
eElemNode.innerHTML = "Javascript experiment";
eDivJavascript.appendChild(eElemNode);

}