package br.com.vitavault.dao;

import br.com.vitavault.model.Produto;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepository {

    void createTable();

    boolean gravar(Produto produto);

    Produto buscarProduto(UUID id);

    List<Produto> listarProdutos();
}
