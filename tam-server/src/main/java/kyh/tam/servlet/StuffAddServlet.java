package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kyh.tam.domain.Stuff;

public class StuffAddServlet implements Servlet {
  List<Stuff> stuffs;

  public StuffAddServlet(List<Stuff> stuffs) {
    this.stuffs = stuffs;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      Stuff stuff = (Stuff) in.readObject();

      int i = 0;
      for (; i < stuffs.size(); i++) {
        if (stuffs.get(i).getNumber() == stuff.getNumber()) {
          break;
        }
      }

      if (i == stuffs.size()) {
        stuffs.add(stuff);
        out.writeUTF("ok");

      } else {
        out.writeUTF("fail");
        out.writeUTF("같은 번호의 물품이 있습니다.");
      }

    } catch (Exception e) {
      System.out.println("[/stuff/add] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }
}
