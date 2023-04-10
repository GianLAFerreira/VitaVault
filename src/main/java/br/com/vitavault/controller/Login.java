package br.com.vitavault.controller;

import br.com.vitavault.model.Funcionario;

public interface Login {
    void login(Funcionario funcionario);

    void logout(Funcionario funcionario);
}
