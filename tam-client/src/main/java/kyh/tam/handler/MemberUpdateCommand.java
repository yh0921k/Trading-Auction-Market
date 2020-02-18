package kyh.tam.handler;

import kyh.tam.dao.MemberDao;
import kyh.tam.domain.Member;
import kyh.util.Prompt;

public class MemberUpdateCommand implements Command {
  MemberDao memberDao;
  Prompt prompt;

  public MemberUpdateCommand(MemberDao memberDao, Prompt prompt) {
    this.memberDao = memberDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() throws Exception {
    System.out.println("--------------------------------------------------");
    try {
      int number = prompt.inputInt("번호 : ");

      Member oldMember;
      try {
        oldMember = memberDao.findByNumber(number);
      } catch (Exception e) {
        System.out.println("해당 번호의 회원이 없습니다.");
        return;
      }

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

      if (oldMember.toString().equals(newMember.toString())) {
        System.out.println("Update cancel");
        return;
      }

      memberDao.update(newMember);
      System.out.println("Update complete");

    } catch (Exception e) {
      System.out.println("[MemberUpdateCommand.java] : Update failed");
    }
  }
}

