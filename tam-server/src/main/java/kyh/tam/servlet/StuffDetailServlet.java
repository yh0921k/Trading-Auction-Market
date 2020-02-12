package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kyh.tam.domain.Stuff;

public class StuffDetailServlet implements Servlet {
  List<Stuff> stuffs;

  public StuffDetailServlet(List<Stuff> stuffs) {
    this.stuffs = stuffs;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      int number = in.readInt();

      Stuff stuff = null;
      for (Stuff l : stuffs) {
        if (l.getNumber() == number) {
          stuff = l;
          break;
        }
      }

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
