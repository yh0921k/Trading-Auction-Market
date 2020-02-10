package kyh.tam.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import kyh.tam.domain.Member;
import kyh.util.Prompt;

public class MemberDetailCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public MemberDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
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

      Member member = (Member) in.readObject();
      System.out.printf("번호 : %d\n", member.getNumber());
      System.out.printf("이름 : %s\n", member.getName());
      System.out.printf("메일 : %s\n", member.getEmail());
      System.out.printf("주소 : %s\n", member.getAddress());
      System.out.printf("암호 : %s\n", member.getPassword());
      System.out.printf("사진 : %s\n", member.getPhoto());
      System.out.printf("연락처 : %s\n", member.getTel());
      System.out.printf("가입일 : %s\n",
          new SimpleDateFormat("yyyy-MM-dd").format(member.getRegisteredDate()));

    } catch (Exception e) {
      System.out.println("[/member/detail] : communication error");
    }
  }
}

