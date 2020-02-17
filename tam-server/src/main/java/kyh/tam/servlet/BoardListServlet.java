package kyh.tam.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kyh.tam.dao.BoardDao;

public class BoardListServlet implements Servlet {
  BoardDao boardDao;

  public BoardListServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("ok");
    out.reset();
    out.writeObject(boardDao.findAll());
  }
}
