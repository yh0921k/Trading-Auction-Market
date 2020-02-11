package kyh.tam.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.domain.Stuff;
import kyh.util.Prompt;

public class StuffDetailCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public StuffDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() throws Exception {
    System.out.println("--------------------------------------------------");
    try {
      int number = prompt.inputInt("번호 : ");
      out.writeUTF("/stuff/detail");
      out.writeInt(number);
      out.flush();
      String response = in.readUTF();
      if (response.equals("fail")) {
        System.out.println("Server(fail) : " + in.readUTF());
        return;
      }

      Stuff stuff = (Stuff) in.readObject();
      System.out.printf("번호 : %s\n", stuff.getNumber());
      System.out.printf("물품명 : %s\n", stuff.getName());
      System.out.printf("분류 : %s\n", stuff.getCategory());
      System.out.printf("상태 : %s\n", stuff.getState());
      System.out.printf("가격 : %d\n", stuff.getPrice());
      System.out.printf("판매자 : %s\n", stuff.getSeller());

    } catch (Exception e) {
      System.out.println("[/stuff/detail] : communication error");
    }
  }
}