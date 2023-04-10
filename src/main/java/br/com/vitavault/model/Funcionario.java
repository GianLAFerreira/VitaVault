package br.com.vitavault.model;

import br.com.vitavault.controller.Login;

import java.util.List;
import java.util.UUID;

public class Funcionario implements Login {

    private UUID id;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private String senha;
    private Papel alcada;

    public Funcionario(UUID id, String nome, String telefone, String email, String endereco, String senha, Papel alcada) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.senha = senha;
        this.alcada = alcada;
    }

    public void gravar(Funcionario funcionario) {

    }

    public Funcionario findById(UUID id) {

        return null;
    }

    public Funcionario findByCPF(String cpf) {

        return null;
    }

    public List<Funcionario> findByName(String nome) {

        return null;
    }

    public void deleteById(UUID id) {

    }

    public List<Funcionario> listar() {

        return null;
    }

    public void atualizar(Funcionario funcionario) {

    }

    @Override
    public void login(Funcionario funcionario) {

    }

    @Override
    public void logout(Funcionario funcionario) {

    }
}
