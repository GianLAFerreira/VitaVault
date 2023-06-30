package br.com.vitavault.dao;

import br.com.vitavault.model.ItemEstoque;

import java.util.List;
import java.util.UUID;

public interface ItemEstoqueRepository {

    void createTable();

    boolean gravar(ItemEstoque itemEstoque);

    boolean alterarSaldo(UUID id, Long quantidade);

    ItemEstoque buscarItem(UUID id);

    ItemEstoque buscarItemEstoquePeloProduto(UUID id);

    List<ItemEstoque> buscarTodosItens();

}
