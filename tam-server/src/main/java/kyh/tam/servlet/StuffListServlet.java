package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.dao.json.StuffJsonFileDao;

public class StuffListServlet implements Servlet {
  StuffJsonFileDao stuffDao;

  public StuffListServlet(StuffJsonFileDao stuffDao) {
    this.stuffDao = stuffDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("ok");
    out.reset();
    out.writeObject(stuffDao.findAll());
  }
}
