package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.List;
import kyh.tam.dao.BoardDao;
import kyh.tam.domain.Board;

public class BoardListServlet implements Servlet {
  BoardDao boardDao;

  public BoardListServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    List<Board> boards = boardDao.findAll();
    for (Board board : boards) {
      out.write(String.format("%d, %s, %s, %d", board.getNumber(), board.getTitle(),
          board.getWriteDate(), board.getViewCount()) + System.lineSeparator());
    }
    out.flush();
  }
}
