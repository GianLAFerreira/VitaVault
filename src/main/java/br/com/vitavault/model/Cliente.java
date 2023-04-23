
package br.com.vitavault.model;

import br.com.vitavault.exceptions.GerenciadorClientesException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
Classe destinada para a criação dos atributos que cada cliente deve ter para se cadastrar no Sistema
*/
public class Cliente {
    private String nome;
    private String cpf;
    private String endereco;
    private String id;
    private int telefone;
    private List<Cliente> clientes = new ArrayList();

    public Cliente(String nome, String cpf, String endereco, int telefone, List<Cliente> clientes) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.clientes = clientes;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Métodos">
    public void cadastrarCliente(Cliente oCliente) throws GerenciadorClientesException {
        if(!verificarClienteExiste(oCliente)) {
            clientes.add(oCliente);
            System.out.printf(this.separador() + "Cliente \n Nome: %s \n Cpf: %s \n Telefone: %d \n Endereço: %s \n Cadastrado com sucesso" + this.separador(), oCliente.getNome(), oCliente.getCpf(), oCliente.getTelefone(), oCliente.getEndereco());
        }
        else {
            throw new GerenciadorClientesException("Já existe um cliente cadastrado com esse CPF.");
        }
    }
    
    public void atualizarCliente(Cliente oClienteAtualizado) throws GerenciadorClientesException {
        if(!clientes.isEmpty()) {
            for(Cliente cliente : clientes) {
                if(cliente.getCpf().equalsIgnoreCase(oClienteAtualizado.getCpf())) {
                    cliente.setEndereco(oClienteAtualizado.getEndereco());
                    cliente.setNome(oClienteAtualizado.getNome());
                    cliente.setTelefone(oClienteAtualizado.getTelefone());
                    System.out.println("Cliente atualizado com sucesso.");
                    return;
                }
            }
            throw new GerenciadorClientesException("Cliente informado não está cadastrado.");
        }
        else {
            throw new GerenciadorClientesException("Não há clientes cadastrados.");
        }
    }
    
    public void removerCliente(Cliente oCliente) throws GerenciadorClientesException {
        if(verificarClienteExiste(oCliente)) {
            clientes.remove(oCliente);
            System.out.printf(this.separador() + "Cliente \n Nome: %s \n Cpf: %s \n Telefone: %d \n Endereço: %s \n Removido com sucesso" + this.separador(), oCliente.getNome(), oCliente.getCpf(), oCliente.getTelefone(), oCliente.getEndereco());
        }
        else {
            throw new GerenciadorClientesException("Cliente informado não está cadastrado.");
        }
    }
    
    public void listarClientes() throws GerenciadorClientesException{
        if(clientes.isEmpty()) {
            throw new GerenciadorClientesException("Não há clientes cadastrados");
        }
        
        for(Cliente cliente : clientes) {
            System.out.printf(this.separador() + "Cliente \n Nome: %s \n Cpf: %s \n Telefone: %d \n Endereço: %s" + this.separador(), cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getEndereco());
        }
    }
    
    private boolean verificarClienteExiste(Cliente oCliente) {
        return clientes.stream().anyMatch(Cliente -> Cliente.getCpf().equals(oCliente.getCpf()));
    }
    
    private String separador() {
        return "\n------------------------\n";
    }
    //</editor-fold>
}
