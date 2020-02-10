package kyh.tam.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import kyh.tam.domain.Board;
import kyh.util.Prompt;

public class BoardDetailCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public BoardDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
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
      Board board = (Board) in.readObject();
      System.out.printf("번호: %d\n", board.getNumber());
      System.out.printf("제목: %s\n", board.getTitle());
      System.out.printf("등록일: %s\n",
          new SimpleDateFormat("yyyy-MM-dd").format(board.getWriteDate()));
      System.out.printf("조회수: %d\n", board.getViewCount());
      System.out.printf("작성자 : %s\n", board.getWriter());

    } catch (Exception e) {
      System.out.println("[/board/detail] : communication error");
    }
  }
}
