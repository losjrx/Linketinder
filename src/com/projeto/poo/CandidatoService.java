package com.projeto.poo;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class CandidatoService {
    public static Map<Key,Candidato> candidatos = new HashMap<>();
    public static Map<String,Candidato> candidatosUsers = new HashMap<>();

    public void cadastraCandidato(String nome,String email,String pais,String estado,String cep,String sobre,String username,String senha,int idade,String  cpf) throws LinketinderException {

        Key chaveTeste = new Key(username, cpf);

        if(candidatos.get(chaveTeste) == null) {

            Candidato novoCandidato = new Candidato(nome, email, pais, estado, cep, sobre, username, senha, idade, cpf);

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

    public void deletaCandidato(String username, String cpf){
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
