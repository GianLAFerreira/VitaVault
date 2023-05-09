
package br.com.vitavault.model;

import br.com.vitavault.controller.Login;
import br.com.vitavault.exceptions.GerenciadorClientesException;
import br.com.vitavault.swing.HomePage_1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/*
Classe destinada para a criação dos atributos que cada cliente deve ter para se cadastrar no Sistema
*/
public class Cliente implements Login, Comparable<Cliente>{
    private String nome;
    private int cpf;
    private String endereco;
    private String id;
    private String senha;
    private int telefone;
    protected static List<Cliente> clientes = new ArrayList();
    
    public Cliente(int cpf,  String senha) {
        this.id = UUID.randomUUID().toString();
        this.cpf = cpf;
        this.senha = senha;
    }
    
    public Cliente(int cpf, String nome, String endereco, int telefone, String senha) {
        this(cpf, senha);
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }
        
    public boolean logarClienteSistema(Cliente oCliente) {
       for(Cliente cliente : clientes) {
            if(cliente.getCpf() == oCliente.getCpf() && cliente.getSenha().equals(oCliente.getSenha())) {
                login(cliente);
                return true;
            }
        }
       return false;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public String getNome() {
        return nome;
    }
    
    public int getCpf() {
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
            System.out.printf(Cliente.separador() + "Cliente \n Nome: %s \n Cpf: %s \n Telefone: %d \n Endereço: %s \n Cadastrado com sucesso" + Cliente.separador(), oCliente.getNome(), oCliente.getCpf(), oCliente.getTelefone(), oCliente.getEndereco());
        }
        else {
            throw new GerenciadorClientesException("Já existe um cliente cadastrado com esse CPF.");
        }
    }
    
    public void atualizarCliente(Cliente oClienteAtualizado) throws GerenciadorClientesException {
        if(!clientes.isEmpty()) {
            for(Cliente cliente : clientes) {
                if(cliente.getCpf() == oClienteAtualizado.getCpf()) {
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
            System.out.printf(Cliente.separador() + "Cliente \n Nome: %s \n Cpf: %s \n Telefone: %d \n Endereço: %s \n Removido com sucesso" + Cliente.separador(), oCliente.getNome(), oCliente.getCpf(), oCliente.getTelefone(), oCliente.getEndereco());
        }
        else {  
            throw new GerenciadorClientesException("Cliente informado não está cadastrado.");
        }
    }
    
    public static void listarClientes() throws GerenciadorClientesException{
        if(clientes.isEmpty()) {
            throw new GerenciadorClientesException("Não há clientes cadastrados");
        }
//        ordenarClientesPorNome();
        ordenarClientesPorCpf();
        for(Cliente cliente : clientes) {
            System.out.printf(Cliente.separador() + "Cliente \n Nome: %s \n Cpf: %s \n Telefone: %d \n Endereço: %s" + Cliente.separador(), cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getEndereco());
        }
    }
    
    private boolean verificarClienteExiste(Cliente oCliente) {
        return clientes.stream().anyMatch(Cliente -> Cliente.getCpf() == oCliente.getCpf());
    }
    
    public static String separador() {
        return "\n------------------------\n";
    }
    //</editor-fold>

    public void login(Cliente oCliente) {
        HomePage_1 tela = new HomePage_1();
        tela.setVisible(true);
    }

    @Override
    public void logout(Cliente oCliente) {
    }

    @Override
    public int compareTo(Cliente oCliente) {
        return this.nome.compareTo(oCliente.getNome());
    }
    
    public static void ordenarClientesPorNome() {
        Collections.sort(clientes);
    }
    
    public static void ordenarClientesPorCpf() {
        Comparator<Cliente> comparador = new Comparator<Cliente>() {
            @Override
            public int compare(Cliente oCliente1, Cliente oCliente2) {
                return oCliente1.getCpf() - oCliente2.getCpf();
            }
        };
        Collections.sort(clientes, comparador);
    }
}
