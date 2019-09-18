package edu.eci.cvds.servlet.model;

import java.util.ArrayList;

import javax.faces.bean.*;


@ManagedBean(name = "User")
@ApplicationScoped
public class User {
	private int randomNumber,intentosRealizados,premioAcum,intento;
	private String arregloIntentosRealizados;
	private String hayGanador;
	public User() {
		randomNumber = (int) (Math.random()*50+1);
		intentosRealizados = 0;
		premioAcum = 0;
		hayGanador = "No";
		arregloIntentosRealizados = "";
	
	}
	public int getIntento() {
		return intento;
	}
	public void setIntento(int intento) {
		this.intento = intento;
	}
	public int getRandomNumber() {
		return randomNumber;
	}
	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}
	public int getIntentosRealizados() {
		return intentosRealizados;
	}
	public void setPremioAcum(int premioAcum) {
		this.premioAcum = premioAcum;
	}
	public String getHayGanador() {
		return hayGanador;
	}
	public void setHayGanador(String hayGanador) {
		this.hayGanador = hayGanador;
	}
	
	public int getPremioAcum() {
		return premioAcum;
	}
	public void setIntentosRealizados(int intentosRealizados) {
		this.intentosRealizados = intentosRealizados;
	}
	public void guess(int intento) {
		if (!arregloIntentosRealizados.contains(Integer.toString(intento))){
			if (intento == randomNumber) {
				this.setPremioAcum(premioAcum+100000);
				hayGanador="Si";
				resetGano();
			}else {
				this.setPremioAcum(premioAcum-10000);
			}
			if (intentosRealizados == 0) {
				arregloIntentosRealizados = Integer.toString(intento);
			}else {
				arregloIntentosRealizados= arregloIntentosRealizados+ ","+Integer.toString(intento);
			}
			intentosRealizados++;
		}
	}
	
	public void restart() {
		randomNumber = (int) (Math.random()*50+1);
		this.intentosRealizados=0;
		this.premioAcum=0;
		this.hayGanador="No";
		this.intento=0;
		arregloIntentosRealizados = "";
	}
	public void resetGano() {
		randomNumber = (int) (Math.random()*50+1);
	}
	public String getArregloIntentosRealizados() {
		return arregloIntentosRealizados;
	}
	
	
	
}
