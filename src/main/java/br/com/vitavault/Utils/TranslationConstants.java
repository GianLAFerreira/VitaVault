package br.com.vitavault.Utils;

import java.util.ResourceBundle;

public class TranslationConstants {
    private static ResourceBundle messages;

    public TranslationConstants() {
    }

    public static final String NOME_OBRIGATORIO = "vita_vault.nome_obrigatorio";
    public static final String CPF_COM_NUMEROS_INTEIROS = "vita_vault.cpf_numeros_inteiros";
    public static final String CPF_INVALIDO = "vita_vault.cpf_invalido";
    public static final String TELEFONE_COM_NUMEROS_INTEIROS = "vita_vault.telefone_numeros_inteiros";
    public static final String ENDERECO_OBRIGATORIO = "vita_vault.endereco_obrigatorio";
    public static final String SENHA_OBRIGATORIO = "vita_vault.senha_obrigatorio";
    public static final String NOME_PRODUTO_OBRIGATORIO = "vita_vault.nome_produto_obrigatorio";
    public static final String DESCRICAO_PRODUTO_OBRIGATORIO = "vita_vault.descricao_produto_obrigatorio";
    public static final String CODIGO_PRODUTO_COM_NUMEROS_INTEIROS = "vita_vault.codigo_produto_numero_inteiros";
    public static final String CODIGO_PRODUTO_OBRIGATORIO = "vita_vault.codigo_produto_obrigatorio";
    public static final String PRECO_PRODUTO_COM_NUMEROS_INTEIROS = "vita_vault.preco_produto_numero_inteiros";
    public static final String PRECO_PRODUTO_OBRIGATORIO = "vita_vault.preco_produto_obrigatorio";
    public static final String CATEGORIA_PRODUTO_OBRIGATORIO = "vita_vault.categoria_produto_obrigatorio";
    public static final String DATA_MOVIMENTACAO_INVALIDA = "vita_vault.data_movimentacao_invalida";
    public static final String QUANTIDADE_MOVIMENTACAO_INVALIDA = "vita_vault.quantidade_movimentacao_invalida";

    public static String getMessage(String key) {
        if (messages == null) {
            messages = ResourceBundle.getBundle("resources.translation.vita_vault");
        }
        return messages.getString(key);
    }
}
