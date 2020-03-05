package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.MemberDao;
import kyh.tam.domain.Member;

public class MemberAddServlet implements Servlet {
  MemberDao memberDao;

  public MemberAddServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    Member member = new Member();

    out.write("이름 : \n!{}!\n");
    out.flush();
    member.setName(in.readLine());
    out.write("이메일 : \n!{}!\n");
    out.flush();
    member.setEmail(in.readLine());
    out.write("주소 : \n!{}!\n");
    out.flush();
    member.setAddress(in.readLine());
    out.write("암호 : \n!{}!\n");
    out.flush();
    member.setPassword(in.readLine());
    out.write("사진 : \n!{}!\n");
    out.flush();
    member.setPhoto(in.readLine());
    out.write("전화 : \n!{}!\n");
    out.flush();
    member.setTel(in.readLine());

    if (memberDao.insert(member) > 0)
      out.write("Save complete" + System.lineSeparator());
    else
      out.write("Save failed" + System.lineSeparator());
    out.flush();
  }
}
