package kyh.tam.dao;

import java.util.List;
import kyh.tam.domain.PhotoFile;

public interface PhotoFileDao {
  public int insert(PhotoFile photoBoard) throws Exception;

  public List<PhotoFile> findAll(int boardNumber) throws Exception;

  public int deleteAll(int boardNumber) throws Exception;
}
