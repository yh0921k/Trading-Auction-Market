package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.BoardDao;
import kyh.tam.domain.Board;

public class BoardDetailServlet implements Servlet {
  BoardDao boardDao;

  public BoardDetailServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    out.write("번호 : " + System.lineSeparator());
    out.write("!{}!" + System.lineSeparator());
    out.flush();

    int number = Integer.parseInt(in.readLine());

    Board board = boardDao.findByNumber(number);
    if (board != null) {
      out.write("번호 : " + board.getNumber() + System.lineSeparator());
      out.write("제목 : " + board.getTitle() + System.lineSeparator());
      out.write("등록일 : " + board.getWriteDate() + System.lineSeparator());
      out.write("조회수 : " + board.getViewCount() + System.lineSeparator());
      out.write("작성자 : " + board.getWriter() + System.lineSeparator());
    } else {
      out.write("Read failed : invalid number" + System.lineSeparator());
    }
    out.flush();
  }
}
