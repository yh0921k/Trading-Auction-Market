package kyh.tam.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import kyh.tam.domain.Member;
import kyh.util.Prompt;

public class MemberAddCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public MemberAddCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() throws Exception {
    System.out.println("--------------------------------------------------");
    Member member = new Member();
    member.setNumber(prompt.inputInt("번호 : "));
    member.setName(prompt.inputString("이름 : "));
    member.setEmail(prompt.inputString("메일 : "));
    member.setAddress(prompt.inputString("주소 : "));
    member.setPassword(prompt.inputString("암호 : "));
    member.setPhoto(prompt.inputString("사진 : "));
    member.setTel(prompt.inputString("연락처 : "));
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    try {
      out.writeUTF("/member/add");
      out.writeObject(member);
      out.flush();
      String response = in.readUTF();
      if (response.equals("fail")) {
        System.out.println("Server(fail) : " + in.readUTF());
        return;
      }
      System.out.println("Save complete");

    } catch (Exception e) {
      System.out.println("[/member/add] : communication error");
    }
  }
}

