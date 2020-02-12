package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kyh.tam.domain.Stuff;

public class StuffUpdateServlet implements Servlet {
  List<Stuff> stuffs;

  public StuffUpdateServlet(List<Stuff> stuffs) {
    this.stuffs = stuffs;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      Stuff stuff = (Stuff) in.readObject();

      int index = -1;
      for (int i = 0; i < stuffs.size(); i++) {
        if (stuffs.get(i).getNumber() == stuff.getNumber()) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        stuffs.set(index, stuff);
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
