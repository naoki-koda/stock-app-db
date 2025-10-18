package org.stofli.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class DbUtil {
    private static Connection connection;
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            Dotenv dotenv = Dotenv.load();
            String host = dotenv.get("NEON_HOST");
            String port = dotenv.get("NEON_PORT");
            String db = dotenv.get("NEON_DB");
            String user = dotenv.get("NEON_USER");
            String pass = dotenv.get("NEON_PASSWORD");
            String url = "jdbc:postgresql://" + host + ":" + port + "/" + db + "?sslmode=require";
            try {
                connection = DriverManager.getConnection(url, user, pass);
            } catch ( SQLException e ) {
                throw new SQLException("Error establishing database connection", e);
            }
        }
        return connection;
    }
}
