package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.PhotoBoardDao;

public class PhotoBoardDeleteServlet implements Servlet {

  PhotoBoardDao photoBoardDao;

  public PhotoBoardDeleteServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    out.write("사진 번호 : " + System.lineSeparator());
    out.write("!{}!" + System.lineSeparator());
    out.flush();

    int number = Integer.parseInt(in.readLine());
    if (photoBoardDao.delete(number) > 0) {
      out.write("Delete complete" + System.lineSeparator());
    } else {
      out.write("Delete failed" + System.lineSeparator());
    }
    out.flush();
  }
}
