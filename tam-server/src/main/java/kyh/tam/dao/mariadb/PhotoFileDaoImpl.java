package kyh.tam.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import kyh.tam.dao.PhotoFileDao;
import kyh.tam.domain.PhotoFile;

public class PhotoFileDaoImpl implements PhotoFileDao {

  SqlSessionFactory sqlSessionFactory;

  public PhotoFileDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(PhotoFile photoFile) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
      int count = sqlSession.insert("PhotoFileMapper.insertPhotoFile", photoFile);
      return count;
    }
  }

  @Override
  public List<PhotoFile> findAll(int boardNumber) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
      return sqlSession.selectList("PhotoFileMapper.selectPhotoFile", boardNumber);
    }
  }

  @Override
  public int deleteAll(int boardNumber) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.delete("PhotoFileMapper.deletePhotoFile", boardNumber);
      return count;
    }
  }
}
