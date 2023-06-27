package br.com.vitavault.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    private static Connection connection = null;
    private static final String url = "jdbc:postgresql://localhost/VitaVault";
    private static final String usuario = "username";
    private static final String senha = "password";

    public static Connection conectar() {
        try {
            connection = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

    public static void descontecar() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
