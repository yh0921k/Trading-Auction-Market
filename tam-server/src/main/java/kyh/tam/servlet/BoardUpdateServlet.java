package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.BoardDao;
import kyh.tam.domain.Board;
import kyh.tam.util.Prompt;

public class BoardUpdateServlet implements Servlet {
  BoardDao boardDao;

  public BoardUpdateServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    int number = Prompt.getInt(in, out, "번호 : ");
    Board oldBoard = boardDao.findByNumber(number);
    if (oldBoard == null) {
      out.write("Update failed : invalid number" + System.lineSeparator());
      out.flush();
      return;
    }

    Board newBoard = new Board();
    newBoard.setNumber(number);
    newBoard.setTitle(Prompt.getString(in, out, String.format("제목(%s) : ", oldBoard.getTitle()),
        oldBoard.getTitle()));

    if (boardDao.update(newBoard) > 0) {
      out.write("Update complete" + System.lineSeparator());
    } else {
      out.write("Update failed" + System.lineSeparator());
    }
    out.flush();
  }
}
