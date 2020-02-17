package kyh.tam.dao;

import java.util.List;
import kyh.tam.domain.Stuff;

public class StuffObjectFileDao extends AbstractObjectFileDao<Stuff> implements StuffDao {

  public StuffObjectFileDao(String filename) throws Exception {
    super(filename);
  }

  @Override
  public int insert(Stuff stuff) throws Exception {
    if (indexOf(stuff.getNumber()) > -1)
      return 0;
    list.add(stuff);
    saveData();
    return 1;
  }

  @Override
  public List<Stuff> findAll() throws Exception {
    return list;
  }

  @Override
  public Stuff findByNumber(int number) throws Exception {
    int index = indexOf(number);
    if (index == -1)
      return null;
    return list.get(index);
  }

  @Override
  public int update(Stuff stuff) throws Exception {
    int index = indexOf(stuff.getNumber());
    if (index == -1)
      return 0;

    list.set(index, stuff);
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
