package kyh.tam.handler;

import java.sql.Date;
import kyh.tam.dao.MemberDao;
import kyh.tam.domain.Member;
import kyh.util.Prompt;

public class MemberAddCommand implements Command {
  MemberDao memberDao;
  Prompt prompt;

  public MemberAddCommand(MemberDao memberDao, Prompt prompt) {
    this.memberDao = memberDao;
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
      memberDao.insert(member);
      System.out.println("Save complete");

    } catch (Exception e) {
      System.out.println("[MemberAddCommand.java] : Save failed");
    }
  }
}

