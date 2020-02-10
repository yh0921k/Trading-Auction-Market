package kyh.tam.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import kyh.tam.domain.Board;
import kyh.util.Prompt;

public class BoardUpdateCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public BoardUpdateCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() throws Exception {
    System.out.println("--------------------------------------------------");
    try {
      int number = prompt.inputInt("번호 : ");
      out.writeUTF("/board/detail");
      out.writeInt(number);
      out.flush();

      String response = in.readUTF();
      if (response.equals("fail")) {
        System.out.println("Server(fail) : " + in.readUTF());
        return;
      }

      Board oldBoard = (Board) in.readObject();
      Board newBoard = new Board();

      newBoard.setNumber(oldBoard.getNumber());
      newBoard.setViewCount(oldBoard.getViewCount());
      newBoard.setWriteDate(new Date(System.currentTimeMillis()));
      newBoard.setTitle(
          prompt.inputString(String.format("제목(%s) : ", oldBoard.getTitle()), oldBoard.getTitle()));

      if (newBoard.equals(oldBoard)) {
        System.out.println("Update cancel");
        return;
      }

      out.writeUTF("/board/update");
      out.writeObject(newBoard);
      out.flush();
      response = in.readUTF();
      if (response.equals("fail")) {
        System.out.println("Server(fail) : " + in.readUTF());
        return;
      }
      System.out.println("Update complete");
    } catch (Exception e) {
      System.out.println("[/board/update] : communication error");
    }
  }
}
