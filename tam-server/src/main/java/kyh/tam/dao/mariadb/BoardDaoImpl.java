package kyh.tam.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import kyh.tam.dao.BoardDao;
import kyh.tam.domain.Board;

public class BoardDaoImpl implements BoardDao {

  SqlSessionFactory sqlSessionFactory;

  public BoardDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Board board) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
      int count = sqlSession.insert("BoardMapper.insertBoard", board);
      sqlSession.commit();
      return count;
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
      return sqlSession.selectList("BoardMapper.selectBoard");
    }
  }

  @Override
  public Board findByNumber(int number) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
      return sqlSession.selectOne("BoardMapper.selectDetail", number);
    }
  }

  @Override
  public int update(Board board) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
      int count = sqlSession.update("BoardMapper.updateBoard", board);
      sqlSession.commit();
      return count;
    }
  }

  @Override
  public int delete(int number) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
      int count = sqlSession.delete("BoardMapper.deleteBoard", number);
      sqlSession.commit();
      return count;
    }
  }
}
