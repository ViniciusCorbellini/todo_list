package com.mycompany.todo_list.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Vinicius Corbellini
 */
public class ConnectionDAO {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/todo_list";
        String user = "root";
        String password = "V1n1MySQLroot!13022007@";

        return DriverManager.getConnection(url, user, password); // obtem a conexao
    }

    public void createDB() throws SQLException, IOException {
        String urlSemBanco = "jdbc:mysql://localhost:3306/";
        Connection con = DriverManager.getConnection(urlSemBanco, "root", "V1n1MySQLroot!13022007@");

        String relativePath = "src/main/java/com/mycompany/todo_list/resources/query-todo_list.sql";
        InputStream input = new FileInputStream(relativePath);

        if (input == null) {
            throw new FileNotFoundException("Arquivo SQL não encontrado!");
        }

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }

        String[] queries = sb.toString().split(";");

        Statement stmt = con.createStatement();
        for (String query : queries) {
            if (!query.trim().isEmpty()) {
                stmt.execute(query.trim());
            }
        }
        System.out.println("Queries executadas com sucesso!");
    }
}
