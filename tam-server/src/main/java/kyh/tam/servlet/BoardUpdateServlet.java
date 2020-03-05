package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.BoardDao;
import kyh.tam.domain.Board;

public class BoardUpdateServlet implements Servlet {
  BoardDao boardDao;

  public BoardUpdateServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    out.write("번호 : " + System.lineSeparator());
    out.write("!{}!" + System.lineSeparator());
    out.flush();

    int number = Integer.parseInt(in.readLine());

    Board oldBoard = boardDao.findByNumber(number);
    if (oldBoard == null) {
      out.write("Update failed : invalid number" + System.lineSeparator());
      out.flush();
      return;
    }

    out.write(String.format("제목(%s) : \n!{}!\n", oldBoard.getTitle()));
    out.flush();

    Board newBoard = new Board();
    newBoard.setNumber(number);
    newBoard.setTitle(in.readLine());

    if (boardDao.update(newBoard) > 0) {
      out.write("Update complete" + System.lineSeparator());
    } else {
      out.write("Update failed" + System.lineSeparator());
    }
    out.flush();
  }
}
