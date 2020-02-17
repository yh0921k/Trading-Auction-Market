package kyh.tam.dao.json;

import java.util.List;
import kyh.tam.dao.MemberDao;
import kyh.tam.domain.Member;

public class MemberJsonFileDao extends AbstractJsonFileDao<Member> implements MemberDao {

  public MemberJsonFileDao(String filename) throws Exception {
    super(filename);
  }

  public int insert(Member member) throws Exception {
    if (indexOf(member.getNumber()) > -1)
      return 0;
    list.add(member);
    saveData();
    return 1;
  }

  public List<Member> findAll() throws Exception {
    return list;
  }

  public Member findByNumber(int number) throws Exception {
    int index = indexOf(number);
    if (index == -1)
      return null;
    return list.get(index);
  }

  public int update(Member member) throws Exception {
    int index = indexOf(member.getNumber());
    if (index == -1)
      return 0;

    list.set(index, member);
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
