package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.MemberDao;
import kyh.tam.domain.Member;
import kyh.tam.util.Prompt;

public class MemberAddServlet implements Servlet {
  MemberDao memberDao;

  public MemberAddServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    Member member = new Member();
    member.setName(Prompt.getString(in, out, "이름 : "));
    member.setEmail(Prompt.getString(in, out, "이메일 : "));
    member.setAddress(Prompt.getString(in, out, "주소 : "));
    member.setPassword(Prompt.getString(in, out, "암호 : "));
    member.setPhoto(Prompt.getString(in, out, "사진 : "));
    member.setTel(Prompt.getString(in, out, "전화 : "));

    if (memberDao.insert(member) > 0)
      out.write("Save complete" + System.lineSeparator());
    else
      out.write("Save failed" + System.lineSeparator());
    out.flush();
  }
}
