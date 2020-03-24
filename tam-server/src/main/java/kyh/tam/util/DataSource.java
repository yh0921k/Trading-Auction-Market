package kyh.tam.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import kyh.tam.sql.ConnectionProxy;

public class DataSource {
  String jdbcUrl;
  String username;
  String password;

  ThreadLocal<Connection> connectionLocal = new ThreadLocal<>();
  ArrayList<Connection> connectionPool = new ArrayList<>();

  public DataSource(String jdbcUrl, String username, String password) {
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

    if (connectionPool.size() > 0) {
      con = connectionPool.remove(0);
      System.out.println("Return connection Object from connectionPool");
    } else {
      con = new ConnectionProxy(DriverManager.getConnection(jdbcUrl, username, password));
      System.out.println("Return new connection");
    }
    connectionLocal.set(con);
    System.out.printf("Current number of DataSource(connectionPool) : %d\n", connectionPool.size());
    return con;
  }

  public Connection removeConnection() {
    Connection con = connectionLocal.get();
    if (con != null) {
      connectionLocal.remove();
      System.out.println("Removed connection object stored in thread");
      connectionPool.add(con);
    }
    System.out.printf("Current number of DataSource(connectionPool) : %d\n", connectionPool.size());
    return con;
  }

  public void clean() {
    while (connectionPool.size() > 0) {
      try {
        ((ConnectionProxy) connectionPool.remove(0)).realClose();
      } catch (Exception e) {
        System.out.println("[DataSource.java] : clean()");
        e.printStackTrace();
      }
    }
  }
}
