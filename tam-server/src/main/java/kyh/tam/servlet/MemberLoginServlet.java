package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.MemberDao;
import kyh.tam.domain.Member;
import kyh.tam.util.Prompt;

public class MemberLoginServlet implements Servlet {
  MemberDao memberDao;

  public MemberLoginServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    String email = Prompt.getString(in, out, "email : ");
    String password = Prompt.getString(in, out, "password : ");

    Member member = memberDao.findByEmailAndPassword(email, password);
    if (member == null) {
      out.write("회원 정보가 유효하지 않습니다." + System.lineSeparator());
    }
    out.write(String.format("%s님 환영합니다.", member.getName()) + System.lineSeparator());
    out.flush();
  }
}
