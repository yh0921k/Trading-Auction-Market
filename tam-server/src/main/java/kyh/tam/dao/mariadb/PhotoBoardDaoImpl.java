package kyh.tam.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import kyh.tam.dao.PhotoBoardDao;
import kyh.tam.domain.PhotoBoard;

public class PhotoBoardDaoImpl implements PhotoBoardDao {

  SqlSessionFactory sqlSessionFactory;

  public PhotoBoardDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
      int count = sqlSession.insert("PhotoBoardMapper.insertPhotoBoard", photoBoard);
      return count;
    }
  }

  @Override
  public List<PhotoBoard> findAllByStuffNumber(int stuffNumber) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
      return sqlSession.selectList("PhotoBoardMapper.selectPhotoBoard", stuffNumber);
    }
  }

  @Override
  public PhotoBoard findByNo(int number) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
      return sqlSession.selectOne("PhotoBoardMapper.selectDetail", number);
    }
  }

  @Override
  public int update(PhotoBoard photoBoard) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
      int count = sqlSession.update("PhotoBoardMapper.updatePhotoBoard", photoBoard);
      return count;
    }
  }

  @Override
  public int delete(int number) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
      int count = sqlSession.delete("PhotoBoardMapper.deletePhotoBoard", number);
      return count;
    }
  }
}
