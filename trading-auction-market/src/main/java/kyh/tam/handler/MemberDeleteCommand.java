package kyh.tam.handler;

import java.util.List;
import kyh.tam.domain.Member;
import kyh.util.Prompt;

public class MemberDeleteCommand implements Command {
  private List<Member> memberList;
  private Prompt prompt;

  public MemberDeleteCommand(Prompt prompt, List<Member> list) {
    this.prompt = prompt;
    this.memberList = list;
  }

  @Override
  public void execute() throws Exception {
    System.out
        .printf("-----------------------------------------------------------------------------\n");
    int index = indexOfMember(prompt.inputInt("번호 : "));
    if (index == -1) {
      System.out.println("회원이 존재하지 않습니다");
      return;
    }
    this.memberList.remove(index);
    System.out.println("회원 삭제 완료");
  }

  private int indexOfMember(int number) {
    for (int i = 0; i < this.memberList.size(); i++)
      if (this.memberList.get(i).getNumber() == number)
        return i;
    return -1;
  }
}

