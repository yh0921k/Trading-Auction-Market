package kyh.tam.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.domain.Stuff;
import kyh.util.Prompt;

public class StuffUpdateCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public StuffUpdateCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
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

      Stuff oldStuff = (Stuff) in.readObject();
      Stuff newStuff = new Stuff();

      newStuff.setNumber(oldStuff.getNumber());

      newStuff.setName(
          prompt.inputString(String.format("물품명(%s) : ", oldStuff.getName()), oldStuff.getName()));

      newStuff.setCategory(prompt.inputString(String.format("분류(%s) : ", oldStuff.getCategory()),
          oldStuff.getCategory()));

      newStuff.setState(
          prompt.inputString(String.format("상태(%s) : ", oldStuff.getState()), oldStuff.getState()));

      newStuff.setPrice(
          prompt.inputInt(String.format("가격(%d) : ", oldStuff.getPrice()), oldStuff.getPrice()));

      newStuff.setSeller(prompt.inputString(String.format("판매자(%s) : ", oldStuff.getSeller()),
          oldStuff.getSeller()));

      if (newStuff.equals(oldStuff)) {
        System.out.println("Update cancel");
        return;
      }

      out.writeUTF("/stuff/update");
      out.writeObject(newStuff);
      out.flush();

      response = in.readUTF();
      if (response.equals("fail")) {
        System.out.println("Server(fail) : " + in.readUTF());
        return;
      }
      System.out.println("Update complete");

    } catch (Exception e) {
      System.out.println("[/stuff/update] : communication error");
    }
  }
}
