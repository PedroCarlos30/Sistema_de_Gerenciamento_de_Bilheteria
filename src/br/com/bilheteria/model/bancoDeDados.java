package br.com.bilheteria.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class bancoDeDados {
    private static final String URL = "jdbc:mysql://localhost:3306/bilheteria";
    private static final String USUARIO = "root";
    private static final String SENHA = "password";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
