package com.projeto.poo;

import java.util.LinkedList;
import java.util.UUID;

public class Empresa extends Usuario implements Conexao {
	private static int quantidadeEmpresas;
	private int id;
	private String cnpj;
    private LinkedList<Vaga> vagas;

	public Empresa(String nome, String email, String pais, String estado, String cep, String descricao, String cnpj, String username, String senha) {
		super(nome, email, pais, estado, cep, descricao, username, senha);
		super.gravaChaves(username,cnpj);
		setId();
		this.cnpj = cnpj;
		this.vagas = new LinkedList<>();
	}

	public static int quantidadeEmpresas() {
		return quantidadeEmpresas;
	}
	
	//utilizado nos testes do JUnit
	public static void zeraQuantidadeEmpresas() {
		quantidadeEmpresas = 0;
	}

	public void setId() {
		String uuid = UUID.randomUUID().toString().substring(0, 5);

		int numericValue = 0;
		for (char c : uuid.toCharArray()) {
			numericValue = numericValue + (int) c;
		}

		// Converter a string concatenada em um número inteiro
		this.id = numericValue;
		System.out.println(id);
	}

	public void setIdBd(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getCnpj() {
		return cnpj;
	}
	
	public Vaga cadastraVaga(String nome, String tipo, double salario, String definicao, Empresa empresa, boolean disponivel) {
		Vaga vaga = new Vaga(nome, tipo, salario, definicao, this, disponivel);
		this.vagas.add(vaga);
		return vaga;
	}

	public void excluirVaga(Vaga vaga){
		vagas.remove(vaga);
	}
	
	public LinkedList<Vaga> listaDeVagas(){
		return vagas;
	}

	@Override
	public void curtir(Usuario user) {
		if(user instanceof Candidato) {
			String cpf = ((Candidato) user).getCpf();
			super.adicionaCurtida(cpf, user);
			System.out.println("Curtida registrada!");
			
			if(verificarMatch(user)) {
				System.out.println("It's a match!");
				gravarMatch(user);
			}
		}		
	}

	@Override
	public boolean verificarMatch(Usuario user) {
		if(user instanceof Candidato) {
			Empresa empresa = (Empresa)user.getCurtidas().get(this.getCnpj());

			return empresa != null;
		}
		return false;
	}

	@Override
	public void gravarMatch(Usuario user) {
		if(verificarMatch(user)) {
			super.adicionaMatch(user);
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
