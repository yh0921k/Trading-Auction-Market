package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.List;
import kyh.tam.dao.PhotoBoardDao;
import kyh.tam.dao.StuffDao;
import kyh.tam.domain.PhotoBoard;
import kyh.tam.domain.Stuff;
import kyh.tam.util.Prompt;

public class PhotoBoardListServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  StuffDao stuffDao;

  public PhotoBoardListServlet(PhotoBoardDao photoBoardDao, StuffDao stuffDao) {
    this.photoBoardDao = photoBoardDao;
    this.stuffDao = stuffDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    int stuffNumber = Prompt.getInt(in, out, "물품 번호 : ");
    Stuff stuff = stuffDao.findByNumber(stuffNumber);
    if (stuff == null) {
      out.write("Search failed : invalid number" + System.lineSeparator());
      out.flush();
      return;
    }

    out.write("물품명 : " + stuff.getName() + System.lineSeparator());
    List<PhotoBoard> photoBoards = photoBoardDao.findAllByStuffNumber(stuffNumber);
    for (PhotoBoard photoBoard : photoBoards) {
      out.write(String.format("%d, %s, %s, %d", photoBoard.getNumber(), photoBoard.getTitle(),
          photoBoard.getRegisteredDate(), photoBoard.getViewCount()) + System.lineSeparator());
    }
    out.flush();
  }
}
