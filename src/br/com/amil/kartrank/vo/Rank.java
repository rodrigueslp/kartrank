package br.com.amil.kartrank.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;


public class Rank {
	private int posicao;
	private Piloto piloto;
	private int totalVoltas;
	private LocalTime tempoTotal;
	
	private LocalTime melhorVoltaCadaPiloto;
	private double velocidadeMediaNaCorrida;
	private LocalTime tempoAposVencedor;
	
	
	public Piloto getPiloto() {
		return piloto;
	}
	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}
	public int getPosicao() {
		return posicao;
	}
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	public int getTotalVoltas() {
		return totalVoltas;
	}
	public void setTotalVoltas(int totalVoltas) {
		this.totalVoltas = totalVoltas;
	}
	public LocalTime getTempoTotal() {
		return tempoTotal;
	}
	public void setTempoTotal(LocalTime tempoTotal) {
		this.tempoTotal = tempoTotal;
	}
	
	public LocalTime getMelhorVoltaCadaPiloto() {
		return melhorVoltaCadaPiloto;
	}
	
	public void setMelhorVoltaCadaPiloto(LocalTime melhorVoltaCadaPiloto) {
		this.melhorVoltaCadaPiloto = melhorVoltaCadaPiloto;
	}
	
	public double getVelocidadeMediaNaCorrida() {
		return velocidadeMediaNaCorrida;
	}

	public void setVelocidadeMediaNaCorrida(double velocidadeMediaNaCorrida) {
		BigDecimal bgVelocidadeMediaCorrida = new BigDecimal(velocidadeMediaNaCorrida).setScale(3, RoundingMode.CEILING);
		this.velocidadeMediaNaCorrida = bgVelocidadeMediaCorrida.doubleValue();
	}
	
	public LocalTime getTempoAposVencedor() {
		return tempoAposVencedor;
	}
	
	public void setTempoAposVencedor(LocalTime tempoAposVencedor) {
		this.tempoAposVencedor = tempoAposVencedor;
	}
}