package com.projeto.poo;

import javax.swing.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class CandidatoService {
    public static Map<Key,Candidato> candidatos = new HashMap<>();
    public static Map<String,Candidato> candidatosUsers = new HashMap<>();

    private ConnectionFactory connection;

    public CandidatoService() {
        this.connection = new ConnectionFactory();
    }

    public void carregaCandidatos(){
        Connection conn = connection.recuperarConexao();
        candidatosUsers = new ContasDAO(conn).carregarCandidatos();

        for (Map.Entry<String, Candidato> entry : candidatosUsers.entrySet()){
            String chave = entry.getKey();
            Candidato candidato = entry.getValue();

            candidatos.put(candidato.getKey(), candidato);
        }

    }

    public void carregaCurtidas(){
        Connection conn = connection.recuperarConexao();
        new ContasDAO(conn).carregarCurtidas();
    }

    public void cadastraCandidato(String nome,String email,String pais,String estado,String cep,String sobre,String username,String senha,int idade,String  cpf, String formacao, String cursos, double pretensaoSalarial) throws LinketinderException {

        Key chaveTeste = new Key(username, cpf);

        if(candidatos.get(chaveTeste) == null) {

            Candidato novoCandidato = new Candidato(nome, email, pais, estado, cep, sobre, username, senha, idade, cpf);
            novoCandidato.cadastraCurriculo(formacao,cursos,pretensaoSalarial);

            Connection conn = connection.recuperarConexao();
            new ContasDAO(conn).cadastrarNovoCandidato(novoCandidato);

            candidatos.put(novoCandidato.getKey(),novoCandidato);

            candidatosUsers.put(username,novoCandidato);

            System.out.println("Cadastro efetuado");

        } else {
            JOptionPane.showMessageDialog(null, "Candidato com este CPF ou Username já cadastrado.","Atenção",JOptionPane.WARNING_MESSAGE);
            throw new LinketinderException("Candidato já cadastrado.");
        }
    }
    public Candidato getCandidato(String username, String cpf){

        return candidatos.get(new Key(username,cpf));
    }

    public static void editaCurriculo(Candidato candidato, String formacao, String cursos, double pretSalarial){
        candidato.getCurriculo().setFormacao(formacao);
        candidato.getCurriculo().setCursosComplementares(cursos);
        candidato.getCurriculo().setPretensaoSalarial(pretSalarial);

        ConnectionFactory connection = new ConnectionFactory();
        Connection conn = connection.recuperarConexao();
        new ContasDAO(conn).editarCurriculo(candidatosUsers.get(candidato.getUsername()));
    }

    public static void gravaCurtida(Candidato candidato, Empresa empresa){
        ConnectionFactory connection = new ConnectionFactory();
        Connection conn = connection.recuperarConexao();
        new ContasDAO(conn).gravarCurtida(candidato, empresa, 2);
    }

    public static void deletaCandidato(String username, String cpf){

        ConnectionFactory connection = new ConnectionFactory();
        Connection conn = connection.recuperarConexao();
        new ContasDAO(conn).deletarCandidato(candidatosUsers.get(username));

        candidatosUsers.remove(username);
        candidatos.remove(new Key(username,cpf));

        System.out.println("Removido");
    }

    public static Candidato autenticacaoCandidato(String username, String password){
        Candidato candidatoLogin = candidatosUsers.get(username);
        if(candidatoLogin == null){
            return null;
        }

        Candidato candidato = candidatos.get(candidatoLogin.getKey());

        if(candidato.getSenha().equals(password)){
            return candidato;
        }

        return null;
    }
}
