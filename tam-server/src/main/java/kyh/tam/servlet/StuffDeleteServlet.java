package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.dao.json.StuffJsonFileDao;

public class StuffDeleteServlet implements Servlet {
  StuffJsonFileDao stuffDao;

  public StuffDeleteServlet(StuffJsonFileDao stuffDao) {
    this.stuffDao = stuffDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      int number = in.readInt();

      if (stuffDao.delete(number) > 0) {
        out.writeUTF("ok");
      } else {
        out.writeUTF("fail");
        out.writeUTF("해당 번호의 물품이 없습니다.");
      }
    } catch (Exception e) {
      System.out.println("[/stuff/delete] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }
}
