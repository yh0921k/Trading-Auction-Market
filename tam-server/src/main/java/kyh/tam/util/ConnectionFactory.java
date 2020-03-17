package kyh.tam.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
  String jdbcUrl;
  String username;
  String password;

  public ConnectionFactory(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(jdbcUrl, username, password);
  }
}
