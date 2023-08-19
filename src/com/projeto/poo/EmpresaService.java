package com.projeto.poo;

import javax.swing.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class EmpresaService {
    public static Map<Key,Empresa> empresas = new HashMap<>();
    public static Map<String,Empresa> empresasUsers = new HashMap<>();

    private ConnectionFactory connection;

    public EmpresaService() {
        this.connection = new ConnectionFactory();
    }

    public void carregaEmpresas(){
        Connection conn = connection.recuperarConexao();
        empresasUsers = new ContasDAO(conn).carregarEmpresas();
        new ContasDAO(conn).carregarVagas();

        for (Map.Entry<String, Empresa> entry : empresasUsers.entrySet()){
            String chave = entry.getKey();
            Empresa empresa = entry.getValue();

            empresas.put(empresa.getKey(), empresa);
        }
    }

    public void cadastraEmpresa(String nome,String email,String pais,String estado,String cep,String sobre,String cnpj,String username,String senha) throws LinketinderException {

        Key chaveTeste = new Key(username,cnpj);

        if(empresas.get(chaveTeste) == null) {

            Empresa novaEmpresa = new Empresa(nome, email, pais, estado, cep, sobre, cnpj, username, senha);

            Connection conn = connection.recuperarConexao();
            new ContasDAO(conn).cadastrarNovaEmpresa(novaEmpresa);

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

    public static void deletaEmpresa(String username, String cnpj){

        ConnectionFactory connection = new ConnectionFactory();
        Connection conn = connection.recuperarConexao();
        new ContasDAO(conn).deletarEmpresa(empresasUsers.get(username));

        empresasUsers.remove(username);
        empresas.remove(new Key<>(username,cnpj));
        System.out.println("Removido");
    }

    public static void cadastraVaga(Vaga vaga){
        ConnectionFactory connection = new ConnectionFactory();
        Connection conn = connection.recuperarConexao();
        new ContasDAO(conn).cadastrarVaga(vaga);
    }

    public static void editaVaga(Vaga vaga){
        ConnectionFactory connection = new ConnectionFactory();
        Connection conn = connection.recuperarConexao();
        new ContasDAO(conn).editarVaga(vaga);
    }

    public static void excluiVaga(Vaga vaga){
        ConnectionFactory connection = new ConnectionFactory();
        Connection conn = connection.recuperarConexao();
        new ContasDAO(conn).excluirVaga(vaga);
    }

    public static void gravaCurtida(Empresa empresa, Candidato candidato){
        ConnectionFactory connection = new ConnectionFactory();
        Connection conn = connection.recuperarConexao();
        new ContasDAO(conn).gravarCurtida(candidato, empresa, 1);
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
