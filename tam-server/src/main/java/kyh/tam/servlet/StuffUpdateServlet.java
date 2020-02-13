package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.dao.StuffObjectFileDao;
import kyh.tam.domain.Stuff;

public class StuffUpdateServlet implements Servlet {
  StuffObjectFileDao stuffDao;

  public StuffUpdateServlet(StuffObjectFileDao stuffDao) {
    this.stuffDao = stuffDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      Stuff stuff = (Stuff) in.readObject();

      if (stuffDao.update(stuff) > 0) {
        out.writeUTF("ok");
      } else {
        out.writeUTF("fail");
        out.writeUTF("해당 번호의 물품이 없습니다.");
      }

    } catch (Exception e) {
      System.out.println("[/stuff/update] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }
}
