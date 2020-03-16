package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.DataLoaderListener;
import kyh.tam.dao.PhotoBoardDao;
import kyh.tam.dao.PhotoFileDao;
import kyh.tam.util.Prompt;

public class PhotoBoardDeleteServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardDeleteServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    int number = Prompt.getInt(in, out, "사진 번호 : ");

    DataLoaderListener.con.setAutoCommit(false);
    try {
      photoFileDao.deleteAll(number);
      if (photoBoardDao.delete(number) == 0) {
        out.write("Delete failed" + System.lineSeparator());
        throw new Exception("[PhotoBoardDeleteServlet.java] : photoBoardDao.delete() failed");
      }
      DataLoaderListener.con.commit();
      out.write("Delete complete" + System.lineSeparator());

    } catch (Exception e) {
      DataLoaderListener.con.rollback();
      out.write("Delete failed" + System.lineSeparator());
      System.out.println("[PhotoBoardDeleteServlet.java]" + e.getMessage());
      e.printStackTrace();

    } finally {
      out.flush();
      DataLoaderListener.con.setAutoCommit(true);
    }
  }
}
