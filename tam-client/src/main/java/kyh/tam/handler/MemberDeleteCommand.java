package kyh.tam.handler;

import kyh.tam.dao.MemberDao;
import kyh.util.Prompt;

public class MemberDeleteCommand implements Command {
  MemberDao memberDao;
  Prompt prompt;

  public MemberDeleteCommand(MemberDao memberDao, Prompt prompt) {
    this.memberDao = memberDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() throws Exception {
    System.out.println("--------------------------------------------------");
    try {
      int number = prompt.inputInt("번호 : ");
      memberDao.delete(number);
      System.out.println("Delete complete");

    } catch (Exception e) {
      System.out.println("[MemberDeleteCommand.java] : Delete failed");
    }
  }
}

