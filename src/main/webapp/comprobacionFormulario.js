function myFunction() {
  var x, text,formulario;

  // Get the value of the input field with id="numb"
  x = document.getElementById("superId").value;

  // If x is Not a Number or less than one or greater than 10
  if (isNaN(x)) {
    alert("Solo puedes ingresar un número en el ID, por favor cambialo");
    return false;

  }

}