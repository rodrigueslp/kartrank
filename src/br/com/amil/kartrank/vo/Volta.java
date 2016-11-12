package br.com.amil.kartrank.vo;

import java.time.LocalTime;

public class Volta {
	private LocalTime hora;
	private Piloto piloto;
	private int numeroVolta;
	private LocalTime tempo;
	private double velocidadeMedia;
	
	
	public Volta(){}
	
	public Volta(LocalTime hora, Piloto p, int numeroVolta,  LocalTime tempo, double velocidadeMedia){
		this.hora = hora;
		this.piloto = p;
		this.numeroVolta = numeroVolta;
		this.tempo = tempo;
		this.velocidadeMedia = velocidadeMedia;
	}
	
	public Piloto getPiloto() {
		return piloto;
	}
	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}
	public LocalTime getTempo() {
		return tempo;
	}
	public void setTempo(LocalTime tempo) {
		this.tempo = tempo;
	}
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public int getNumeroVolta() {
		return numeroVolta;
	}

	public void setNumeroVolta(int numeroVolta) {
		this.numeroVolta = numeroVolta;
	}

	public double getVelocidadeMedia() {
		return velocidadeMedia;
	}

	public void setVelocidadeMedia(double velocidadeMedia) {
		this.velocidadeMedia = velocidadeMedia;
	}
}