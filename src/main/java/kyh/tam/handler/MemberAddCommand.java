package kyh.tam.handler;

import java.util.Date;
import java.util.List;
import kyh.tam.domain.Member;
import kyh.util.Prompt;

public class MemberAddCommand implements Command {
  private List<Member> memberList;
  private Prompt prompt;

  public MemberAddCommand(Prompt prompt, List<Member> list) {
    this.prompt = prompt;
    this.memberList = list;
  }

  @Override
  public void execute() throws Exception {
    System.out
        .printf("-----------------------------------------------------------------------------\n");
    Member member = new Member();

    member.setNumber(prompt.inputInt("번호 : "));
    member.setName(prompt.inputString("이름 : "));
    member.setEmail(prompt.inputString("메일 : "));
    member.setAddress(prompt.inputString("주소 : "));
    member.setPassword(prompt.inputString("암호 : "));
    member.setPhoto(prompt.inputString("사진 : "));
    member.setTel(prompt.inputString("연락처 : "));
    member.setRegisteredDate(new Date());

    this.memberList.add(member);
    System.out.println("저장하였습니다.");
  }
}

