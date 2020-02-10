package kyh.tam.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import kyh.tam.domain.Board;
import kyh.util.Prompt;

public class BoardAddCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public BoardAddCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() throws Exception {
    System.out.println("--------------------------------------------------");

    Board board = new Board();
    board.setNumber(prompt.inputInt("번호 : "));
    board.setTitle(prompt.inputString("내용 : "));
    board.setWriteDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);

    try {
      out.writeUTF("/board/add");
      out.writeObject(board);
      out.flush();
      String response = in.readUTF();
      if (response.equals("fail")) {
        System.out.println("Server(fail) : " + in.readUTF());
        return;
      }
      System.out.println("Save complete");

    } catch (Exception e) {
      System.out.println("[/board/add] : communication error");
    }
  }
}
