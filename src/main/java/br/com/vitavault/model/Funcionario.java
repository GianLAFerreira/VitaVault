
package br.com.vitavault.model;

import br.com.vitavault.controller.Login;
import br.com.vitavault.exceptions.GerenciadorClientesException;
import br.com.vitavault.swing.HomePage_1;

import java.util.*;

/*
Classe destinada para a criação dos atributos que cada cliente deve ter para se cadastrar no Sistema
*/
public class Funcionario implements Login, Comparable<Funcionario>{
    private String nome;
    private String cpf;
    private String endereco;
    private UUID id;
    private String senha;
    private String telefone;
    protected static List<Funcionario> funcionarios = new ArrayList();
    
    public Funcionario(String cpf, String senha) {
        this.id = UUID.randomUUID();
        this.cpf = cpf;
        this.senha = senha;
    }
    
    public Funcionario(String cpf, String nome, String endereco, String telefone, String senha) {
        this(cpf, senha);
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }
        
    public boolean logarClienteSistema(Funcionario oFuncionario) {
       for(Funcionario funcionario : funcionarios) {
            if(Objects.equals(funcionario.getCpf(), oFuncionario.getCpf()) && funcionario.getSenha().equals(oFuncionario.getSenha())) {
                login(funcionario);
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
    
    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public UUID getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Métodos">
    public void cadastrarCliente(Funcionario oFuncionario) throws GerenciadorClientesException {
        if(!verificarClienteExiste(oFuncionario)) {
            funcionarios.add(oFuncionario);
            System.out.printf(Funcionario.separador() + "Cliente \n Nome: %s \n Cpf: %s \n Telefone: %s \n Endereço: %s \n Cadastrado com sucesso" + Funcionario.separador(), oFuncionario.getNome(), oFuncionario.getCpf(), oFuncionario.getTelefone(), oFuncionario.getEndereco());
        }
        else {
            throw new GerenciadorClientesException("Já existe um cliente cadastrado com esse CPF.");
        }
    }
    
    public void atualizarCliente(Funcionario oFuncionarioAtualizado) throws GerenciadorClientesException {
        if(!funcionarios.isEmpty()) {
            for(Funcionario funcionario : funcionarios) {
                if(funcionario.getCpf() == oFuncionarioAtualizado.getCpf()) {
                    funcionario.setEndereco(oFuncionarioAtualizado.getEndereco());
                    funcionario.setNome(oFuncionarioAtualizado.getNome());
                    funcionario.setTelefone(oFuncionarioAtualizado.getTelefone());
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
    
    public void removerCliente(Funcionario oFuncionario) throws GerenciadorClientesException {
        if(verificarClienteExiste(oFuncionario)) {
            funcionarios.remove(oFuncionario);
            System.out.printf(Funcionario.separador() + "Cliente \n Nome: %s \n Cpf: %s \n Telefone: %d \n Endereço: %s \n Removido com sucesso" + Funcionario.separador(), oFuncionario.getNome(), oFuncionario.getCpf(), oFuncionario.getTelefone(), oFuncionario.getEndereco());
        }
        else {  
            throw new GerenciadorClientesException("Cliente informado não está cadastrado.");
        }
    }
    
    public static void listarClientes() throws GerenciadorClientesException{
        if(funcionarios.isEmpty()) {
            throw new GerenciadorClientesException("Não há clientes cadastrados");
        }
//        ordenarClientesPorNome();
        ordenarClientesPorCpf();
        for(Funcionario funcionario : funcionarios) {
            System.out.printf(Funcionario.separador() + "Cliente \n Nome: %s \n Cpf: %s \n Telefone: %d \n Endereço: %s" + Funcionario.separador(), funcionario.getNome(), funcionario.getCpf(), funcionario.getTelefone(), funcionario.getEndereco());
        }
    }
    
    private boolean verificarClienteExiste(Funcionario oFuncionario) {
        return funcionarios.stream().anyMatch(Cliente -> Cliente.getCpf() == oFuncionario.getCpf());
    }
    
    public static String separador() {
        return "\n------------------------\n";
    }
    //</editor-fold>

    public void login(Funcionario oFuncionario) {
        HomePage_1 tela = new HomePage_1();
        tela.setVisible(true);
    }

    @Override
    public void logout(Funcionario oFuncionario) {
    }

    @Override
    public int compareTo(Funcionario oFuncionario) {
        return this.nome.compareTo(oFuncionario.getNome());
    }
    
    public static void ordenarClientesPorNome() {
        Collections.sort(funcionarios);
    }
    
    public static void ordenarClientesPorCpf() {
        Comparator<Funcionario> comparador = new Comparator<Funcionario>() {
            @Override
            public int compare(Funcionario oCliente1, Funcionario oCliente2) {
                return Integer.parseInt(oCliente1.getCpf()) - Integer.parseInt(oCliente2.getCpf());
            }
        };
        Collections.sort(funcionarios, comparador);
    }
}
