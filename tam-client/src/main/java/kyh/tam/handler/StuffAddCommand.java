package kyh.tam.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.domain.Stuff;
import kyh.util.Prompt;

public class StuffAddCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public StuffAddCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() throws Exception {
    System.out.println("--------------------------------------------------");
    Stuff stuff = new Stuff();
    stuff.setNumber(prompt.inputInt("번호 : "));
    stuff.setName(prompt.inputString("물품명 : "));
    stuff.setCategory(prompt.inputString("분류 : "));
    stuff.setState(prompt.inputString("상태 : "));
    stuff.setPrice(prompt.inputInt("가격 : "));
    stuff.setSeller(prompt.inputString("판매자 : "));

    try {
      out.writeUTF("/stuff/add");
      out.writeObject(stuff);
      out.flush();
      String response = in.readUTF();
      if (response.equals("fail")) {
        System.out.println("Server(fail) : " + in.readUTF());
        return;
      }
      System.out.println("Save complete");

    } catch (Exception e) {
      System.out.println("[/stuff/add] : communication error");
    }
  }
}
