package kyh.tam.sql;

import java.sql.Connection;
import java.sql.SQLException;
import kyh.tam.util.DataSource;

public class PlatformTransactionManager {
  DataSource dataSource;

  public PlatformTransactionManager(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void beginTransaction() throws SQLException {
    Connection con = dataSource.getConnection();
    con.setAutoCommit(false);
  }

  public void commit() throws SQLException {
    Connection con = dataSource.getConnection();
    con.commit();
    con.setAutoCommit(true);
  }

  public void rollback() throws SQLException {
    Connection con = dataSource.getConnection();
    con.rollback();
    con.setAutoCommit(true);
  }
}
