package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.dao.json.StuffJsonFileDao;
import kyh.tam.domain.Stuff;

public class StuffDetailServlet implements Servlet {
  StuffJsonFileDao stuffDao;

  public StuffDetailServlet(StuffJsonFileDao stuffDao) {
    this.stuffDao = stuffDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      int number = in.readInt();

      Stuff stuff = stuffDao.findByNumber(number);
      if (stuff != null) {
        out.writeUTF("ok");
        out.writeObject(stuff);
      } else {
        out.writeUTF("fail");
        out.writeUTF("해당 번호의 물품이 없습니다.");
      }

    } catch (Exception e) {
      System.out.println("[/stuff/detail] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }
}