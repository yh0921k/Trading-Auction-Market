package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.BoardDao;
import kyh.tam.domain.Board;

public class BoardAddServlet implements Servlet {
  BoardDao boardDao;

  public BoardAddServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    Board board = new Board();

    out.write("제목 : " + System.lineSeparator());
    out.write("!{}!" + System.lineSeparator());
    out.flush();

    board.setTitle(in.readLine());
    if (boardDao.insert(board) > 0)
      out.write("Save complete" + System.lineSeparator());
    else
      out.write("Save failed" + System.lineSeparator());
    out.flush();
  }
}
