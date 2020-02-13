package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.dao.json.StuffJsonFileDao;
import kyh.tam.domain.Stuff;

public class StuffAddServlet implements Servlet {
  StuffJsonFileDao stuffDao;

  public StuffAddServlet(StuffJsonFileDao stuffDao) {
    this.stuffDao = stuffDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      Stuff stuff = (Stuff) in.readObject();

      if (stuffDao.insert(stuff) > 0) {
        out.writeUTF("ok");
      } else {
        out.writeUTF("fail");
        out.writeUTF("같은 번호의 회원이 있습니다.");
      }

    } catch (Exception e) {
      System.out.println("[/stuff/add] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }
}
