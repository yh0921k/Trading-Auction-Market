package kyh.tam.handler;

import java.util.List;
import kyh.tam.domain.Member;
import kyh.util.Prompt;

public class MemberUpdateCommand implements Command {
  private List<Member> memberList;
  private Prompt prompt;

  public MemberUpdateCommand(Prompt prompt, List<Member> list) {
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

    Member oldMember = memberList.get(index);
    Member newMember = new Member();

    newMember.setNumber(oldMember.getNumber());
    newMember.setName(
        prompt.inputString(String.format("이름(%s) : ", oldMember.getName()), oldMember.getName()));

    newMember.setEmail(
        prompt.inputString(String.format("메일(%s) : ", oldMember.getEmail()), oldMember.getEmail()));

    newMember.setAddress(prompt.inputString(String.format("주소(%s) : ", oldMember.getAddress()),
        oldMember.getAddress()));

    newMember.setPassword(prompt.inputString(String.format("암호(%s) : ", oldMember.getPassword()),
        oldMember.getPassword()));

    newMember.setPhoto(
        prompt.inputString(String.format("사진(%s) : ", oldMember.getPhoto()), oldMember.getPhoto()));

    newMember.setTel(
        prompt.inputString(String.format("연락처(%s) : ", oldMember.getTel()), oldMember.getTel()));

    newMember.setRegisteredDate(oldMember.getRegisteredDate());

    if (oldMember.equals(newMember)) {
      System.out.println("회원 변경 취소");
      return;
    }
    this.memberList.set(index, newMember);
    System.out.println("회원 변경 완료");
  }

  private int indexOfMember(int number) {
    for (int i = 0; i < this.memberList.size(); i++)
      if (this.memberList.get(i).getNumber() == number)
        return i;
    return -1;
  }
}

