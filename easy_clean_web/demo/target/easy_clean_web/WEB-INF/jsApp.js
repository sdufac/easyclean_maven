var slider = document.getElementById("myRange");
var output = document.getElementById("value");
output.innerHTML = slider.value;

slider.oninput = function() {
  output.innerHTML = this.value;
}
function onSubmitForm() {
var distanceValue = document.getElementById('myRange').value;
console.log('Distance sélectionnée : ' + distanceValue)
return true; 
};