package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.MemberDao;
import kyh.tam.domain.Member;
import kyh.tam.util.Prompt;

public class MemberDetailServlet implements Servlet {
  MemberDao memberDao;

  public MemberDetailServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    int number = Prompt.getInt(in, out, "번호 : ");
    Member member = memberDao.findByNumber(number);
    if (member != null) {
      out.write("번호 : " + member.getNumber() + System.lineSeparator());
      out.write("이름 : " + member.getName() + System.lineSeparator());
      out.write("메일 : " + member.getEmail() + System.lineSeparator());
      out.write("주소 : " + member.getAddress() + System.lineSeparator());
      out.write("암호 : " + member.getPassword() + System.lineSeparator());
      out.write("사진 : " + member.getPhoto() + System.lineSeparator());
      out.write("전화 : " + member.getTel() + System.lineSeparator());
      out.write("등록일 : " + member.getRegisteredDate() + System.lineSeparator());
    } else {
      out.write("Read failed : invalid number" + System.lineSeparator());
    }
    out.flush();
  }
}
