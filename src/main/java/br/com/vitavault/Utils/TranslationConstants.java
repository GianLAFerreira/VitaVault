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

    public static String getMessage(String key) {
        if (messages == null) {
            messages = ResourceBundle.getBundle("resources.translation.vita_vault");
        }
        return messages.getString(key);
    }
}
