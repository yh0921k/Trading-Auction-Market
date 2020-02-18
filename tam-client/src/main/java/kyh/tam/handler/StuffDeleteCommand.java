package kyh.tam.handler;

import kyh.tam.dao.StuffDao;
import kyh.util.Prompt;

public class StuffDeleteCommand implements Command {
  StuffDao stuffDao;
  Prompt prompt;

  public StuffDeleteCommand(StuffDao stuffDao, Prompt prompt) {
    this.stuffDao = stuffDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() throws Exception {
    System.out.println("--------------------------------------------------");
    try {
      int number = prompt.inputInt("번호 : ");
      stuffDao.delete(number);
      System.out.println("Delete complete");

    } catch (Exception e) {
      System.out.println("[StuffDeleteCommand.java] : Delete failed");
    }
  }
}
