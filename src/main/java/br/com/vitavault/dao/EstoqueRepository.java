package br.com.vitavault.dao;

import br.com.vitavault.model.Estoque;


public interface EstoqueRepository {
    void createTable();

    Estoque buscarEstoque();
}
