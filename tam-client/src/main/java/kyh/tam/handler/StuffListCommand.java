package kyh.tam.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kyh.tam.domain.Stuff;

public class StuffListCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;

  public StuffListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {
    System.out.println("--------------------------------------------------");
    try {
      out.writeUTF("/stuff/list");
      out.flush();
      String response = in.readUTF();
      if (response.equals("fail")) {
        System.out.println("Server(fail) : " + in.readUTF());
        return;
      }

      List<Stuff> stuffs = (List<Stuff>) in.readObject();
      for (Stuff s : stuffs)
        System.out.println(s);

    } catch (Exception e) {
      System.out.println("[/stuff/list] : communication error");
    }
  }
}
