package kyh.tam.handler;

import java.text.SimpleDateFormat;
import kyh.tam.dao.BoardDao;
import kyh.tam.domain.Board;
import kyh.util.Prompt;

public class BoardDetailCommand implements Command {
  BoardDao boardDao;
  Prompt prompt;

  public BoardDetailCommand(BoardDao boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() throws Exception {
    System.out.println("--------------------------------------------------");
    try {
      int number = prompt.inputInt("번호 : ");

      Board board = boardDao.findByNumber(number);
      System.out.printf("번호: %d\n", board.getNumber());
      System.out.printf("제목: %s\n", board.getTitle());
      System.out.printf("등록일: %s\n",
          new SimpleDateFormat("yyyy-MM-dd").format(board.getWriteDate()));
      System.out.printf("조회수: %d\n", board.getViewCount());
      System.out.printf("작성자 : %s\n", board.getWriter());

    } catch (Exception e) {
      System.out.println("[BoardDetailCommand.java] : Read failed");
    }
  }
}
