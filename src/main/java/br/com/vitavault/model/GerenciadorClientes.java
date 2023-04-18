
package br.com.vitavault.model;

import br.com.vitavault.exceptions.GerenciadorClientesException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Classe que tem por função conter todos os métodos de manutenção de um cliente tais como : Cadastro de clientes, Exclusão de um cliente
 */
public class GerenciadorClientes {
    List<Cliente> clientes = new ArrayList();
    
    GerenciadorClientes(List<Cliente> clientes) {
        if(clientes.isEmpty()) {
            this.clientes = clientes;
        }
    }
    
    public void cadastrarCliente(Cliente oCliente) throws GerenciadorClientesException {
        if(!verificarClienteExiste(oCliente)) {
            clientes.add(oCliente);
            System.out.printf(this.separador() + "Cliente \n Nome: %s \n Cpf: %s \n Telefone: %d \n Endereço: %s \n Cadastrado com sucesso" + this.separador(), oCliente.getNome(), oCliente.getCpf(), oCliente.getTelefone(), oCliente.getEndereco());
        }
        else {
            throw new GerenciadorClientesException("Já existe um cliente cadastrado com esse CPF.");
        }
    }
    
    /*
    Implementar 
    */
    public void atualizarCliente(Cliente oCliente) {
        
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
}
