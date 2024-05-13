var slider = document.getElementById("myRange");
var output = document.getElementById("value");
output.innerHTML = slider.value;

slider.oninput = function() {
  output.innerHTML = this.value;
  const allowedValues = [0, 5, 10, 15, 20, 25, 30, 40, 50, 75, 100];
  const slider = document.getElementById("myRange");
  const output = document.getElementById("value");
}
function onSubmitForm() {
var distanceValue = document.getElementById('myRange').value;
console.log('Distance sélectionnée : ' + distanceValue)
return true; 
};