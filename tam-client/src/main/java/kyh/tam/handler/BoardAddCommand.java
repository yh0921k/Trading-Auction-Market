package kyh.tam.handler;

import kyh.tam.dao.BoardDao;
import kyh.tam.domain.Board;
import kyh.util.Prompt;

public class BoardAddCommand implements Command {

  BoardDao boardDao;
  Prompt prompt;

  public BoardAddCommand(BoardDao boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() throws Exception {
    System.out.println("--------------------------------------------------");

    Board board = new Board();
    board.setTitle(prompt.inputString("내용 : "));

    try {
      boardDao.insert(board);
      System.out.println("Save complete");

    } catch (Exception e) {
      System.out.println("[BoardAddCommand.java] : Save failed");
      e.printStackTrace();
    }
  }
}
