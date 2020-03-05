package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.List;
import kyh.tam.dao.MemberDao;
import kyh.tam.domain.Member;

public class MemberSearchServlet implements Servlet {
  MemberDao memberDao;

  public MemberSearchServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    out.write("검색어 : " + System.lineSeparator());
    out.write("!{}!" + System.lineSeparator());
    out.flush();

    String keyword = in.readLine();

    List<Member> members = memberDao.findByKeyword(keyword);
    for (Member member : members) {
      out.write(String.format("%d, %s, %s, %s, %s, %s", member.getNumber(), member.getName(),
          member.getEmail(), member.getAddress(), member.getPhoto(), member.getTel())
          + System.lineSeparator());
    }
    out.flush();
  }
}
