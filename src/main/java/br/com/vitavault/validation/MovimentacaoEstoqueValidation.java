package br.com.vitavault.validation;

import java.util.List;

public interface MovimentacaoEstoqueValidation {

    List<String> validar(String data, String quantidade);
}
