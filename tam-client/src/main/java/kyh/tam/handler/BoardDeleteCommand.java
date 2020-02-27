package kyh.tam.handler;

import kyh.tam.dao.BoardDao;
import kyh.util.Prompt;

public class BoardDeleteCommand implements Command {
  BoardDao boardDao;
  Prompt prompt;

  public BoardDeleteCommand(BoardDao boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() throws Exception {
    System.out.println("--------------------------------------------------");
    try {
      int number = prompt.inputInt("번호 : ");
      if (boardDao.delete(number) > 0) {
        System.out.println("Delete complete");
      } else {
        System.out.println("The number does not exist");
      }

    } catch (Exception e) {
      System.out.println("[BoardDeleteCommand.java] : Delete failed");
      e.printStackTrace();
    }
  }
}
