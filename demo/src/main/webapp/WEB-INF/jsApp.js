var slider = document.getElementById("myRange");
var output = document.getElementById("value");
output.innerHTML = slider.value; // Affiche la valeur par défaut du slider

// Mise à jour de la valeur à chaque fois que vous faites glisser le slider
slider.oninput = function() {
  output.innerHTML = this.value;
  const allowedValues = [0, 5, 10, 15, 20, 25, 30, 40, 50, 75, 100];
  const slider = document.getElementById("myRange");
  const output = document.getElementById("value");
}
