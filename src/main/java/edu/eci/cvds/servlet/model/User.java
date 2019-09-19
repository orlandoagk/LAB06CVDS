package edu.eci.cvds.servlet.model;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;


@ManagedBean(name = "User")
@ApplicationScoped
public class User {
	private int randomNumber,intentosRealizados,premioAcum,intento,ronda;
	private String arregloIntentosRealizados;
	private String hayGanador;
	public User() {
		randomNumber = (int) (Math.random()*50+1);
		intentosRealizados = 0;
		premioAcum = 0;
		ronda = 1;
		hayGanador = "Ronda 1";
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
	
	public void guess(String texto) {
		try {
			int intento = Integer.parseInt(texto);
			this.guess(intento);
			cambioEstado();
		}catch(NumberFormatException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No puedes colocar texto, dejar vacio o colocar decimales, tienes que colocar un número entero!"));
		} catch (guessException e) {
			if (e.getMessage().equals(guessException.YA_GANO)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
						"Coronaste!", "Ganaste un total de $"+Integer.toString(premioAcum)+ "y atinaste "+Integer.toString(ronda-1)+" veces el número"));
				restart();
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Suerte en la proxima!", "Perdiste, vamos a reiniciar el juego pa que intentes de nuevo!"));
				restart();
			}
		}
	}
	
	private void cambioEstado() throws guessException {
		if (premioAcum >= 400000) {
			throw new guessException(guessException.YA_GANO);
		} else if (intentosRealizados == 10) {
			throw new guessException(guessException.YA_PERDIO);
		}
	}
	
	public void guess(int intento) {
		boolean puedesAvanzar = true;
		if (intento <= 0 || intento > 50) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Tiene que ser un número entre 1 y 50!"));
			puedesAvanzar = false;
		}
		String[] temporal = arregloIntentosRealizados.split(",");
		for (int i = 0; i<temporal.length;i++) {
			if (temporal[i].equals(Integer.toString(intento))) {
				puedesAvanzar = false;
			}
		}
		if (puedesAvanzar){
			if (intento == randomNumber) {
				this.setPremioAcum(premioAcum+100000);
				ronda++;
				hayGanador="Ronda "+Integer.toString(ronda);
				resetGano();
				
			}else {
				this.setPremioAcum(premioAcum-10000);
				if (intentosRealizados == 0) {
					arregloIntentosRealizados = Integer.toString(intento);
				}else {
					arregloIntentosRealizados= arregloIntentosRealizados+ ","+Integer.toString(intento);
				}
				intentosRealizados++;
			}
			
		}
	}
	
	public void restart() {
		randomNumber = (int) (Math.random()*50+1);
		this.intentosRealizados=0;
		this.premioAcum=0;
		this.hayGanador="Ronda 1";
		this.intento=0;
		arregloIntentosRealizados = "";
		this.ronda = 0;
	}
	
	public void resetGano() {
		randomNumber = (int) (Math.random()*50+1);
		arregloIntentosRealizados = "";
		intentosRealizados = 0;
	}
	
	public String getArregloIntentosRealizados() {
		return arregloIntentosRealizados;
	}
	
	
	
}
