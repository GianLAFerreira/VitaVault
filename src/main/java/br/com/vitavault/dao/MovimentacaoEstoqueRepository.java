package br.com.vitavault.dao;

import br.com.vitavault.domain.MovimentacaoEstoque;

public interface MovimentacaoEstoqueRepository {
    void createTable();

    boolean gravar(MovimentacaoEstoque movimentacaoEstoque);
}
