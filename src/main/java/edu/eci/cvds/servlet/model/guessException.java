package edu.eci.cvds.servlet.model;

public class guessException extends Exception {
	final static String YA_GANO = "El jugador ya ganó el juego";
	final static String YA_PERDIO = "El jugador ya perdió el juego";
	

	public guessException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}



}
