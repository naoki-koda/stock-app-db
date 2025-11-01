package org.stofli.database.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class DbUtil {

    private static DBConfig dbConfig = load();
    
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://" + dbConfig.host() + ":" + dbConfig.port() + "/" + dbConfig.db() + "?sslmode=require";
        return DriverManager.getConnection(url, dbConfig.user(), dbConfig.pass());
    }

    private static DBConfig load() {
        Dotenv env = Dotenv.load();
        return new DBConfig(env.get("NEON_HOST"), env.get("NEON_PORT"), env.get("NEON_DB"), env.get("NEON_USER"), env.get("NEON_PASSWORD"));
    }
    public static record DBConfig(String host, String port, String db, String user, String pass) {
    }
}
