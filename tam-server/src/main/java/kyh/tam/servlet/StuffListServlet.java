package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.dao.StuffDao;

public class StuffListServlet implements Servlet {
  StuffDao stuffDao;

  public StuffListServlet(StuffDao stuffDao) {
    this.stuffDao = stuffDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("ok");
    out.reset();
    out.writeObject(stuffDao.findAll());
  }
}
