package main.java.br.com.vitavault.controller;

import main.java.br.com.vitavault.model.Funcionario;

public interface Login {
    void login(Funcionario funcionario);

    void logout(Funcionario funcionario);
}
