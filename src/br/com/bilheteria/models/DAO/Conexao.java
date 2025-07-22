package br.com.bilheteria.models.DAO;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class Conexao {

    private static final String url = "jdbc:mysql://localhost:3306/bilheteria";
    private static final String user = "root";
    private static final String password = "root";

    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}