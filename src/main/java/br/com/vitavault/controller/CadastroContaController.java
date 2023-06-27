package br.com.vitavault.controller;

import br.com.vitavault.exceptions.GerenciadorFuncionariosException;
import br.com.vitavault.model.Funcionario;
import br.com.vitavault.validation.CadastroContaValidation;
import br.com.vitavault.validation.impl.CadastroContaValidationImpl;
import br.com.vitavault.view.CadastrarContaView;

import javax.swing.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroContaController {
    private CadastrarContaView cadastrarContaView;
    private CadastroContaValidation cadastroContaValidation;


    public CadastroContaController(CadastrarContaView cadastrarContaView) {
        this.cadastrarContaView = cadastrarContaView;
        cadastroContaValidation = new CadastroContaValidationImpl();
        inicializarBotoes();
    }


    public void inicializarBotoes() {
        cadastrarContaView.adicionarAcaoCriarConta((a) -> cadastrarUsuario());
    }

    public void cadastrarUsuario() {
        String nome = cadastrarContaView.getNome();
        String cpfTexto = cadastrarContaView.getCpf();
        String endereco = cadastrarContaView.getEndereco();
        String senha = cadastrarContaView.getSenha();
        String telefoneTexto = cadastrarContaView.getTelefone();

        List<String> mensagens = cadastroContaValidation.validar(nome, cpfTexto, endereco, senha, telefoneTexto);


        if (!mensagens.isEmpty()) {
            cadastrarContaView.alertaMensagens(mensagens);
            return;
        }

        Funcionario funcionario = new Funcionario(cpfTexto, nome, endereco, telefoneTexto, senha);

        try {
            funcionario.cadastrarFuncionario(funcionario);
            JOptionPane.showMessageDialog(cadastrarContaView, "Conta criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            cadastrarContaView.resetarCampos();
            cadastrarContaView.dispose();
        } catch (GerenciadorFuncionariosException ex) {
            JOptionPane.showMessageDialog(cadastrarContaView, "Erro ao criar a conta: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CadastrarContaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void exibirTela() {
        cadastrarContaView.exibe();
    }
}
