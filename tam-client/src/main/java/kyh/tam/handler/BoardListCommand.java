package kyh.tam.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kyh.tam.domain.Board;

public class BoardListCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;

  public BoardListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {
    System.out.println("--------------------------------------------------");
    try {
      out.writeUTF("/board/list");
      out.flush();
      String response = in.readUTF();
      if (response.equals("fail")) {
        System.out.println("Server(fail) : " + in.readUTF());
        return;
      }

      List<Board> boards = (List<Board>) in.readObject();
      for (Board b : boards)
        System.out.println(b);

    } catch (Exception e) {
      System.out.println("[/board/list] : communication error");
    }
  }
}
