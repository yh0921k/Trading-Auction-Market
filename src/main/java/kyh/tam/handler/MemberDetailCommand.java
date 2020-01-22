package kyh.tam.handler;

import java.text.SimpleDateFormat;
import java.util.List;
import kyh.tam.domain.Member;
import kyh.util.Prompt;

public class MemberDetailCommand implements Command {
  private List<Member> memberList;
  private Prompt prompt;

  public MemberDetailCommand(Prompt prompt, List<Member> list) {
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

    Member member = this.memberList.get(index);
    System.out.printf("번호 : %d\n", member.getNumber());
    System.out.printf("이름 : %s\n", member.getName());
    System.out.printf("메일 : %s\n", member.getEmail());
    System.out.printf("주소 : %s\n", member.getAddress());
    System.out.printf("암호 : %s\n", member.getPassword());
    System.out.printf("사진 : %s\n", member.getPhoto());
    System.out.printf("연락처 : %s\n", member.getTel());
    System.out.printf("가입일 : %s\n",
        new SimpleDateFormat("yyyy-MM-dd").format(member.getRegisteredDate()));
  }

  private int indexOfMember(int number) {
    for (int i = 0; i < this.memberList.size(); i++)
      if (this.memberList.get(i).getNumber() == number)
        return i;
    return -1;
  }
}

