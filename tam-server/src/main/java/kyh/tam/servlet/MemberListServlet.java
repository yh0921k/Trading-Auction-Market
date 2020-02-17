package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.dao.MemberDao;

public class MemberListServlet implements Servlet {
  MemberDao memberDao;

  public MemberListServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("ok");
    out.reset();
    out.writeObject(memberDao.findAll());
  }
}
