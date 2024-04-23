var slider = document.getElementById("myRange");
var output = document.getElementById("value");
output.innerHTML = slider.value; // Affiche la valeur par défaut du slider

// Mise à jour de la valeur à chaque fois que vous faites glisser le slider
slider.oninput = function() {
  output.innerHTML = this.value;
}
