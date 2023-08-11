package com.projeto.poo;

public class Vaga {
	static int contadorVagas = 0;
	private int id;
	private String nome;
	private String tipo;
	private double salario;
	private String definicao;
	private boolean disponivel;
	private Empresa empresa;
	
	public Vaga(String nome, String tipo, double salario, String definicao, Empresa empresa, boolean disponivel) {
		this.id = ++contadorVagas;
		this.nome = nome;
		this.tipo = tipo;
		this.salario = salario;
		this.definicao = definicao;
		this.empresa = empresa;
		this.disponivel = disponivel;
	}
	
	public static int totalVagasCadastradas(){
		return contadorVagas;
	}

	public String getNome(){
		return nome;
	}

	public String getTipo(){
		return tipo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public boolean isDisponivel(){
		return disponivel;
	}

	@Override
	public String toString() {
		return "Vaga [id=" + id + ", nome=" + nome + ", tipo=" + tipo + ", salario=" + salario + ", definicao="
				+ definicao + ", disponivel=" + disponivel + "]";
	}
}
