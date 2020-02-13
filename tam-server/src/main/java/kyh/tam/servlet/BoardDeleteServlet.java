package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.dao.BoardObjectFileDao;

public class BoardDeleteServlet implements Servlet {
  BoardObjectFileDao boardDao;

  public BoardDeleteServlet(BoardObjectFileDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      int number = in.readInt();

      if (boardDao.delete(number) > 0) {
        out.writeUTF("ok");
      } else {
        out.writeUTF("fail");
        out.writeUTF("해당 번호의 게시물이 없습니다.");
      }
    } catch (Exception e) {
      System.out.println("[/board/delete] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }
}
