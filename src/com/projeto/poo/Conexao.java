package com.projeto.poo;

public interface Conexao {

	public void curtir(Usuario user);
	
	public boolean verificarMatch(Usuario user);
	
	public void gravarMatch(Usuario user);
}
