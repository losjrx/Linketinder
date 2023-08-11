package com.projeto.poo;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class EmpresaService {
    public static Map<Key,Empresa> empresas = new HashMap<>();
    public static Map<String,Empresa> empresasUsers = new HashMap<>();

    public void cadastraEmpresa(String nome,String email,String pais,String estado,String cep,String sobre,String cnpj,String username,String senha) throws LinketinderException {

        Key chaveTeste = new Key(username,cnpj);

        if(empresas.get(chaveTeste) == null) {

            Empresa novaEmpresa = new Empresa(nome, email, pais, estado, cep, sobre, cnpj, username, senha);

            empresas.put(novaEmpresa.getKey(),novaEmpresa);

            empresasUsers.put(username,novaEmpresa);

            System.out.println("Cadastro efetuado");

        } else {
            JOptionPane.showMessageDialog(null, "Empresa com este username já cadastrado.","Atenção",JOptionPane.WARNING_MESSAGE);
            throw new LinketinderException("Empresa com este username já cadastrado.");
        }
    }
    public Empresa getEmpresa(String username, String cnpj) {
        return empresas.get(new Key<>(username,cnpj));
    }

    public void deletaEmpresa(String username, String cnpj){
        empresas.remove(new Key<>(username,cnpj));
        System.out.println("Removido");
    }

    public static Empresa autenticacaoEmpresa(String username, String password){
        Empresa empresaLogin = empresasUsers.get(username);
        if(empresaLogin == null){
            return null;
        }

        Empresa empresa = empresas.get(empresaLogin.getKey());

        if(empresa.getSenha().equals(password)){
            return empresa;
        }

        return null;
    }
}
