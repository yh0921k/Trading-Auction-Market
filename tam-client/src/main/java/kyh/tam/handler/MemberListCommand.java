package kyh.tam.handler;

import java.util.List;
import kyh.tam.dao.MemberDao;
import kyh.tam.domain.Member;

public class MemberListCommand implements Command {
  MemberDao memberDao;

  public MemberListCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute() {
    System.out.println("--------------------------------------------------");
    try {
      List<Member> members = memberDao.findAll();
      for (Member m : members)
        System.out.println(m);

    } catch (Exception e) {
      System.out.println("[MemberListCommand.java] : Read failed");
    }
  }
}

