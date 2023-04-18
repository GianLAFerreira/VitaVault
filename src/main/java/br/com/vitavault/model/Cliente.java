
package br.com.vitavault.model;

import java.util.UUID;

/*
Classe destinada para a criação dos atributos que cada cliente deve ter para se cadastrar no Sistema
*/
public abstract class Cliente {
    private String nome;
    private String cpf;
    private String endereco;
    private String id;
    private int telefone;
    private int nivelAcesso;

    Cliente(String nome, String cpf, String endereco, int telefone) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }
    
    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public int getTelefone() {
        return telefone;
    }

    public String getId() {
        return id;
    }
    
    public int getNivelAcesso() {
        return nivelAcesso;
    }

    protected void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
}
