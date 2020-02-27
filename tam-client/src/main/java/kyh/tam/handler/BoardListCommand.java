package kyh.tam.handler;

import java.util.List;
import kyh.tam.dao.BoardDao;
import kyh.tam.domain.Board;

public class BoardListCommand implements Command {

  BoardDao boardDao;

  public BoardListCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute() {
    System.out.println("--------------------------------------------------");
    try {
      List<Board> boards = boardDao.findAll();

      for (Board b : boards)
        System.out.println(b);

    } catch (Exception e) {
      System.out.println("[BoardListCommand.java] : Read failed");
      e.printStackTrace();
    }
  }
}
