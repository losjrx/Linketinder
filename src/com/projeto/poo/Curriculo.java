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

	@Override
	public String toString() {
		return "Curriculo [formacao=" + formacao + ", cursosComplementares=" + cursosComplementares
				+ ", pretensaoSalarial=" + pretensaoSalarial + "]";
	}
}
