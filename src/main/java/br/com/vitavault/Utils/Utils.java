package br.com.vitavault.Utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static boolean apenasNumeros(String s) {
        return s.matches("\\d+");
    }

    public static LocalDate formatarData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(data, formatter);
    }

    public static Long formatarQuantidade(String quantidade) {
        return Long.parseLong(quantidade);
    }
}
