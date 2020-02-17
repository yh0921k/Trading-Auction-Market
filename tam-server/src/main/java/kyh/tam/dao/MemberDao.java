package kyh.tam.dao;

import java.util.List;
import kyh.tam.domain.Member;

public interface MemberDao {
  public int insert(Member board) throws Exception;

  public List<Member> findAll() throws Exception;

  public Member findByNumber(int no) throws Exception;

  public int update(Member board) throws Exception;

  public int delete(int no) throws Exception;
}

