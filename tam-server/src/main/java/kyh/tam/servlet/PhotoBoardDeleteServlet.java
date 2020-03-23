package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.PhotoBoardDao;
import kyh.tam.dao.PhotoFileDao;
import kyh.tam.sql.PlatformTransactionManager;
import kyh.tam.util.Prompt;

public class PhotoBoardDeleteServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  PlatformTransactionManager txManager;

  public PhotoBoardDeleteServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao,
      PlatformTransactionManager txManager) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.txManager = txManager;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    int number = Prompt.getInt(in, out, "사진 번호 : ");

    txManager.beginTransaction();
    try {
      photoFileDao.deleteAll(number);
      if (photoBoardDao.delete(number) == 0) {
        out.write("Delete failed" + System.lineSeparator());
        throw new Exception("[PhotoBoardDeleteServlet.java] : photoBoardDao.delete() failed");
      }
      txManager.commit();
      out.write("Delete complete" + System.lineSeparator());

    } catch (Exception e) {
      txManager.rollback();
      out.write("Delete failed" + System.lineSeparator());
      System.out.println("[PhotoBoardDeleteServlet.java]" + e.getMessage());
      e.printStackTrace();

    } finally {
      out.flush();
    }
  }
}
