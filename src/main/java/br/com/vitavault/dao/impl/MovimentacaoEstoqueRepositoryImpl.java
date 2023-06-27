package br.com.vitavault.dao.impl;

import br.com.vitavault.dao.ConexaoBD;
import br.com.vitavault.dao.MovimentacaoEstoqueRepository;
import br.com.vitavault.domain.MovimentacaoEstoque;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static br.com.vitavault.dao.ConexaoBD.conectar;

public class MovimentacaoEstoqueRepositoryImpl implements MovimentacaoEstoqueRepository {
    public MovimentacaoEstoqueRepositoryImpl() {
        createTable();
    }

    @Override
    public void createTable() {
        Connection connection = ConexaoBD.conectar();
        String sqlCreate = "CREATE TABLE IF NOT EXISTS MovimentacaoEstoque ("
                + "id UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
                + "item UUID,"
                + "funcionario  UUID,"
                + "dataMovimentacao  DATE,"
                + "tipoMovimentacao  VARCHAR(255),"
                + "quantidade BIGINT,"
                + "FOREIGN KEY (item) REFERENCES ItemEstoque(id),"
                + "FOREIGN KEY (funcionario) REFERENCES funcionario(id))";

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
    public boolean gravar(MovimentacaoEstoque movimentacaoEstoque) {
        String sql = "INSERT INTO movimentacaoEstoque(item, funcionario, dataMovimentacao, tipoMovimentacao, quantidade) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setObject(1, movimentacaoEstoque.getItem().getId());
            pstmt.setObject(2, movimentacaoEstoque.getFuncionario().getId());
            Date date = Date.valueOf(movimentacaoEstoque.getDataMovimentacao());
            pstmt.setDate(3, date);
            pstmt.setString(4, movimentacaoEstoque.getTipoMovimentacao().toString());
            pstmt.setLong(5, movimentacaoEstoque.getQuantidade());

            pstmt.executeUpdate();

            System.out.println("Gravou a movimentacao estoque com sucesso no banco");

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoBD.descontecar();
        }
    }
}
