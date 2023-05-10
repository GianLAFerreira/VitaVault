package br.com.vitavault.validation.impl;

import br.com.vitavault.Utils.TranslationConstants;
import br.com.vitavault.Utils.Utils;
import br.com.vitavault.validation.CadastroProdutoValidation;

import java.util.ArrayList;
import java.util.List;

public class CadastroProdutoValidationImpl implements CadastroProdutoValidation {
    @Override
    public List<String> validar(String nomeProduto, String descricaoProduto, String codigoProduto, String precoProduto, String categoriaProduto) {
        List<String> mensagens = new ArrayList();
        validarNomeProduto(nomeProduto, mensagens);
        validarDescricaoProduto(descricaoProduto, mensagens);
        validarCodigoProduto(codigoProduto, mensagens);
        validarPrecoProduto(precoProduto, mensagens);
        validarCategoria(categoriaProduto, mensagens);

        return mensagens;
    }

    private void validarNomeProduto(String nomeProduto, List<String> mensagens) {
        if (nomeProduto.isEmpty()) {
            mensagens.add(TranslationConstants.getMessage(TranslationConstants.NOME_PRODUTO_OBRIGATORIO));
        }
    }

    private void validarDescricaoProduto(String descricaoProduto, List<String> mensagens) {
        if (descricaoProduto.isEmpty()) {
            mensagens.add(TranslationConstants.getMessage(TranslationConstants.DESCRICAO_PRODUTO_OBRIGATORIO));
        }
    }

    private void validarCodigoProduto(String codigoProduto, List<String> mensagens) {
        if (!Utils.apenasNumeros(codigoProduto)) {
            mensagens.add(TranslationConstants.getMessage(TranslationConstants.CODIGO_PRODUTO_COM_NUMEROS_INTEIROS));
        }

        if (codigoProduto.isEmpty() && Utils.apenasNumeros(codigoProduto)) {
            mensagens.add(TranslationConstants.getMessage(TranslationConstants.CODIGO_PRODUTO_OBRIGATORIO));
        }
    }

    private void validarPrecoProduto(String precoProduto, List<String> mensagens) {
        if (!Utils.apenasNumeros(precoProduto)) {
            mensagens.add(TranslationConstants.getMessage(TranslationConstants.PRECO_PRODUTO_COM_NUMEROS_INTEIROS));
        }

        if (precoProduto.isEmpty() && Utils.apenasNumeros(precoProduto)) {
            mensagens.add(TranslationConstants.getMessage(TranslationConstants.PRECO_PRODUTO_OBRIGATORIO));
        }
    }

    private void validarCategoria(String categoriaProduto, List<String> mensagens) {
        if (categoriaProduto.isEmpty()) {
            mensagens.add(TranslationConstants.getMessage(TranslationConstants.CATEGORIA_PRODUTO_OBRIGATORIO));
        }
    }

}
