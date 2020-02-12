package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kyh.tam.domain.Stuff;

public class StuffDeleteServlet implements Servlet {
  List<Stuff> stuffs;

  public StuffDeleteServlet(List<Stuff> stuffs) {
    this.stuffs = stuffs;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      int number = in.readInt();

      int index = -1;
      for (int i = 0; i < stuffs.size(); i++) {
        if (stuffs.get(i).getNumber() == number) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        stuffs.remove(index);
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
