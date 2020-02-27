package kyh.tam.handler;

import kyh.tam.dao.BoardDao;
import kyh.tam.domain.Board;
import kyh.util.Prompt;

public class BoardUpdateCommand implements Command {
  BoardDao boardDao;
  Prompt prompt;

  public BoardUpdateCommand(BoardDao boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() throws Exception {
    System.out.println("--------------------------------------------------");
    try {
      int number = prompt.inputInt("번호 : ");

      Board oldBoard;
      try {
        oldBoard = boardDao.findByNumber(number);

      } catch (Exception e) {
        System.out.println("해당 번호의 게시물이 없습니다.");
        return;
      }

      Board newBoard = new Board();
      newBoard.setNumber(oldBoard.getNumber());
      newBoard.setTitle(
          prompt.inputString(String.format("제목(%s) : ", oldBoard.getTitle()), oldBoard.getTitle()));

      if (newBoard.getTitle().equals(oldBoard.getTitle())) {
        System.out.println("Update cancel");
        return;
      }

      boardDao.update(newBoard);
      System.out.println("Update complete");
    } catch (Exception e) {
      System.out.println("[BoardUpdateCommand.java] : Update failed");
      e.printStackTrace();
    }
  }
}
