package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.BoardDao;
import kyh.tam.util.Prompt;

public class BoardDeleteServlet implements Servlet {
  BoardDao boardDao;

  public BoardDeleteServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    int number = Prompt.getInt(in, out, "번호 : ");
    if (boardDao.delete(number) > 0) {
      out.write("Delete complete" + System.lineSeparator());
    } else {
      out.write("Delete failed" + System.lineSeparator());
    }
    out.flush();
  }
}
