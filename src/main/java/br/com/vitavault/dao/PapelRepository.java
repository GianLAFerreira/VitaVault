package br.com.vitavault.dao;

import br.com.vitavault.model.Papel;

import java.util.UUID;

public interface PapelRepository {

    void createTable();

    boolean gravar(Papel papel);

    Papel buscarPapel(UUID id);

    boolean removerPapel(UUID id);

}
