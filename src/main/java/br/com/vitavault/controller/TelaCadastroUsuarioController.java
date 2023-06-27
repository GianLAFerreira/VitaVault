package br.com.vitavault.controller;

import br.com.vitavault.exceptions.GerenciadorClientesException;
import br.com.vitavault.model.Funcionario;
import br.com.vitavault.swing.Tela_Cadastrar_Conta;
import br.com.vitavault.validation.CadastroContaValidation;
import br.com.vitavault.validation.impl.CadastroContaValidationImpl;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TelaCadastroUsuarioController {
    private Tela_Cadastrar_Conta oTelaCadastroUsuario;
    private CadastroContaValidation cadastroContaValidation = new CadastroContaValidationImpl();

    
    public TelaCadastroUsuarioController(Tela_Cadastrar_Conta oTelaCadastroUsuario) {
        this.oTelaCadastroUsuario = oTelaCadastroUsuario;
        inicializarBotoes();
    }
    
    public void inicializarBotoes() {
        oTelaCadastroUsuario.adicionarAcaoCriarConta((a) -> cadastrarUsuario());
    }
    
    public void cadastrarUsuario() {
        String nome = oTelaCadastroUsuario.getNome();
        String cpfTexto = oTelaCadastroUsuario.getCpf();
        String endereco = oTelaCadastroUsuario.getEndereco();
        String senha = oTelaCadastroUsuario.getSenha();
        String telefoneTexto = oTelaCadastroUsuario.getTelefone();
        
        List<String> mensagens = cadastroContaValidation.validar(nome, cpfTexto, endereco, senha, telefoneTexto);
        
        
        if (!mensagens.isEmpty()) {
            oTelaCadastroUsuario.alertaMensagens(mensagens);
            return;
        }

        String cpf = cpfTexto;
        String telefone = telefoneTexto;
        Funcionario funcionario = new Funcionario(cpf, nome, endereco, telefone, senha);
        
        try {
            funcionario.cadastrarCliente(funcionario);
            JOptionPane.showMessageDialog(oTelaCadastroUsuario, "Conta criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            oTelaCadastroUsuario.resetarCampos();
        } catch (GerenciadorClientesException ex) {
            JOptionPane.showMessageDialog(oTelaCadastroUsuario, "Erro ao criar a conta: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Tela_Cadastrar_Conta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void exibirTela() {
        oTelaCadastroUsuario.exibe();
    }
}
