package com.projeto.poo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Usuario {
	private static int quantidadeUsuarios = 0;
	private String nome, email, pais, estado, cep, descricao;
	private String username, senha;
    private Map<String, Usuario> curtidas;
    private ArrayList<Usuario> matches;
    
	public Usuario(String nome, String email, String pais, String estado, String cep, String descricao, String username, String senha) {
		this.nome = nome;
		this.email = email;
		this.pais = pais;
		this.estado = estado;
		this.cep = cep;
		this.descricao = descricao;
		this.username = username;
		this.senha = senha;
		this.curtidas = new HashMap<>();
		this.matches = new ArrayList<>();
		quantidadeUsuarios++;
	}
	
	public static int quantidadeUsuarios() {
		return quantidadeUsuarios;
	}
	
	//utilizado nos testes do JUnit
		public static void zeraQuantidadeUsuarios() {
			quantidadeUsuarios = 0;
		}

	public Map<String, Usuario> getCurtidas() {
		return curtidas;
	}

	public void adicionaCurtida(String chave, Usuario user) {
		this.curtidas.put(chave, user);
	}

	public ArrayList<Usuario> getMatches() {
		return matches;
	}

	public void adicionaMatch(Usuario user) {
		this.matches.add(user);
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "[nome=" + nome + ", email=" + email + ", pais=" + pais + ", estado=" + estado + ", cep=" + cep
				+ ", descricao=" + descricao;
	}
}
