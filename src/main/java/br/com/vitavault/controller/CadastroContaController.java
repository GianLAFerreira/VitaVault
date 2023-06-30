package br.com.vitavault.controller;

import br.com.vitavault.exceptions.GerenciadorFuncionariosException;
import br.com.vitavault.model.Funcionario;
import br.com.vitavault.validation.CadastroContaValidation;
import br.com.vitavault.validation.impl.CadastroContaValidationImpl;
import br.com.vitavault.view.CadastroContaView;

import javax.swing.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroContaController {
    private CadastroContaView cadastroContaView;
    private CadastroContaValidation cadastroContaValidation;

    public CadastroContaController(CadastroContaView cadastroContaView) {
        this.cadastroContaView = cadastroContaView;
        cadastroContaValidation = new CadastroContaValidationImpl();
        inicializarBotoes();
    }


    public void inicializarBotoes() {
        cadastroContaView.adicionarAcaoCriarConta((a) -> cadastrarUsuario());
    }

    public void cadastrarUsuario() {
        String nome = cadastroContaView.getNome();
        String cpfTexto = cadastroContaView.getCpf();
        String endereco = cadastroContaView.getEndereco();
        String senha = cadastroContaView.getSenha();
        String telefoneTexto = cadastroContaView.getTelefone();

        List<String> mensagens = cadastroContaValidation.validar(nome, cpfTexto, endereco, senha, telefoneTexto);


        if (!mensagens.isEmpty()) {
            cadastroContaView.alertaMensagens(mensagens);
            return;
        }

        Funcionario funcionario = new Funcionario(cpfTexto, nome, endereco, telefoneTexto, senha);

        try {
            funcionario.cadastrarFuncionario(funcionario);
            JOptionPane.showMessageDialog(cadastroContaView, "Conta criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            cadastroContaView.resetarCampos();
            cadastroContaView.dispose();
        } catch (GerenciadorFuncionariosException ex) {
            JOptionPane.showMessageDialog(cadastroContaView, "Erro ao criar a conta: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CadastroContaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void exibirTela() {
        cadastroContaView.exibe();
    }
}
