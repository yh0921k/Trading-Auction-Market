package kyh.tam.dao.json;

import java.util.List;
import kyh.tam.dao.StuffDao;
import kyh.tam.domain.Stuff;

public class StuffJsonFileDao extends AbstractJsonFileDao<Stuff> implements StuffDao {

  public StuffJsonFileDao(String filename) throws Exception {
    super(filename);
  }

  public int insert(Stuff stuff) throws Exception {
    if (indexOf(stuff.getNumber()) > -1)
      return 0;
    list.add(stuff);
    saveData();
    return 1;
  }

  public List<Stuff> findAll() throws Exception {
    return list;
  }

  public Stuff findByNumber(int number) throws Exception {
    int index = indexOf(number);
    if (index == -1)
      return null;
    return list.get(index);
  }

  public int update(Stuff stuff) throws Exception {
    int index = indexOf(stuff.getNumber());
    if (index == -1)
      return 0;

    list.set(index, stuff);
    saveData();
    return 1;
  }

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
