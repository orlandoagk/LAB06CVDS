function comprobarNumero() {
  var x;

  x = document.getElementById("intento").value;

  if (isNaN(x)) {
    alert("Solo puedes ingresar un número");

  }

}
comprobarNumero();