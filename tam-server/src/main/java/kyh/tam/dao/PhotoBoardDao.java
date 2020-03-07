package kyh.tam.dao;

import java.util.List;
import kyh.tam.domain.PhotoBoard;

public interface PhotoBoardDao {
  public int insert(PhotoBoard photoBoard) throws Exception;

  public List<PhotoBoard> findAllByStuffNumber(int stuffNumber) throws Exception;

  public PhotoBoard findByNo(int number) throws Exception;

  public int update(PhotoBoard photoBoard) throws Exception;

  public int delete(int number) throws Exception;
}
