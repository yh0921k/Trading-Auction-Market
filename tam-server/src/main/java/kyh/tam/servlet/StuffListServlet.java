package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kyh.tam.domain.Stuff;

public class StuffListServlet implements Servlet {
  List<Stuff> stuffs;

  public StuffListServlet(List<Stuff> stuffs) {
    this.stuffs = stuffs;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("ok");
    out.reset();
    out.writeObject(stuffs);
  }
}
