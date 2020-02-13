package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.dao.MemberObjectFileDao;
import kyh.tam.domain.Member;

public class MemberDetailServlet implements Servlet {
  MemberObjectFileDao memberDao;

  public MemberDetailServlet(MemberObjectFileDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      int number = in.readInt();

      Member member = memberDao.findByNumber(number);
      if (member != null) {
        out.writeUTF("ok");
        out.writeObject(member);
      } else {
        out.writeUTF("fail");
        out.writeUTF("해당 번호의 회원이 없습니다.");
      }

    } catch (Exception e) {
      System.out.println("[/member/detail] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }
}
