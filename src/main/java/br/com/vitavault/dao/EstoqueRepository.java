package br.com.vitavault.dao;

import br.com.vitavault.model.Estoque;
import br.com.vitavault.model.ItemEstoque;
import java.util.List;
import java.util.UUID;


public interface EstoqueRepository {
    void createTable();

    Estoque buscarEstoque();
    
    List<ItemEstoque> buscarItensEstoquePeloEstoque(UUID id);
}
