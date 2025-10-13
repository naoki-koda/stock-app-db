package org.stofli.jquants.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/stock";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql";
    private String mailAdress = "";
    private String password = "";
    private static Connection connection;

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Load the JDBC driver (optional in some cases)
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Establish a new connection if one does not exist
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                throw new SQLException("Error establishing database connection", e);
            }
        }
        return connection;
    }
}
