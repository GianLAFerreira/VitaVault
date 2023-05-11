package br.com.vitavault.validation.impl;

import br.com.vitavault.Utils.TranslationConstants;
import br.com.vitavault.Utils.Utils;
import br.com.vitavault.validation.MovimentacaoEstoqueValidation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoEstoqueValidationImpl implements MovimentacaoEstoqueValidation {
    @Override
    public List<String> validar(String data, String quantidade) {
        List<String> mensagens = new ArrayList();
        validarData(data, mensagens);
        validarQuantidade(quantidade, mensagens);


        return mensagens;
    }

    private void validarData(String data, List<String> mensagens) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate.parse(data, formatter);
        } catch (DateTimeParseException e) {
            mensagens.add(TranslationConstants.getMessage(TranslationConstants.DATA_MOVIMENTACAO_INVALIDA));
        }
    }

    private void validarQuantidade(String quantidade, List<String> mensagens) {
        if (!Utils.apenasNumeros(quantidade)) {
            mensagens.add(TranslationConstants.getMessage(TranslationConstants.QUANTIDADE_MOVIMENTACAO_INVALIDA));
        }
    }
}
