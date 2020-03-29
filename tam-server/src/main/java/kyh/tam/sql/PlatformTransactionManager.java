package kyh.tam.sql;

import java.sql.SQLException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class PlatformTransactionManager {
  SqlSessionFactory sqlSessionFactory;

  public PlatformTransactionManager(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  public void beginTransaction() throws SQLException {
    ((SqlSessionFactoryProxy) sqlSessionFactory).closeSession();
    sqlSessionFactory.openSession(false);
  }

  public void commit() throws SQLException {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    sqlSession.commit();
  }

  public void rollback() throws SQLException {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    sqlSession.rollback();
  }
}
