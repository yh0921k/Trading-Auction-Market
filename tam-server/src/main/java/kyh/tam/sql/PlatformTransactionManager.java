package kyh.tam.sql;

import java.sql.Connection;
import java.sql.SQLException;
import kyh.tam.util.ConnectionFactory;

public class PlatformTransactionManager {
  ConnectionFactory connectionFactory;

  public PlatformTransactionManager(ConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
  }

  public void beginTransaction() throws SQLException {
    Connection con = connectionFactory.getConnection();
    con.setAutoCommit(false);
  }

  public void commit() throws SQLException {
    Connection con = connectionFactory.getConnection();
    con.commit();
    con.setAutoCommit(true);
  }

  public void rollback() throws SQLException {
    Connection con = connectionFactory.getConnection();
    con.rollback();
    con.setAutoCommit(true);
  }
}
