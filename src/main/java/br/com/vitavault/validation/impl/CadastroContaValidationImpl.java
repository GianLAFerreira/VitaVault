package br.com.vitavault.validation.impl;

import br.com.vitavault.Utils.TranslationConstants;
import br.com.vitavault.Utils.Utils;
import br.com.vitavault.dao.FuncionarioRepository;
import br.com.vitavault.dao.impl.FuncionarioRepositoryImpl;
import br.com.vitavault.validation.CadastroContaValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CadastroContaValidationImpl implements CadastroContaValidation {
    private FuncionarioRepository funcionarioRepository;

    public CadastroContaValidationImpl() {
        this.funcionarioRepository = new FuncionarioRepositoryImpl();
    }

    @Override
    public List<String> validar(String nome, String cpfTexto, String endereco, String senha, String telefoneTexto) {
        List<String> mensagens = new ArrayList();
        validarNome(nome, mensagens);
        validarCPF(cpfTexto, mensagens);
        validarTelefone(telefoneTexto, mensagens);
        validarEndereco(endereco, mensagens);
        validarSenha(senha, mensagens);
        validaCpfCadastrado(cpfTexto, mensagens);

        return mensagens;
    }

    public List<String> validar(String cpfTexto, String senha) {
        List<String> mensagens = new ArrayList();

        validaCpfNaoCadastrado(cpfTexto, mensagens);
        if (mensagens.isEmpty()) {
            validarSenhaValida(cpfTexto, senha, mensagens);
        }

        return mensagens;
    }

    private void validarNome(String nome, List<String> mensagens) {
        if (nome.isEmpty()) {
            mensagens.add(TranslationConstants.getMessage(TranslationConstants.NOME_OBRIGATORIO));
        }
    }

    private void validarCPF(String cpfTexto, List<String> mensagens) {
        if (!Utils.apenasNumeros(cpfTexto)) {
            mensagens.add(TranslationConstants.getMessage(TranslationConstants.CPF_COM_NUMEROS_INTEIROS));
        }
        if (!verificarValorCPF(cpfTexto) && Utils.apenasNumeros(cpfTexto)) {
            mensagens.add(TranslationConstants.getMessage(TranslationConstants.CPF_INVALIDO));
        }
    }

    private void validarTelefone(String telefoneTexto, List<String> mensagens) {
        if (!Utils.apenasNumeros(telefoneTexto)) {
            mensagens.add(TranslationConstants.getMessage(TranslationConstants.TELEFONE_COM_NUMEROS_INTEIROS));
        }
    }

    private void validarEndereco(String endereco, List<String> mensagens) {
        if (endereco.isEmpty()) {
            mensagens.add(TranslationConstants.getMessage(TranslationConstants.ENDERECO_OBRIGATORIO));
        }
    }

    private void validarSenha(String senha, List<String> mensagens) {
        if (senha.isEmpty()) {
            mensagens.add(TranslationConstants.getMessage(TranslationConstants.SENHA_OBRIGATORIO));
        }
    }

    public boolean verificarValorCPF(String cpf) {
        // Verifica se o CPF possui 11 dígitos
        if (cpf.length() != 11 || !Utils.apenasNumeros(cpf)) {
            return false;
        }

        // Verifica os dígitos de verificação
        return verificarDigitos(cpf);
    }

    private boolean verificarDigitos(String cpf) {
        // Calcula o primeiro dígito de verificação
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }

        int resto = (soma * 10) % 11;
        if (resto == 10) {
            resto = 0;
        }

        // Verifica o primeiro dígito de verificação
        if (resto != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        // Calcula o segundo dígito de verificação
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }

        resto = (soma * 10) % 11;
        if (resto == 10) {
            resto = 0;
        }

        // Verifica o segundo dígito de verificação
        return resto == Character.getNumericValue(cpf.charAt(10));
    }

    private void validaCpfCadastrado(String cpf, List<String> mensagens) {
        if (!Objects.isNull(funcionarioRepository.buscarFuncionarioByCPF(cpf))) {
            mensagens.add(TranslationConstants.getMessage(TranslationConstants.CPF_JA_CADASTRADO));
        }
    }

    private void validaCpfNaoCadastrado(String cpf, List<String> mensagens) {
        if (Objects.isNull(funcionarioRepository.buscarFuncionarioByCPF(cpf))) {
            mensagens.add(TranslationConstants.getMessage(TranslationConstants.CPF_NAO_CADASTRADO));
        }
    }

    private void validarSenhaValida(String cpf, String senha, List<String> mensagens) {
        if (Objects.isNull(funcionarioRepository.buscarFuncionarioByCPFAndSenha(cpf, senha))) {
            mensagens.add(TranslationConstants.getMessage(TranslationConstants.SENHA_INVALIDA));
        }
    }

}
