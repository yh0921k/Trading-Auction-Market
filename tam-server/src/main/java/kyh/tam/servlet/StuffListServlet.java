package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.dao.StuffObjectFileDao;

public class StuffListServlet implements Servlet {
  StuffObjectFileDao stuffDao;

  public StuffListServlet(StuffObjectFileDao stuffDao) {
    this.stuffDao = stuffDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("ok");
    out.reset();
    out.writeObject(stuffDao.findAll());
  }
}
