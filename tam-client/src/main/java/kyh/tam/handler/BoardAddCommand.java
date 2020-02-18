package kyh.tam.handler;

import java.sql.Date;
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
    board.setNumber(prompt.inputInt("번호 : "));
    board.setTitle(prompt.inputString("내용 : "));
    board.setWriteDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);

    try {
      boardDao.insert(board);
      System.out.println("Save complete");

    } catch (Exception e) {
      System.out.println("[BoardAddCommand.java] : Save failed");
    }
  }
}
