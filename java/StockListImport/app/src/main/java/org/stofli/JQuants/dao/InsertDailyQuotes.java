package org.stofli.jquants.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.stofli.jquants.client.ApiClient;
import org.stofli.jquants.util.DatabaseConnection;
import org.stofli.jquants.util.PropertiesUtil;

public class InsertDailyQuotes {

  public InsertDailyQuotes() {
  }

  public void allCompany() throws IOException, InterruptedException {
    try (Connection conn = DatabaseConnection.getConnection()) {
      String sql = "SELECT * FROM stock.company";

      try (PreparedStatement statement = conn.prepareStatement(sql)) {
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
          Properties prop = PropertiesUtil.loadProperties();
          ApiClient client = new ApiClient(prop.getProperty("mailAdress"), prop.getProperty("password"));
          String code = rs.getString("code");
          client.getDailyQuates(code, "20240306");
        }
      }
   } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
