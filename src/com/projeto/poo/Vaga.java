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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome(){
		return nome;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo(){
		return tipo;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public double getSalario() {
		return salario;
	}

	public void setDefinicao(String definicao) {
		this.definicao = definicao;
	}

	public String getDefinicao() {
		return definicao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
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
