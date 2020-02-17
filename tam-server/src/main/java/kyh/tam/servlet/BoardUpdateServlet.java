package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.dao.BoardDao;
import kyh.tam.domain.Board;

public class BoardUpdateServlet implements Servlet {
  BoardDao boardDao;

  public BoardUpdateServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    try {
      Board board = (Board) in.readObject();

      if (boardDao.update(board) > 0) {
        out.writeUTF("ok");
      } else {
        out.writeUTF("fail");
        out.writeUTF("해당 번호의 게시물이 없습니다.");
      }

    } catch (Exception e) {
      System.out.println("[/board/update] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }
}
