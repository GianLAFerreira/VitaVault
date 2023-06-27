package br.com.vitavault.controller;

import br.com.vitavault.model.Estoque;
import br.com.vitavault.validation.CadastroContaValidation;
import br.com.vitavault.validation.impl.CadastroContaValidationImpl;
import br.com.vitavault.view.CadastrarContaView;
import br.com.vitavault.view.HomePageView;
import br.com.vitavault.view.LoginView;

import java.util.HashSet;
import java.util.List;

public class TelaLoginController {
    private LoginView loginView;
    private CadastrarContaView cadastrarContaView;
    private CadastroContaController cadastroContaController;
    private CadastroContaValidation cadastroContaValidation;
    private Estoque estoque;
    private TelaHomeController telaHomeController;

    public Estoque getEstoque() {
        return estoque;
    }

    public String getCpf() {
        return loginView.getCpf();
    }

    public String getSenha() {
        return loginView.getSenha();
    }

    public TelaLoginController() {
        this.loginView = new LoginView();
        this.cadastrarContaView = new CadastrarContaView();
        this.cadastroContaController = new CadastroContaController(cadastrarContaView);
        this.telaHomeController = new TelaHomeController(new HomePageView(criarEstoque()), this);
        this.cadastroContaValidation = new CadastroContaValidationImpl();
        inicializarBotoesLogin();
    }

    public void inicializarBotoesLogin() {
        loginView.adicionarAcaoBotaoCadastrarUsuario((a) -> exibeTelaCadastroConta());
        loginView.adicionarAcaoBotaoLogar((a) -> exibeTelaHome());
    }

    private void exibeTelaCadastroConta() {
        cadastroContaController.exibirTela();
    }

    private void exibeTelaHome() {
        if (Boolean.TRUE.equals(validarLogin(getCpf(), getSenha()))) {
            loginView.dispose();
            loginView.resetarCamposLogin();
            telaHomeController.exibirTela();
        }
    }

    private Boolean validarLogin(String cpf, String senha) {
        List<String> mensagens = cadastroContaValidation.validar(cpf, senha);

        if (!mensagens.isEmpty()) {
            cadastrarContaView.alertaMensagens(mensagens);
            return false;
        }
        return true;
    }

    private Estoque criarEstoque() {
        Estoque oEstoque = new Estoque(new HashSet<>());
        estoque = oEstoque;
        return oEstoque;
    }

    public void exibe() {
        loginView.exibe();
    }

    public static void main(String[] args) {
        TelaLoginController tela = new TelaLoginController();
        tela.exibe();
    }
}

        
