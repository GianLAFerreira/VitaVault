package br.com.vitavault.dao.impl;

import br.com.vitavault.dao.ConexaoBD;
import br.com.vitavault.dao.EstoqueRepository;
import br.com.vitavault.dao.ItemEstoqueRepository;
import br.com.vitavault.dao.ProdutoRepository;
import br.com.vitavault.domain.EnumTipoMovimentacao;
import br.com.vitavault.model.ItemEstoque;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.UUID;

import static br.com.vitavault.dao.ConexaoBD.conectar;

public class ItemEstoqueRepositoryImpl implements ItemEstoqueRepository {

    private ProdutoRepository produtoRepository;
    private EstoqueRepository estoqueRepository;

    public ItemEstoqueRepositoryImpl() {
        createTable();
        produtoRepository = new ProdutoRepositoryImpl();
        estoqueRepository = new EstoqueRepositoryImpl();
    }

    @Override
    public void createTable() {
        Connection connection = ConexaoBD.conectar();
        String sqlCreate = "CREATE TABLE IF NOT EXISTS ItemEstoque ("
                + "id UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
                + "produto UUID,"
                + "estoque  UUID,"
                + "data DATE,"
                + "quantidade BIGINT,"
                + "tipoMovimentacao  VARCHAR(255),"
                + "FOREIGN KEY (produto) REFERENCES produto(id),"
                + "FOREIGN KEY (estoque) REFERENCES estoque(id))";

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
    public boolean gravar(ItemEstoque itemEstoque) {
        String sql = "INSERT INTO itemestoque(id, produto, estoque, data, quantidade, tipoMovimentacao) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setObject(1, itemEstoque.getId());
            pstmt.setObject(2, itemEstoque.getProduto().getId());
            pstmt.setObject(3, itemEstoque.getEstoque().getId());
            Date date = Date.valueOf(itemEstoque.getData());
            pstmt.setDate(4, date);
            pstmt.setLong(5, itemEstoque.getQuantidade());
            pstmt.setString(6, itemEstoque.getTipoMovimentacao().toString());

            pstmt.executeUpdate();
            
            System.out.println("Gravou o itemEstoque com sucesso no banco");

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoBD.descontecar();
        }
    }

    @Override
    public ItemEstoque buscarItem(UUID id) {
        ItemEstoque itemEstoque = null;
        String sql = "SELECT * FROM itemestoque WHERE id = ?";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                UUID idcoluna = UUID.fromString(rs.getString("id"));
                UUID produto = UUID.fromString(rs.getString("produto"));
                UUID estoque = UUID.fromString(rs.getString("estoque"));
                LocalDate data = rs.getDate("data").toLocalDate();
                Long quantidade = rs.getLong("quantidade");
                String tipoMovimentacao = rs.getString("tipoMovimentacao");


                itemEstoque = new ItemEstoque(idcoluna,
                        produtoRepository.buscarProduto(produto),
                        estoqueRepository.buscarEstoque(),
                        data,
                        quantidade,
                        EnumTipoMovimentacao.valueOf(tipoMovimentacao));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            ConexaoBD.descontecar();
        }
        System.out.println("Produto encontrado com sucesso");
        return itemEstoque;
    }
}
