package kyh.tam.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import kyh.tam.dao.StuffDao;
import kyh.tam.domain.Stuff;

public class StuffDaoImpl implements StuffDao {

  SqlSessionFactory sqlSessionFactory;

  public StuffDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Stuff stuff) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
      int count = sqlSession.insert("StuffMapper.insertStuff", stuff);
      sqlSession.commit();
      return count;
    }
  }

  @Override
  public List<Stuff> findAll() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
      return sqlSession.selectList("StuffMapper.selectStuff");
    }
  }

  @Override
  public Stuff findByNumber(int number) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
      return sqlSession.selectOne("StuffMapper.selectDetail", number);
    }
  }

  @Override
  public int update(Stuff stuff) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
      int count = sqlSession.update("StuffMapper.updateStuff", stuff);
      sqlSession.commit();
      return count;
    }
  }

  @Override
  public int delete(int number) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
      int count = sqlSession.delete("StuffMapper.deleteStuff", number);
      sqlSession.commit();
      return count;
    }
  }
}
