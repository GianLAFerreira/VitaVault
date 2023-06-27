package br.com.vitavault.dao.impl;

import br.com.vitavault.dao.ConexaoBD;
import br.com.vitavault.dao.FuncionarioRepository;
import br.com.vitavault.model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static br.com.vitavault.dao.ConexaoBD.conectar;

public class FuncionarioRepositoryImpl implements FuncionarioRepository {

    public FuncionarioRepositoryImpl() {
        createTable();
    }

    @Override
    public void createTable() {
        Connection connection = ConexaoBD.conectar();
        String sqlCreate = "CREATE TABLE IF NOT EXISTS Funcionario ("
                + "id UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
                + "nome VARCHAR(255),"
                + "cpf VARCHAR(11),"
                + "endereco VARCHAR(255),"
                + "senha VARCHAR(255),"
                + "telefone VARCHAR(15))";

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute(sqlCreate);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConexaoBD.descontecar();
        }
    }

    @Override
    public boolean gravar(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario(nome, cpf, endereco, senha, telefone) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getCpf());
            pstmt.setString(3, funcionario.getEndereco());
            pstmt.setString(4, funcionario.getSenha());
            pstmt.setString(5, funcionario.getTelefone());

            pstmt.executeUpdate();

            ResultSet resultSet = pstmt.getGeneratedKeys();

            if (resultSet.next()) {
                UUID id = (UUID) resultSet.getObject(1);
                funcionario.setId(id);
            }
            System.out.println("Gravou com sucesso no banco");

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoBD.descontecar();
        }
    }

    @Override
    public Funcionario buscarFuncionario(UUID id) {
        Funcionario funcionario = null;
        String sql = "SELECT * FROM funcionario WHERE id = ?";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                UUID idcoluna = UUID.fromString(rs.getString("id"));
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String endereco = rs.getString("endereco");
                String senha = rs.getString("senha");
                String telefone = rs.getString("telefone");

                funcionario = new Funcionario(idcoluna, cpf, nome, endereco, senha, telefone);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            ConexaoBD.descontecar();
        }
        System.out.println("Funcionario encontrado com sucesso");
        return funcionario;
    }

    @Override
    public Funcionario buscarFuncionarioByCPF(String cpf) {
        Funcionario funcionario = null;
        String sql = "SELECT * FROM funcionario WHERE cpf = ?";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, cpf);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                UUID idcoluna = UUID.fromString(rs.getString("id"));
                String nome = rs.getString("nome");
                String cpfColuna = rs.getString("cpf");
                String endereco = rs.getString("endereco");
                String senha = rs.getString("senha");
                String telefone = rs.getString("telefone");

                funcionario = new Funcionario(idcoluna, cpfColuna, nome, endereco, senha, telefone);
                System.out.println("Funcionario encontrado com sucesso");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            ConexaoBD.descontecar();
        }
        return funcionario;
    }

    @Override
    public Funcionario buscarFuncionarioByCPFAndSenha(String cpf, String senha) {
        Funcionario funcionario = null;
        String sql = "SELECT * FROM funcionario WHERE cpf = ? and senha = ?";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, cpf);
            pstmt.setObject(2, senha);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                UUID idcoluna = UUID.fromString(rs.getString("id"));
                String nome = rs.getString("nome");
                String cpfColuna = rs.getString("cpf");
                String endereco = rs.getString("endereco");
                String senhaColuna = rs.getString("senha");
                String telefone = rs.getString("telefone");

                funcionario = new Funcionario(idcoluna, cpfColuna, nome, endereco, senhaColuna, telefone);
                System.out.println("Funcionario encontrado com sucesso");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            ConexaoBD.descontecar();
        }
        return funcionario;
    }


    @Override
    public boolean removerFuncionario(UUID id) {
        String sql = "DELETE FROM funcionario WHERE id = ?";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, id);
            pstmt.execute();

            System.out.println("Funcionario removido com sucesso");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoBD.descontecar();
        }
    }

    @Override
    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();

        String sql = "SELECT * FROM funcionario";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                UUID idcoluna = UUID.fromString(rs.getString("id"));
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String endereco = rs.getString("endereco");
                String senha = rs.getString("senha");
                String telefone = rs.getString("telefone");

                funcionarios.add(new Funcionario(idcoluna, cpf, nome, endereco, senha, telefone));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        } finally {
            ConexaoBD.descontecar();
        }
        return funcionarios;
    }
}
