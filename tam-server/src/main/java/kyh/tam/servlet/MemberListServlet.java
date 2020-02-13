package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.dao.json.MemberJsonFileDao;

public class MemberListServlet implements Servlet {
  MemberJsonFileDao memberDao;

  public MemberListServlet(MemberJsonFileDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("ok");
    out.reset();
    out.writeObject(memberDao.findAll());
  }
}
