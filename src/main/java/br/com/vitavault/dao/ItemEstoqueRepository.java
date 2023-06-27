package br.com.vitavault.dao;

import br.com.vitavault.model.ItemEstoque;

import java.util.UUID;

public interface ItemEstoqueRepository {

    void createTable();

    boolean gravar(ItemEstoque itemEstoque);

    ItemEstoque buscarItem(UUID id);
}
