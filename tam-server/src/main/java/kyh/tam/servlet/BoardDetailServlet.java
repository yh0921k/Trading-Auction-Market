package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.dao.json.BoardJsonFileDao;
import kyh.tam.domain.Board;

public class BoardDetailServlet implements Servlet {
  BoardJsonFileDao boardDao;

  public BoardDetailServlet(BoardJsonFileDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      int number = in.readInt();

      Board board = boardDao.findByNumber(number);
      if (board != null) {
        out.writeUTF("ok");
        out.writeObject(board);
      } else {
        out.writeUTF("fail");
        out.writeUTF("해당 번호의 게시물이 없습니다.");
      }

    } catch (Exception e) {
      System.out.println("[/board/detail] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }
}