package kyh.tam.dao;

import java.util.List;
import kyh.tam.domain.Board;

public interface BoardDao {
  public int insert(Board board) throws Exception;

  public List<Board> findAll() throws Exception;

  public Board findByNumber(int number) throws Exception;

  public int update(Board board) throws Exception;

  public int delete(int number) throws Exception;
}
