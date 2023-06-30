package br.com.vitavault.dao.impl;

import br.com.vitavault.dao.ConexaoBD;
import br.com.vitavault.dao.EstoqueRepository;
import br.com.vitavault.dao.FuncionarioRepository;
import br.com.vitavault.dao.MovimentacaoEstoqueRepository;
import br.com.vitavault.dao.ProdutoRepository;
import br.com.vitavault.domain.EnumTipoMovimentacao;
import br.com.vitavault.domain.MovimentacaoEstoque;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static br.com.vitavault.dao.ConexaoBD.conectar;

public class MovimentacaoEstoqueRepositoryImpl implements MovimentacaoEstoqueRepository {

    private ProdutoRepository produtoRepository;
    private FuncionarioRepository funcionarioRepository;
    private EstoqueRepository estoqueRepository;

    public MovimentacaoEstoqueRepositoryImpl() {
        createTable();
        produtoRepository = new ProdutoRepositoryImpl();
        funcionarioRepository = new FuncionarioRepositoryImpl();
        estoqueRepository = new EstoqueRepositoryImpl();
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

            ResultSet resultSet = pstmt.getGeneratedKeys();

            if (resultSet.next()) {
                UUID id = (UUID) resultSet.getObject(1);
                movimentacaoEstoque.setId(id);
            }

            System.out.println("Gravou a movimentacao estoque com sucesso no banco");

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoBD.descontecar();
        }
    }

    @Override
    public List<MovimentacaoEstoque> buscarMovimentacoes(UUID id) {
        List<MovimentacaoEstoque> movimentacaoEstoqueList = new ArrayList<>();
        MovimentacaoEstoque movimentacaoEstoque = null;

        String sql = "SELECT * FROM movimentacaoestoque where item = ?";

        try (Connection conn = conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, id);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                UUID idcoluna = UUID.fromString(rs.getString("id"));
                UUID item = UUID.fromString(rs.getString("item"));
                UUID funcionario = UUID.fromString(rs.getString("funcionario"));
                LocalDate dataMovimentacao = rs.getDate("dataMovimentacao").toLocalDate();
                EnumTipoMovimentacao tipoMovimentacao = EnumTipoMovimentacao.valueOf(rs.getString("tipoMovimentacao"));
                Long quantidade = rs.getLong("quantidade");


                movimentacaoEstoque = new MovimentacaoEstoque(
                        idcoluna,
                        produtoRepository.buscarProduto(item)
                        , funcionarioRepository.buscarFuncionario(funcionario)
                        , dataMovimentacao
                        , tipoMovimentacao
                        , quantidade
                        , estoqueRepository.buscarEstoque());


                movimentacaoEstoqueList.add(movimentacaoEstoque);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            ConexaoBD.descontecar();
        }
        System.out.println("Produtos encontrado com sucesso");
        return movimentacaoEstoqueList;
    }
}
