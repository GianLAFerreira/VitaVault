package br.com.vitavault.dao;

import br.com.vitavault.model.Funcionario;

import java.util.List;
import java.util.UUID;

public interface FuncionarioRepository {

    void createTable();

    boolean gravar(Funcionario funcionario);

    Funcionario buscarFuncionario(UUID id);

    boolean removerFuncionario(UUID id);

    List<Funcionario> listarFuncionarios();
}
