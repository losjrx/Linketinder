package com.projeto.poo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ContasDAO {

    private Connection conn;

    ContasDAO(Connection connection){
        this.conn = connection;
    }

    public void cadastrarNovoCandidato(Candidato novoCandidato){

        String sql = "INSERT INTO USUARIO (id_user, id_type, nome, email, pais, estado, descricao, username, senha)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String sql2 = "INSERT INTO CANDIDATO (id_candidato, idade, cpf) VALUES (?, ?, ?)";

        String sql3 = "INSERT INTO CURRICULO (id_candidato, formacao, cursos_complementares, pretensao_salarial)"+
                "VALUES (?, ?, ?, ?)";

        try{
            PreparedStatement preparedStatementUsuario = conn.prepareStatement(sql);
            preparedStatementUsuario.setInt(1, novoCandidato.getId());
            preparedStatementUsuario.setInt(2,2);
            preparedStatementUsuario.setString(3, novoCandidato.getNome());
            preparedStatementUsuario.setString(4, novoCandidato.getEmail());
            preparedStatementUsuario.setString(5,novoCandidato.getPais());
            preparedStatementUsuario.setString(6,novoCandidato.getEstado());
            preparedStatementUsuario.setString(7,novoCandidato.getDescricao());
            preparedStatementUsuario.setString(8,novoCandidato.getUsername());
            preparedStatementUsuario.setString(9,novoCandidato.getSenha());

            preparedStatementUsuario.execute();

            PreparedStatement preparedStatementCandidato = conn.prepareStatement(sql2);
            preparedStatementCandidato.setInt(1, novoCandidato.getId());
            preparedStatementCandidato.setInt(2, novoCandidato.getIdade());
            preparedStatementCandidato.setString(3, novoCandidato.getCpf());

            preparedStatementCandidato.execute();

            PreparedStatement preparedStatementCurriculo = conn.prepareStatement(sql3);
            preparedStatementCurriculo.setInt(1, novoCandidato.getId());
            preparedStatementCurriculo.setString(2, novoCandidato.getCurriculo().getFormacao());
            preparedStatementCurriculo.setString(3, novoCandidato.getCurriculo().getCursosComplementares());
            preparedStatementCurriculo.setDouble(4, novoCandidato.getCurriculo().getPretensaoSalarial());
            preparedStatementCurriculo.execute();

            preparedStatementUsuario.close();
            preparedStatementCandidato.close();
            preparedStatementCurriculo.close();
            conn.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void cadastrarNovaEmpresa(Empresa novaEmpresa){
//String nome, String email, String pais, String estado, String cep, String descricao, String username, String senha, int idade,
//			String cpf
        String sql = "INSERT INTO USUARIO (id_user, id_type, nome, email, pais, estado, cep, descricao, username, senha)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String sql2 = "INSERT INTO EMPRESA (id_empresa, cnpj) VALUES (?, ?)";

        try{
            PreparedStatement preparedStatementUsuario = conn.prepareStatement(sql);
            preparedStatementUsuario.setInt(1, novaEmpresa.getId());
            preparedStatementUsuario.setInt(2,1);
            preparedStatementUsuario.setString(3, novaEmpresa.getNome());
            preparedStatementUsuario.setString(4, novaEmpresa.getEmail());
            preparedStatementUsuario.setString(5, novaEmpresa.getPais());
            preparedStatementUsuario.setString(6, novaEmpresa.getEstado());
            preparedStatementUsuario.setString(7, novaEmpresa.getCep());
            preparedStatementUsuario.setString(8, novaEmpresa.getDescricao());
            preparedStatementUsuario.setString(9, novaEmpresa.getUsername());
            preparedStatementUsuario.setString(10, novaEmpresa.getSenha());

            preparedStatementUsuario.execute();

            PreparedStatement preparedStatementEmpresa = conn.prepareStatement(sql2);
            preparedStatementEmpresa.setInt(1, novaEmpresa.getId());
            preparedStatementEmpresa.setString(2, novaEmpresa.getCnpj());

            preparedStatementEmpresa.execute();

            preparedStatementUsuario.close();
            preparedStatementEmpresa.close();
            conn.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Map<String,Candidato> carregarCandidatos(){
        PreparedStatement psUsuario;
        PreparedStatement psCandidato;
        PreparedStatement psCurriculo;
        ResultSet resultSetUsuario;
        ResultSet resultSetCandidato;
        ResultSet resultSetCurriculo;

        Map<String,Candidato> candidatos = new HashMap<>();

        String sql = "SELECT * FROM usuario";
        String sql2 = "SELECT * FROM candidato";
        String sql3 = "SELECT * FROM curriculo";

        try{
            psUsuario = conn.prepareStatement(sql);
            psCandidato = conn.prepareStatement(sql2);
            psCurriculo = conn.prepareStatement(sql3);
            resultSetUsuario = psUsuario.executeQuery();
            resultSetCandidato = psCandidato.executeQuery();
            resultSetCurriculo = psCurriculo.executeQuery();

            while (resultSetUsuario.next() && resultSetCandidato.next() && resultSetCurriculo.next()){
                int id_user = resultSetUsuario.getInt(1);
                int id_type = resultSetUsuario.getInt(2);
                String nome = resultSetUsuario.getString(3);
                String email = resultSetUsuario.getString(4);
                String pais = resultSetUsuario.getString(5);
                String estado = resultSetUsuario.getString(6);
                String cep = resultSetUsuario.getString(7);
                String descricao = resultSetUsuario.getString(8);
                String username = resultSetUsuario.getString(9);
                String senha = resultSetUsuario.getString(10);

                int id_candidato = resultSetCandidato.getInt(1);
                int idade = resultSetCandidato.getInt(2);
                String cpf = resultSetCandidato.getString(3);

                int idcandidato = resultSetCurriculo.getInt(1);
                String formacao = resultSetCurriculo.getString(2);
                String cursos = resultSetCurriculo.getString(3);
                double pretensaoSalarial = resultSetCurriculo.getDouble(4);

                Candidato candidato = new Candidato(nome, email, pais, estado, cep, descricao, username, senha, idade, cpf);
                candidato.cadastraCurriculo(formacao,cursos,pretensaoSalarial);

                candidatos.put(username,candidato);
            }

            resultSetUsuario.close();
            psUsuario.close();

            resultSetCandidato.close();
            psCandidato.close();

            conn.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return candidatos;
    }
}
