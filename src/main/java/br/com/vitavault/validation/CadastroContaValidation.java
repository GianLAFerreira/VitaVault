package br.com.vitavault.validation;

import java.util.List;

public interface CadastroContaValidation {

    List<String> validar(String nome, String cpfTexto, String endereco, String senha, String telefoneTexto);

    List<String> validar(String cpfTexto, String senha);
}
