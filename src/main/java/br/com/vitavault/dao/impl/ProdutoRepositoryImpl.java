package br.com.vitavault.dao.impl;

import br.com.vitavault.dao.ConexaoBD;
import br.com.vitavault.dao.ProdutoRepository;
import br.com.vitavault.model.Produto;
import br.com.vitavault.model.ProdutoDepreciavel;
import br.com.vitavault.model.ProdutoNaoDepreciavel;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static br.com.vitavault.dao.ConexaoBD.conectar;

public class ProdutoRepositoryImpl implements ProdutoRepository {
    public ProdutoRepositoryImpl() {
        createTable();
    }

    @Override
    public void createTable() {
        Connection connection = ConexaoBD.conectar();
        String sqlCreate = "CREATE TABLE IF NOT EXISTS Produto ("
                + "id UUID DEFAULT gen_random_uuid() PRIMARY KEY,"
                + "codigo  INT,"
                + "nome  VARCHAR(255),"
                + "descricao TEXT,"
                + "preco NUMERIC(15, 2),"
                + "categoria VARCHAR(255),"
                + "situacao BOOLEAN,"
                + "dataValidade DATE)";

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute(sqlCreate);
            System.out.println("Criando a tabela Produto");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConexaoBD.descontecar();
        }
    }

    @Override
    public boolean gravar(Produto produto) {
        String sql = "INSERT INTO produto(codigo, nome, descricao, preco, categoria, situacao, dataValidade) VALUES(?, ?, ?, ?, ?, ? ,?)";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, produto.getCodigo());
            pstmt.setString(2, produto.getNome());
            pstmt.setString(3, produto.getDescricao());
            pstmt.setBigDecimal(4, produto.getPreco());
            pstmt.setString(5, produto.getCategoria());
            pstmt.setBoolean(6, produto.isSituacao());

            if (produto instanceof ProdutoDepreciavel) {
                LocalDate dataValidade = ((ProdutoDepreciavel) produto).getDataValidade();
                Date date = Date.valueOf(dataValidade);
                pstmt.setDate(7, date);
            } else {
                pstmt.setDate(7, null);
            }

            pstmt.executeUpdate();

            ResultSet resultSet = pstmt.getGeneratedKeys();

            if (resultSet.next()) {
                UUID id = (UUID) resultSet.getObject(1);
                produto.setId(id);
            }

            System.out.println("Gravou o Produto com sucesso no banco");

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoBD.descontecar();
        }
    }

    @Override
    public Produto buscarProduto(UUID id) {
        Produto produto = null;
        String sql = "SELECT * FROM produto WHERE id = ?";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                UUID idcoluna = UUID.fromString(rs.getString("id"));
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                BigDecimal preco = rs.getBigDecimal("preco");
                String categoria = rs.getString("categoria");
                boolean situacao = rs.getBoolean("situacao");
                Date dataValidade = rs.getDate("dataValidade");

                if (dataValidade != null) {
                    produto = new ProdutoDepreciavel(idcoluna, codigo, nome, descricao, preco, categoria, situacao, dataValidade.toLocalDate());
                } else {
                    produto = new ProdutoNaoDepreciavel(idcoluna, codigo, nome, descricao, preco, categoria, situacao);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            ConexaoBD.descontecar();
        }
        System.out.println("Produto encontrado com sucesso");
        return produto;
    }

    @Override
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM produto";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                UUID idcoluna = UUID.fromString(rs.getString("id"));
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                BigDecimal preco = rs.getBigDecimal("preco");
                String categoria = rs.getString("categoria");
                boolean situacao = rs.getBoolean("situacao");
                Date dataValidade = rs.getDate("dataValidade");

                if (dataValidade != null) {
                    produtos.add(new ProdutoDepreciavel(idcoluna, codigo, nome, descricao, preco, categoria, situacao, dataValidade.toLocalDate()));
                } else {
                    produtos.add(new ProdutoNaoDepreciavel(idcoluna, codigo, nome, descricao, preco, categoria, situacao));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        } finally {
            ConexaoBD.descontecar();
        }
        return produtos;
    }
}
