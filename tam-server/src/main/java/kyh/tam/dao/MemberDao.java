package kyh.tam.dao;

import java.util.List;
import kyh.tam.domain.Member;

public interface MemberDao {
  public int insert(Member member) throws Exception;

  public List<Member> findAll() throws Exception;

  public Member findByNumber(int number) throws Exception;

  default List<Member> findByKeyword(String keyword) throws Exception {
    return null;
  }

  public int update(Member member) throws Exception;

  public int delete(int number) throws Exception;
}

