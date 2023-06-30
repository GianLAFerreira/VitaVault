package br.com.vitavault.dao.impl;

import br.com.vitavault.dao.ConexaoBD;
import static br.com.vitavault.dao.ConexaoBD.conectar;
import br.com.vitavault.dao.EstoqueRepository;
import br.com.vitavault.dao.ProdutoRepository;
import br.com.vitavault.domain.EnumTipoMovimentacao;
import br.com.vitavault.model.Estoque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

import br.com.vitavault.model.ItemEstoque;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstoqueRepositoryImpl implements EstoqueRepository {
    private ProdutoRepository produtoRepository;
    public EstoqueRepositoryImpl() {
        createTable();
        produtoRepository = new ProdutoRepositoryImpl();
        System.out.println("cria tabela");
    }

    @Override
    public void createTable() {

        String sqlCreate = "CREATE TABLE IF NOT EXISTS Estoque ("
                + "id UUID DEFAULT gen_random_uuid() PRIMARY KEY)";


        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sqlCreate, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConexaoBD.descontecar();
        }
        Estoque estoque = buscarEstoque();
        
        if (Objects.isNull(estoque)) {
            gravar();
        }
    }

    private void gravar() {
        String sql = "INSERT INTO Estoque DEFAULT VALUES";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConexaoBD.descontecar();
        }
    }

    @Override
    public Estoque buscarEstoque() {
        Estoque estoque = null;
        String sql = "SELECT * FROM estoque";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));


                estoque = new Estoque(new HashSet<>(), id);
                System.out.println("Estoque encontrado com sucesso");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            ConexaoBD.descontecar();
        }
        return estoque;
    }

    @Override
    public List<ItemEstoque> buscarItensEstoquePeloEstoque(UUID id) {
        String sql = "SELECT * FROM itemestoque where estoque = ?";
        List<ItemEstoque> itemEstoqueList = new ArrayList<>();
        ItemEstoque itemEstoque = null;
        
        try (Connection conn = conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setObject(1, id);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                UUID idcoluna = UUID.fromString(rs.getString("id"));
                UUID item = UUID.fromString(rs.getString("produto"));
                LocalDate data = rs.getDate("data").toLocalDate();
                Long quantidade = rs.getLong("quantidade");
                EnumTipoMovimentacao tipoMovimentacao = EnumTipoMovimentacao.valueOf(rs.getString("tipoMovimentacao"));
                
                itemEstoque = new ItemEstoque (
                        idcoluna, 
                        produtoRepository.buscarProduto(item),
                        buscarEstoque(),
                        data,
                        quantidade,
                        tipoMovimentacao);
                
                itemEstoqueList.add(itemEstoque);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            ConexaoBD.descontecar();
        }
        System.out.println("Produtos encontrado com sucesso");
        return itemEstoqueList;
    }
    
}
