package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.PhotoBoardDao;
import kyh.tam.dao.PhotoFileDao;
import kyh.tam.sql.PlatformTransactionManager;
import kyh.tam.sql.TransactionCallback;
import kyh.tam.sql.TransactionTemplate;
import kyh.tam.util.Prompt;

public class PhotoBoardDeleteServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  TransactionTemplate transactionTemplate;

  public PhotoBoardDeleteServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao,
      PlatformTransactionManager txManager) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.transactionTemplate = new TransactionTemplate(txManager);
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    int number = Prompt.getInt(in, out, "사진 번호 : ");

    transactionTemplate.execute(new TransactionCallback() {
      @Override
      public Object doInTransaction() throws Exception {
        photoFileDao.deleteAll(number);
        if (photoBoardDao.delete(number) == 0) {
          out.write("Delete failed" + System.lineSeparator());
          throw new Exception("[PhotoBoardDeleteServlet.java] : photoBoardDao.delete() failed");
        }
        out.write("Delete complete" + System.lineSeparator());
        out.flush();
        return null;
      }
    });
  }
}
