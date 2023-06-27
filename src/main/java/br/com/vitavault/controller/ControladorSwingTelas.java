//
//package br.com.vitavault.controller;
//
//import br.com.vitavault.exceptions.GerenciadorClientesException;
//import br.com.vitavault.model.Estoque;
//import br.com.vitavault.model.Funcionario;
//import br.com.vitavault.swing.HomeConsulta;
//import br.com.vitavault.swing.HomeMovimentacao;
//import br.com.vitavault.swing.HomePage_1;
//import br.com.vitavault.swing.Tela_Cadastrar_Conta;
//import br.com.vitavault.swing.Tela_Login;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.JOptionPane;
//import br.com.vitavault.validation.CadastroContaValidation;
//import br.com.vitavault.validation.impl.CadastroContaValidationImpl;
//import java.util.HashSet;
//
//public class ControladorSwingTelas {
//    private Tela_Cadastrar_Conta telaCadastrarConta;
//    private CadastroContaValidation cadastroContaValidation = new CadastroContaValidationImpl();
//    private HomePage_1 telaHome;
//    private Estoque estoque;
//    private Funcionario funcionarioLogado;
//    
//    public ControladorSwingTelas(Tela_Login oTelaLogin) {
//        this.telaLogin = oTelaLogin;
//        inicializarBotoesLogin();
//    }
//    
//    
//    

//    
//    
//    
//    private void inicializaBotoesHome() {
//        telaHome.adicionarAcaoBotaoConsultaEstoque((a) -> exibeTelaConsultaEstoque());
//        telaHome.adicionaAcaoBotaoMovimentaEstoque((a) -> exibeTelaMovimentaoEstoque());
//    }
//    
//     private Estoque criarEstoque() {
//        Estoque oEstoque = new Estoque(new HashSet<>());
//        estoque = oEstoque;
//        return oEstoque;
//    }
//
//    
//    
//     private void exibeTelaHome() {
//        if(verificaUsuarioCadastrado()) {
//            
//        }
//    }
//    
//    public void cadastrarUsuario() {
//        String nome = telaCadastrarConta.getNome();
//        String cpfTexto = telaCadastrarConta.getCpf();
//        String endereco = telaCadastrarConta.getEndereco();
//        String senha = telaCadastrarConta.getSenha();
//        String telefoneTexto = telaCadastrarConta.getTelefone();
//        Tela_Cadastrar_Conta oTelaCadastrarConta = new Tela_Cadastrar_Conta();
//        this.telaCadastrarConta = oTelaCadastrarConta;
//        inicializaBotaoCadastrarUsuario();
//        
//        List<String> mensagens = cadastroContaValidation.validar(nome, cpfTexto, endereco, senha, telefoneTexto);
//
//        if (!mensagens.isEmpty()) {
//            alertaMensagens(mensagens);
//            return;
//        }
//
//        String cpf = cpfTexto;
//        String telefone = telefoneTexto;
//        Funcionario funcionario = new Funcionario(cpf, nome, endereco, telefone, senha);
//        
//        try {
//            funcionario.cadastrarCliente(funcionario);
//            JOptionPane.showMessageDialog(telaCadastrarConta, "Conta criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
//            telaCadastrarConta.dispose();
//        } catch (GerenciadorClientesException ex) {
//            JOptionPane.showMessageDialog(telaCadastrarConta, "Erro ao criar a conta: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
//            Logger.getLogger(Tela_Cadastrar_Conta.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    private void inicializaBotaoCadastrarUsuario() {
//        telaCadastrarConta.adicionarAcaoCriarConta((a) -> cadastrarUsuario());
//    }
//    
//    
//    public static void main(String[] args) {
//        Tela_Login tela = new Tela_Login();
//        ControladorSwingTelas o = new ControladorSwingTelas(tela);
//        tela.setVisible(true);
//    }
//}
//
