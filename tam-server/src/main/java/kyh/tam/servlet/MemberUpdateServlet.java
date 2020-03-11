package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.MemberDao;
import kyh.tam.domain.Member;
import kyh.tam.util.Prompt;

public class MemberUpdateServlet implements Servlet {
  MemberDao memberDao;

  public MemberUpdateServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    int number = Prompt.getInt(in, out, "번호 : ");
    Member oldMember = memberDao.findByNumber(number);
    if (oldMember == null) {
      out.write("Update failed : invalid number" + System.lineSeparator());
      out.flush();
      return;
    }

    Member newMember = new Member();
    newMember.setNumber(oldMember.getNumber());
    newMember.setName(Prompt.getString(in, out, String.format("이름(%s) : ", oldMember.getName()),
        oldMember.getName()));
    newMember.setEmail(Prompt.getString(in, out, String.format("이메일(%s) : ", oldMember.getEmail()),
        oldMember.getEmail()));
    newMember.setAddress(Prompt.getString(in, out,
        String.format("주소(%s) : ", oldMember.getAddress()), oldMember.getAddress()));
    newMember.setPassword(Prompt.getString(in, out,
        String.format("암호(%s) : ", oldMember.getPassword()), oldMember.getPassword()));
    newMember.setPhoto(Prompt.getString(in, out, String.format("사진(%s) : ", oldMember.getPhoto()),
        oldMember.getPhoto()));
    newMember.setTel(Prompt.getString(in, out, String.format("전화(%s) : ", oldMember.getTel()),
        oldMember.getTel()));

    if (memberDao.update(newMember) > 0) {
      out.write("Update complete" + System.lineSeparator());
    } else {
      out.write("Update failed" + System.lineSeparator());
    }
    out.flush();
  }
}
