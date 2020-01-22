package kyh.tam.handler;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import kyh.tam.domain.Board;

public class BoardListCommand implements Command {
  private List<Board> boardList;

  public BoardListCommand(List<Board> list) {
    this.boardList = list;
  }

  @Override
  public void execute() {
    System.out
        .printf("-----------------------------------------------------------------------------\n");

    Iterator<Board> it = boardList.iterator();
    while (it.hasNext()) {
      Board board = it.next();
      String wDate = new SimpleDateFormat("yyyy-MM-dd").format(board.getWriteDate());
      System.out.printf("%s, %s, %s, %s\n", board.getNumber(), board.getTitle(), wDate,
          board.getViewCount());
    }
  }
}
