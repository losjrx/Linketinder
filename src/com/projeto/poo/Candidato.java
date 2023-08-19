package com.projeto.poo;

import java.util.ArrayList;
import java.util.UUID;

public class Candidato extends Usuario implements Conexao {
	private static int quantidadeCandidatos = 0;
	private	int id;
	private int idade;
    private String cpf;
    private Curriculo curriculo;
    private ArrayList<Vaga> vagasRequeridas;
    
	public Candidato(String nome, String email, String pais, String estado, String cep, String descricao, String username, String senha, int idade,
			String cpf) {
		super(nome, email, pais, estado, cep, descricao, username, senha);
		super.gravaChaves(username,cpf);
		setId();
		this.idade = idade;
		this.cpf = cpf;
		this.vagasRequeridas = new ArrayList<>();
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

	public int getId() {
		return id;
	}

	public int getIdade(){
		return idade;
	}

	public static int quantidadeCandidatos() {
		return quantidadeCandidatos;
	}
	
	//utilizado nos testes do JUnit
	public static void zeraQuantidadeCandidatos() {
		quantidadeCandidatos = 0;
	}

	public String getCpf() {
		return cpf;
	}
	
	public void cadastraCurriculo(String formacao, String cursosComplementares, double pretensaoSalarial) {
		this.curriculo = new Curriculo(formacao, cursosComplementares, pretensaoSalarial);
	}

	public Curriculo getCurriculo() {
		return curriculo;
	}

	public void insereVagaNaLista(Vaga vaga) {
		this.vagasRequeridas.add(vaga);
	}
	
	public ArrayList<Vaga> getVagas() {
		return this.vagasRequeridas;
	}

	@Override
	public void curtir(Usuario user) {
		if(user instanceof Empresa) {
			String cnpj = ((Empresa) user).getCnpj();
			super.adicionaCurtida(cnpj, user);
			System.out.println("Curtida registrada!");
			
			if(verificarMatch(user)) {
				System.out.println("It's a match!");
				gravarMatch(user);
			}
		}		
	}

	@Override
	public boolean verificarMatch(Usuario user) {
		if(user instanceof Empresa) {
			Candidato candidato = (Candidato)user.getCurtidas().get(this.getCpf());

			return candidato != null;
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
		return "Candidato " + super.toString() + " id=" + id + ", idade=" + idade + ", cpf=" + cpf + ", curriculo=" + curriculo + "]";
	}
	
	
}
