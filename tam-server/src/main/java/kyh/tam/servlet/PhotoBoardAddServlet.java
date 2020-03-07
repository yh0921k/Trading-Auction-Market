package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.PhotoBoardDao;
import kyh.tam.domain.PhotoBoard;
import kyh.tam.domain.Stuff;

public class PhotoBoardAddServlet implements Servlet {

  PhotoBoardDao photoBoardDao;

  public PhotoBoardAddServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    PhotoBoard photoBoard = new PhotoBoard();

    out.write("사진 제목 : " + System.lineSeparator());
    out.write("!{}!" + System.lineSeparator());
    out.flush();
    photoBoard.setTitle(in.readLine());

    out.write("물품 번호 : " + System.lineSeparator());
    out.write("!{}!" + System.lineSeparator());
    out.flush();
    Stuff stuff = new Stuff();
    stuff.setNumber(Integer.parseInt(in.readLine()));
    photoBoard.setStuff(stuff);

    try {
      if (photoBoardDao.insert(photoBoard) > 0)
        out.write("Save complete" + System.lineSeparator());
    } catch (Exception e) {
      out.write("Save failed : invalid number" + System.lineSeparator());
      System.out.println("[PhotoBoardAddServlet.java] : invalid number");
      e.printStackTrace();
    }
    out.flush();
  }
}
