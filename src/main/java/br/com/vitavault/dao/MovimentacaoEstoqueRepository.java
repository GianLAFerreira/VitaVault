package br.com.vitavault.dao;

import br.com.vitavault.domain.MovimentacaoEstoque;

import java.util.List;
import java.util.UUID;

public interface MovimentacaoEstoqueRepository {
    void createTable();

    boolean gravar(MovimentacaoEstoque movimentacaoEstoque);

    List<MovimentacaoEstoque> buscarMovimentacoes(UUID id);

    List<MovimentacaoEstoque> buscarMovimentacoes();
}
