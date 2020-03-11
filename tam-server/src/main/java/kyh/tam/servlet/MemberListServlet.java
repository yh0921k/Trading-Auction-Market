package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.List;
import kyh.tam.dao.MemberDao;
import kyh.tam.domain.Member;

public class MemberListServlet implements Servlet {
  MemberDao memberDao;

  public MemberListServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    List<Member> members = memberDao.findAll();
    for (Member m : members) {
      out.write(String.format("%d, %s, %s, %s, %s, %s", m.getNumber(), m.getName(), m.getEmail(),
          m.getAddress(), m.getPhoto(), m.getTel()) + System.lineSeparator());
    }
    out.flush();
  }
}
