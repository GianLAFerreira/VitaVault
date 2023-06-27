
package br.com.vitavault.controller;

import br.com.vitavault.model.Estoque;
import br.com.vitavault.model.Funcionario;
import br.com.vitavault.swing.HomePage_1;
import br.com.vitavault.swing.Tela_Cadastrar_Conta;
import br.com.vitavault.swing.Tela_Login;
import java.util.HashSet;

public class TelaLoginController {
    private Tela_Login oTelaLogin;
    private Funcionario oFuncionarioLogado;
    private Tela_Cadastrar_Conta oTelaCadastrarConta;
    private Estoque estoque;
    
    public Estoque getEstoque() {
        return estoque;
    }
    
    public Funcionario getFuncionarioLogado() {
        return oFuncionarioLogado;
    }
   
    public TelaLoginController(Tela_Login oTelaLogin) {
        this.oTelaLogin = oTelaLogin;
        inicializarBotoesLogin();
    }
    
    public void inicializarBotoesLogin() {
        oTelaLogin.adicionarAcaoBotaoCadastrarUsuario((a) -> exibeTelaCadastroUsuario(new Tela_Cadastrar_Conta()));
        oTelaLogin.adicionarAcaoBotaoLogar((a) -> exibeTelaHome());
    }
    
    private void exibeTelaCadastroUsuario(Tela_Cadastrar_Conta oTelaCadastrarConta) {
        this.oTelaCadastrarConta = oTelaCadastrarConta;
        TelaCadastroUsuarioController oTelaCadastrarContaController = new TelaCadastroUsuarioController(oTelaCadastrarConta);
        oTelaCadastrarContaController.exibirTela();
    }
    
    private void exibeTelaHome() {
        if(verificaUsuarioCadastrado()) {
            oTelaLogin.dispose();
            oTelaLogin.resetarCamposLogin();
            TelaHomeController oTelaController = new TelaHomeController(new HomePage_1(new Funcionario(oTelaLogin.getCpf(), oTelaLogin.getSenha()), criarEstoque()), this);
            oTelaController.exibirTela();
        }
    }
    
    private Estoque criarEstoque() {
        Estoque oEstoque = new Estoque(new HashSet<>());
        estoque = oEstoque;
        return oEstoque;
    }
   
    private boolean verificaUsuarioCadastrado() {
        String cpf = oTelaLogin.getCpf();
        String senha = oTelaLogin.getSenha();
        
        return Funcionario.verificarClienteCadastradoSistema(cpf, senha);
    }
    
    public void exibe() {
        oTelaLogin.exibe();
    }
    
    public static void main(String[] args) {
        TelaLoginController oTela = new TelaLoginController(new Tela_Login());
        oTela.exibe();
    }
}

        
