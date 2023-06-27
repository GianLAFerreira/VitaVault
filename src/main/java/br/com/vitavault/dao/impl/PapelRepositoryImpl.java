package br.com.vitavault.dao.impl;

import br.com.vitavault.dao.ConexaoBD;
import br.com.vitavault.dao.PapelRepository;
import br.com.vitavault.domain.EnumNivelAcesso;
import br.com.vitavault.model.Papel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import static br.com.vitavault.dao.ConexaoBD.conectar;

public class PapelRepositoryImpl implements PapelRepository {
    public PapelRepositoryImpl() {
        System.out.println("Criando tabela");
        createTable();
    }

    @Override
    public void createTable() {
        Connection connection = ConexaoBD.conectar();
        String sqlCreate = "CREATE TABLE IF NOT EXISTS Papel ("
                + "id UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
                + "nivelAcesso  VARCHAR(255),"
                + "descricao  TEXT)";

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
    public boolean gravar(Papel papel) {
        String sql = "INSERT INTO papel(nivelAcesso, descricao) VALUES(?, ?)";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, papel.getNivelAcesso().toString());
            pstmt.setString(2, papel.getDescricao());

            pstmt.executeUpdate();

            ResultSet resultSet = pstmt.getGeneratedKeys();

            if (resultSet.next()) {
                UUID id = (UUID) resultSet.getObject(1);
                papel.setId(id);
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
    public Papel buscarPapel(UUID id) {
        Papel papel = null;
        String sql = "SELECT * FROM papel WHERE id = ?";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                UUID idcoluna = UUID.fromString(rs.getString("id"));
                String nivelAcesso = rs.getString("nivelAcesso");
                String descricao = rs.getString("descricao");

                papel = new Papel(idcoluna, EnumNivelAcesso.valueOf(nivelAcesso), descricao);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            ConexaoBD.descontecar();
        }
        System.out.println("Papel encontrado com sucesso");
        return papel;
    }

    @Override
    public boolean removerPapel(UUID id) {
        String sql = "DELETE FROM papel WHERE id = ?";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, id);
            pstmt.execute();

            System.out.println("Papel removido com sucesso");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoBD.descontecar();
        }
    }

}
