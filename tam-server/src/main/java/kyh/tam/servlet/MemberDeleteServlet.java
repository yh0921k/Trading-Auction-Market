package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.dao.MemberDao;

public class MemberDeleteServlet implements Servlet {
  MemberDao memberDao;

  public MemberDeleteServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      int number = in.readInt();

      if (memberDao.delete(number) > 0) {
        out.writeUTF("ok");
      } else {
        out.writeUTF("fail");
        out.writeUTF("해당 번호의 회원이 없습니다.");
      }
    } catch (Exception e) {
      System.out.println("[/member/delete] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }
}
