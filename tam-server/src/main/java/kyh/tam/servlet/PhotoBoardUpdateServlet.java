package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import kyh.tam.dao.PhotoBoardDao;
import kyh.tam.domain.PhotoBoard;

public class PhotoBoardUpdateServlet implements Servlet {

  PhotoBoardDao photoBoardDao;

  public PhotoBoardUpdateServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    out.write("사진 번호 : " + System.lineSeparator());
    out.write("!{}!" + System.lineSeparator());
    out.flush();

    int number = Integer.parseInt(in.readLine());
    PhotoBoard oldPhotoBaord = photoBoardDao.findByNo(number);
    if (oldPhotoBaord == null) {
      out.write("Update failed : invalid number" + System.lineSeparator());
      out.flush();
      return;
    }

    out.write(String.format("제목(%s) : %s", oldPhotoBaord.getTitle(), System.lineSeparator()));
    out.write("!{}!" + System.lineSeparator());
    out.flush();

    PhotoBoard newPhotoBoard = new PhotoBoard();
    newPhotoBoard.setNumber(number);
    newPhotoBoard.setTitle(in.readLine());

    if (photoBoardDao.update(newPhotoBoard) > 0) {
      out.write("Update complete" + System.lineSeparator());
    } else {
      out.write("Update failed" + System.lineSeparator());
    }
    out.flush();
  }
}
