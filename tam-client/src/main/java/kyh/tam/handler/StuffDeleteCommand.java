package kyh.tam.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.util.Prompt;

public class StuffDeleteCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public StuffDeleteCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() throws Exception {
    System.out.println("--------------------------------------------------");
    try {
      int number = prompt.inputInt("번호 : ");

      out.writeUTF("/stuff/delete");
      out.writeInt(number);
      out.flush();

      String response = in.readUTF();

      if (response.equals("fail")) {
        System.out.println("Server(fail) : " + in.readUTF());
        return;
      }
      System.out.println("Delete complete");

    } catch (Exception e) {
      System.out.println("[/stuff/delete] : communication error");
    }
  }
}
