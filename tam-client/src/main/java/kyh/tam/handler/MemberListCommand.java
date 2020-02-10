package kyh.tam.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kyh.tam.domain.Member;

public class MemberListCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;

  public MemberListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {
    System.out.println("--------------------------------------------------");
    try {
      out.writeUTF("/member/list");
      out.flush();
      String response = in.readUTF();
      if (response.equals("fail")) {
        System.out.println("Server(fail) : " + in.readUTF());
        return;
      }

      List<Member> members = (List<Member>) in.readObject();
      for (Member m : members)
        System.out.println(m);

    } catch (Exception e) {
      System.out.println("[/member/list] : communication error");
    }
  }
}

