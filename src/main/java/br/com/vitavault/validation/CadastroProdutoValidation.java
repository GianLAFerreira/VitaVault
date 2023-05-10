package br.com.vitavault.validation;

import java.util.List;

public interface CadastroProdutoValidation {

    List<String> validar(String nomeProduto, String descricaoProduto, String codigoProduto, String precoProduto, String categoriaProduto);
}
