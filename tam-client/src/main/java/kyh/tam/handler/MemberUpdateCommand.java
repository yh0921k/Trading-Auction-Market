package kyh.tam.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.domain.Member;
import kyh.util.Prompt;

public class MemberUpdateCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public MemberUpdateCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() throws Exception {
    System.out.println("--------------------------------------------------");
    try {
      int number = prompt.inputInt("번호 : ");
      out.writeUTF("/member/detail");
      out.writeInt(number);
      out.flush();

      String response = in.readUTF();
      if (response.equals("fail")) {
        System.out.println("Server(fail) : " + in.readUTF());
        return;
      }

      Member oldMember = (Member) in.readObject();
      Member newMember = new Member();

      newMember.setNumber(oldMember.getNumber());
      newMember.setName(
          prompt.inputString(String.format("이름(%s) : ", oldMember.getName()), oldMember.getName()));

      newMember.setEmail(prompt.inputString(String.format("메일(%s) : ", oldMember.getEmail()),
          oldMember.getEmail()));

      newMember.setAddress(prompt.inputString(String.format("주소(%s) : ", oldMember.getAddress()),
          oldMember.getAddress()));

      newMember.setPassword(prompt.inputString(String.format("암호(%s) : ", oldMember.getPassword()),
          oldMember.getPassword()));

      newMember.setPhoto(prompt.inputString(String.format("사진(%s) : ", oldMember.getPhoto()),
          oldMember.getPhoto()));

      newMember.setTel(
          prompt.inputString(String.format("연락처(%s) : ", oldMember.getTel()), oldMember.getTel()));

      newMember.setRegisteredDate(oldMember.getRegisteredDate());

      if (oldMember.equals(newMember)) {
        System.out.println("Update cancel");
        return;
      }

      out.writeUTF("/member/update");
      out.writeObject(newMember);
      out.flush();

      response = in.readUTF();
      if (response.equals("fail")) {
        System.out.println("Server(fail) : " + in.readUTF());
        return;
      }
      System.out.println("Update complete");

    } catch (Exception e) {
      System.out.println("[/member/update] : communication error");
    }
  }
}

