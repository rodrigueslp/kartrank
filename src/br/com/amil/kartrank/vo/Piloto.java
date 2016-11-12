package br.com.amil.kartrank.vo;

public class Piloto{
	private String id;
	private String nome;
	
	
	public Piloto(){}
	
	public Piloto(String id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Integer.valueOf(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		Piloto p = (Piloto) obj;
		return this.id.equals(p.getId());
	}
}