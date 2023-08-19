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

        String sql = "INSERT INTO USUARIO (id_user, id_type, nome, email, pais, estado, cep, descricao, username, senha)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            preparedStatementUsuario.setString(7,novoCandidato.getCep());
            preparedStatementUsuario.setString(8,novoCandidato.getDescricao());
            preparedStatementUsuario.setString(9,novoCandidato.getUsername());
            preparedStatementUsuario.setString(10,novoCandidato.getSenha());

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

        String sql = "select * from usuario where id_type = 2 order by id_user;";
        String sql2 = "SELECT * FROM candidato order by id_candidato";
        String sql3 = "SELECT * FROM curriculo order by id_candidato";

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
                candidato.setIdBd(id_candidato);
                candidato.cadastraCurriculo(formacao,cursos,pretensaoSalarial);

                candidatos.put(username,candidato);
            }

            resultSetUsuario.close();
            psUsuario.close();

            resultSetCandidato.close();
            psCandidato.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return candidatos;
    }

    public Map<String,Empresa> carregarEmpresas(){
        PreparedStatement psUsuario;
        PreparedStatement psEmpresa;
        ResultSet resultSetUsuario;
        ResultSet resultSetEmpresa;

        Map<String,Empresa> empresas = new HashMap<>();

        String sql = "select * from usuario where id_type = 1 order by id_user;";
        String sql2 = "SELECT * FROM empresa order by id_empresa";

        try{
            psUsuario = conn.prepareStatement(sql);
            psEmpresa = conn.prepareStatement(sql2);
            resultSetUsuario = psUsuario.executeQuery();
            resultSetEmpresa = psEmpresa.executeQuery();

            while (resultSetUsuario.next() && resultSetEmpresa.next()){
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

                int id_empresa = resultSetEmpresa.getInt(1);
                String cnpj = resultSetEmpresa.getString(2);

                Empresa empresa = new Empresa(nome, email, pais, estado, cep, descricao, cnpj, username, senha);
                empresa.setIdBd(id_empresa);

                empresas.put(username,empresa);
            }

            resultSetUsuario.close();
            psUsuario.close();

            resultSetEmpresa.close();
            psEmpresa.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return empresas;
    }

    public void carregarCurtidas(){
        for (Map.Entry<String, Empresa> entry : EmpresaService.empresasUsers.entrySet()) {
            try{
                String chave = entry.getKey();
                Empresa empresa = entry.getValue();

                String sqlSelectCurtidas = "SELECT * FROM CURTIDA WHERE id_user = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectCurtidas);
                preparedStatement.setInt(1, empresa.getId());
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    int idCurtido = resultSet.getInt("id_user_curtido");

                    for (Map.Entry<String, Candidato> entry2 : CandidatoService.candidatosUsers.entrySet()){
                        Candidato candidato = entry2.getValue();
                        if(candidato.getId() == idCurtido){
                            empresa.curtir(candidato);
                        }
                    }
                }

                preparedStatement.close();

            } catch (SQLException e){
                throw new RuntimeException(e);
            }
        }

        for (Map.Entry<String, Candidato> entry : CandidatoService.candidatosUsers.entrySet()) {
            try{
                String chave = entry.getKey();
                Candidato candidato = entry.getValue();

                String sqlSelectCurtidas = "SELECT * FROM CURTIDA WHERE id_user = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectCurtidas);
                preparedStatement.setInt(1, candidato.getId());
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    int idCurtido = resultSet.getInt("id_user_curtido");

                    for (Map.Entry<String, Empresa> entry2 : EmpresaService.empresasUsers.entrySet()){
                        Empresa empresa = entry2.getValue();
                        if(empresa.getId() == idCurtido){
                            candidato.curtir(empresa);
                        }
                    }
                }

                preparedStatement.close();

            } catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }

    public void deletarCandidato(Candidato candidato){
        String sqlDeleteCurtidas = "DELETE FROM CURTIDA WHERE id_user = ? OR id_user_curtido = ?";
        String sqlDeleteCurriculo = "DELETE FROM CURRICULO WHERE id_candidato = ?";
        String sqlDeleteCandidato = "DELETE FROM CANDIDATO WHERE id_candidato = ?";
        String sqlDeleteUsuario = "DELETE FROM USUARIO WHERE id_user = ?";

        PreparedStatement psCurtidas;
        PreparedStatement psCurriculo;
        PreparedStatement psCandidato;
        PreparedStatement psUsuario;

        try{
            psCurtidas = conn.prepareStatement(sqlDeleteCurtidas);
            psCurtidas.setInt(1, candidato.getId());
            psCurtidas.setInt(2, candidato.getId());
            psCurtidas.executeUpdate();

            psCurriculo = conn.prepareStatement(sqlDeleteCurriculo);
            psCurriculo.setInt(1, candidato.getId());
            psCurriculo.executeUpdate();

            psCandidato = conn.prepareStatement(sqlDeleteCandidato);
            psCandidato.setInt(1, candidato.getId());
            psCandidato.executeUpdate();

            psUsuario = conn.prepareStatement(sqlDeleteUsuario);
            psUsuario.setInt(1, candidato.getId());
            psUsuario.executeUpdate();

            psCurtidas.close();
            psCurriculo.close();
            psCandidato.close();
            psUsuario.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deletarEmpresa(Empresa empresa){
        String sqlDeleteCurtidas = "DELETE FROM CURTIDA WHERE id_user = ? OR id_user_curtido = ?";
        String sqlDeleteVagas = "DELETE FROM VAGA WHERE id_empresa = ?";
        String sqlDeleteEmpresa = "DELETE FROM EMPRESA WHERE id_empresa = ?";
        String sqlDeleteUsuario = "DELETE FROM USUARIO WHERE id_user = ?";

        PreparedStatement psCurtidas;
        PreparedStatement psDeleteVagas;
        PreparedStatement psEmpresa;
        PreparedStatement psUsuario;

        try{
            psCurtidas = conn.prepareStatement(sqlDeleteCurtidas);
            psCurtidas.setInt(1, empresa.getId());
            psCurtidas.setInt(2, empresa.getId());
            psCurtidas.executeUpdate();

            psDeleteVagas = conn.prepareStatement(sqlDeleteVagas);
            psDeleteVagas.setInt(1, empresa.getId());
            psDeleteVagas.executeUpdate();

            psEmpresa = conn.prepareStatement(sqlDeleteEmpresa);
            psEmpresa.setInt(1, empresa.getId());
            psEmpresa.executeUpdate();

            psUsuario = conn.prepareStatement(sqlDeleteUsuario);
            psUsuario.setInt(1, empresa.getId());
            psUsuario.executeUpdate();

            psCurtidas.close();
            psDeleteVagas.close();
            psEmpresa.close();
            psUsuario.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void cadastrarVaga(Vaga vaga){
        String sqlInsertVaga = "INSERT INTO VAGA (id_vaga, id_empresa, nome, tipo, salario, definicao, disponivel)"+
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement psVaga;

        try{
            psVaga = conn.prepareStatement(sqlInsertVaga);

            psVaga.setInt(1, vaga.getId());
            psVaga.setInt(2, vaga.getEmpresa().getId());
            psVaga.setString(3, vaga.getNome());
            psVaga.setString(4, vaga.getTipo());
            psVaga.setDouble(5, vaga.getSalario());
            psVaga.setString(6, vaga.getDefinicao());
            psVaga.setBoolean(7, vaga.isDisponivel());

            psVaga.executeUpdate();

            psVaga.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void carregarVagas(){

        for (Map.Entry<String, Empresa> entry : EmpresaService.empresasUsers.entrySet()) {
            try{
                String chave = entry.getKey();
                Empresa empresa = entry.getValue();

                String sqlSelectVaga = "SELECT * FROM VAGA WHERE id_empresa = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectVaga);
                preparedStatement.setInt(1, empresa.getId());
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    int idVaga = resultSet.getInt("id_vaga");
                    String nome = resultSet.getString("nome");
                    String tipo = resultSet.getString("tipo");
                    double salario = resultSet.getDouble("salario");
                    String definicao = resultSet.getString("definicao");
                    boolean disponivel = resultSet.getBoolean("disponivel");

                    Vaga vaga = empresa.cadastraVaga(nome,tipo,salario,definicao,empresa,disponivel);
                    vaga.setIdBd(idVaga);
                }

                preparedStatement.close();

            } catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }

    public void editarVaga(Vaga vaga){
        String sqlUpdateVaga = "UPDATE VAGA SET nome = ?, tipo = ?, salario = ?, definicao = ?, disponivel = ? WHERE id_vaga = ?";
        PreparedStatement psVaga;

        try {
            psVaga = conn.prepareStatement(sqlUpdateVaga);

            psVaga.setString(1, vaga.getNome());
            psVaga.setString(2, vaga.getTipo());
            psVaga.setDouble(3, vaga.getSalario());
            psVaga.setString(4, vaga.getDefinicao());
            psVaga.setBoolean(5, vaga.isDisponivel());
            psVaga.setInt(6, vaga.getId());
            psVaga.executeUpdate();

            psVaga.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void excluirVaga(Vaga vaga){
        try {
            String sqlDeleteVaga = "DELETE FROM VAGA WHERE id_vaga = ?";
            PreparedStatement preparedStatementVaga = conn.prepareStatement(sqlDeleteVaga);
            preparedStatementVaga.setInt(1, vaga.getId());
            preparedStatementVaga.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir vaga: " + e.getMessage());
        }
    }

    public void gravarCurtida(Candidato candidato, Empresa empresa, int tipo){

        int tipo_do_que_foi_curtido = (tipo == 1) ? 2 :1;
        int id_de_quem_curte = (tipo == 1) ? empresa.getId() : candidato.getId();
        int id_que_foi_curtido = (tipo == 1)? candidato.getId() : empresa.getId();

        String sql = "INSERT INTO CURTIDA (id_user, id_type, id_user_curtido, id_type_curtido) VALUES (?, ?, ?, ?)";

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, id_de_quem_curte);
            preparedStatement.setInt(2, tipo);

            preparedStatement.setInt(3, id_que_foi_curtido);
            preparedStatement.setInt(4, tipo_do_que_foi_curtido);

            preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException("Erro ao gravar curtida: " + e.getMessage());
        }
    }

    public void editarCurriculo(Candidato candidato){
        String sqlUpdateCurriculo = "UPDATE CURRICULO SET formacao = ?, cursos_complementares = ?, pretensao_salarial = ? WHERE id_candidato = ?";
        PreparedStatement psCurriculo;

        try {
            psCurriculo = conn.prepareStatement(sqlUpdateCurriculo);


            psCurriculo.setString(1, candidato.getCurriculo().getFormacao());
            psCurriculo.setString(2, candidato.getCurriculo().getCursosComplementares());
            psCurriculo.setDouble(3, candidato.getCurriculo().getPretensaoSalarial());
            psCurriculo.setInt(4, candidato.getId());

            psCurriculo.executeUpdate();

            psCurriculo.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
