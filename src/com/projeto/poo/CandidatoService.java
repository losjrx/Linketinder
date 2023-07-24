package com.projeto.poo;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class CandidatoService {
    public static Map<String,Candidato> candidatos = new HashMap<>();

    public void cadastraCandidato(String nome,String email,String pais,String estado,String cep,String sobre,String username,String senha,int idade,String  cpf) throws LinketinderException {

        if(candidatos.get(cpf) == null) {

            candidatos.put(cpf, new Candidato(nome, email, pais, estado, cep, sobre, username, senha, idade, cpf));

            System.out.println("Cadastro efetuado");

        } else {
            JOptionPane.showMessageDialog(null, "Candidato com este CPF já cadastrado.","Atenção",JOptionPane.WARNING_MESSAGE);
            throw new LinketinderException("Candidato já cadastrado.");
        }
    }
    public Candidato getCandidato(String cpf){
        return candidatos.get(cpf);
    }

    public void deletaCandidato(String cpf){
        candidatos.remove(cpf);
        System.out.println("Removido");
    }
}
