package br.com.vitavault.controller;

import br.com.vitavault.model.Cliente;

public interface Login {
    void login(Cliente oCliente);

    void logout(Cliente oCliente);
}
