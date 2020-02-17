package kyh.tam.dao;

import java.util.List;
import kyh.tam.domain.Board;

public class BoardObjectFileDao extends AbstractObjectFileDao<Board> implements BoardDao {

  public BoardObjectFileDao(String filename) throws Exception {
    super(filename);
  }

  @Override
  public int insert(Board board) throws Exception {
    if (indexOf(board.getNumber()) > -1)
      return 0;
    list.add(board);
    saveData();
    return 1;
  }

  @Override
  public List<Board> findAll() throws Exception {
    return list;
  }

  @Override
  public Board findByNumber(int number) throws Exception {
    int index = indexOf(number);
    if (index == -1)
      return null;
    return list.get(index);
  }

  @Override
  public int update(Board board) throws Exception {
    int index = indexOf(board.getNumber());
    if (index == -1)
      return 0;

    list.set(index, board);
    saveData();
    return 1;
  }

  @Override
  public int delete(int number) throws Exception {
    int index = indexOf(number);
    if (index == -1)
      return 0;

    list.remove(index);
    saveData();
    return 1;
  }

  @Override
  protected <K> int indexOf(K key) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNumber() == (int) key) {
        return i;
      }
    }
    return -1;
  }
}
