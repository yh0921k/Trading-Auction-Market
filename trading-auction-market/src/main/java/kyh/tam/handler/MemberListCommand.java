package kyh.tam.handler;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import kyh.tam.domain.Member;

public class MemberListCommand implements Command {
  private List<Member> memberList;

  public MemberListCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute() {
    System.out
        .printf("-----------------------------------------------------------------------------\n");
    Iterator<Member> it = memberList.iterator();
    while (it.hasNext()) {
      Member member = it.next();
      System.out.printf("%s, %s, %s, %s, %s\n", member.getNumber(), member.getName(),
          member.getAddress(), member.getTel(),
          new SimpleDateFormat("yyyy-MM-dd").format(member.getRegisteredDate()));
    }
  }
}

