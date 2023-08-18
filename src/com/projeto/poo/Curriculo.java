package com.projeto.poo;

public class Curriculo {
	private String formacao;
	private String cursosComplementares;
	private double pretensaoSalarial;
	
	public Curriculo(String formacao, String cursosComplementares, double pretensaoSalarial) {
		this.formacao = formacao;
		this.cursosComplementares = cursosComplementares;
		this.pretensaoSalarial = pretensaoSalarial;
	}

	public void setFormacao(String formacao){
		this.formacao = formacao;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setCursosComplementares(String cursosComplementares){
		this.cursosComplementares = cursosComplementares;
	}

	public String getCursosComplementares() {
		return cursosComplementares;
	}

	public void setPretensaoSalarial(double pretensaoSalarial){
		this.pretensaoSalarial = pretensaoSalarial;
	}

	public double getPretensaoSalarial() {
		return pretensaoSalarial;
	}

	@Override
	public String toString() {
		return "Curriculo [formacao=" + formacao + ", cursosComplementares=" + cursosComplementares
				+ ", pretensaoSalarial=" + pretensaoSalarial + "]";
	}
}
