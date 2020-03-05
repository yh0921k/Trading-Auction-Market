package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.MemberDao;
import kyh.tam.domain.Member;

public class MemberUpdateServlet implements Servlet {
  MemberDao memberDao;

  public MemberUpdateServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    out.write("번호 : " + System.lineSeparator());
    out.write("!{}!" + System.lineSeparator());
    out.flush();

    int number = Integer.parseInt(in.readLine());

    Member oldMember = memberDao.findByNumber(number);
    if (oldMember == null) {
      out.write("Update failed : invalid number" + System.lineSeparator());
      out.flush();
      return;
    }

    Member newMember = new Member();
    newMember.setNumber(oldMember.getNumber());
    out.write(String.format("이름(%s) : \n!{}!\n", oldMember.getName()));
    out.flush();
    newMember.setName(in.readLine());
    out.write(String.format("이메일(%s) : \n!{}!\n", oldMember.getEmail()));
    out.flush();
    newMember.setEmail(in.readLine());
    out.write(String.format("주소(%s) : \n!{}!\n", oldMember.getAddress()));
    out.flush();
    newMember.setAddress(in.readLine());
    out.write(String.format("암호(%s) : \n!{}!\n", oldMember.getPassword()));
    out.flush();
    newMember.setPassword(in.readLine());
    out.write(String.format("사진(%s) : \n!{}!\n", oldMember.getPhoto()));
    out.flush();
    newMember.setPhoto(in.readLine());
    out.write(String.format("전화(%s) : \n!{}!\n", oldMember.getTel()));
    out.flush();
    newMember.setTel(in.readLine());

    if (memberDao.update(newMember) > 0) {
      out.write("Update complete" + System.lineSeparator());
    } else {
      out.write("Update failed" + System.lineSeparator());
    }
    out.flush();
  }
}
