package kyh.tam.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import kyh.tam.sql.ConnectionProxy;

public class ConnectionFactory {
  String jdbcUrl;
  String username;
  String password;

  ThreadLocal<Connection> connectionLocal = new ThreadLocal<>();

  public ConnectionFactory(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  public Connection getConnection() throws SQLException {
    Connection con = connectionLocal.get();
    if (con != null) {
      System.out.println("Return connection object from connectionLocal");
      return con;
    }

    con = new ConnectionProxy(DriverManager.getConnection(jdbcUrl, username, password));
    System.out.println("Return new connection");
    connectionLocal.set(con);
    return con;
  }

  public Connection removeConnection() {
    Connection con = connectionLocal.get();
    if (con != null) {
      connectionLocal.remove();
      System.out.println("Removed connection object stored in thread");
    }
    return con;
  }
}
