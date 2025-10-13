package org.stofli.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class DbUtil {

    public static Connection getConnection() throws SQLException {
        Dotenv dotenv = Dotenv.load();
        String host = dotenv.get("NEON_HOST");
        String port = dotenv.get("NEON_PORT");
        String db = dotenv.get("NEON_DB");
        String user = dotenv.get("NEON_USER");
        String pass = dotenv.get("NEON_PASSWORD");
        // String uri = dotenv.get("NEON_URI");
        String url = "jdbc:postgresql://" + host + ":" + port + "/" + db + "?sslmode=require";

        return DriverManager.getConnection(url, user, pass);
    }
}
