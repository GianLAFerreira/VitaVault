package br.com.vitavault.controller;

import br.com.vitavault.model.Estoque;
import br.com.vitavault.validation.CadastroContaValidation;
import br.com.vitavault.validation.impl.CadastroContaValidationImpl;
import br.com.vitavault.view.CadastroContaView;
import br.com.vitavault.view.HomePageView;
import br.com.vitavault.view.LoginView;

import java.util.HashSet;
import java.util.List;

public class LoginController {
    private LoginView loginView;
    private CadastroContaView cadastroContaView;
    private CadastroContaController cadastroContaController;
    private CadastroContaValidation cadastroContaValidation;
    private Estoque estoque;
    private HomePageController homePageController;

    public LoginController() {
        this.loginView = new LoginView();
        this.cadastroContaView = new CadastroContaView();
        this.cadastroContaController = new CadastroContaController(cadastroContaView);
        this.homePageController = new HomePageController(new HomePageView(criarEstoque()), this);
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
            homePageController.exibirTela();
        }
    }

    private Boolean validarLogin(String cpf, String senha) {
        List<String> mensagens = cadastroContaValidation.validar(cpf, senha);

        if (!mensagens.isEmpty()) {
            cadastroContaView.alertaMensagens(mensagens);
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

    public Estoque getEstoque() {
        return estoque;
    }

    public String getCpf() {
        return loginView.getCpf();
    }

    public String getSenha() {
        return loginView.getSenha();
    }
}

        
