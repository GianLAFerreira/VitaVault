/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testes;

import java.text.ParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author ariad
 */

public class Testes {
     public static void main(String[] args) throws ParseException {
        // Define a máscara para o telefone
        MaskFormatter mascara = new MaskFormatter("(##) ####-####");

        // Cria um campo de texto formatado com a máscara
        JFormattedTextField campoTelefone = new JFormattedTextField(mascara);

        // Exibe o campo de texto em uma janela
        javax.swing.JOptionPane.showMessageDialog(null, campoTelefone);

        // Obtém o telefone inserido pelo usuário
        String telefone = campoTelefone.getText();

        // Imprime o telefone
        System.out.println("Telefone: " + telefone);
    }
}
