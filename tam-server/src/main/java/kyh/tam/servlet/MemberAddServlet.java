package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.dao.MemberDao;
import kyh.tam.domain.Member;

public class MemberAddServlet implements Servlet {
  MemberDao memberDao;

  public MemberAddServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      Member member = (Member) in.readObject();

      if (memberDao.insert(member) > 0) {
        out.writeUTF("ok");
      } else {
        out.writeUTF("fail");
        out.writeUTF("같은 번호의 회원이 있습니다.");
      }

    } catch (Exception e) {
      System.out.println("[/member/add] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }
}
