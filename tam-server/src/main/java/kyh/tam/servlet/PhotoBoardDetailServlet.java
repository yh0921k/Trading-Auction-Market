package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.List;
import kyh.tam.dao.PhotoBoardDao;
import kyh.tam.domain.PhotoBoard;
import kyh.tam.domain.PhotoFile;
import kyh.tam.util.Prompt;

public class PhotoBoardDetailServlet implements Servlet {

  PhotoBoardDao photoBoardDao;

  public PhotoBoardDetailServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    int number = Prompt.getInt(in, out, "사진 번호 : ");
    PhotoBoard photoBoard = photoBoardDao.findByNo(number);
    if (photoBoard != null) {
      out.write(String.format("번호 : %d%s", photoBoard.getNumber(), System.lineSeparator()));
      out.write(String.format("제목 : %s%s", photoBoard.getTitle(), System.lineSeparator()));
      out.write(
          String.format("등록일 : %s%s", photoBoard.getRegisteredDate(), System.lineSeparator()));
      out.write(String.format("조회수 : %s%s", photoBoard.getViewCount(), System.lineSeparator()));
      out.write(
          String.format("물품명 : %s%s", photoBoard.getStuff().getName(), System.lineSeparator()));
      out.write("사진 파일 : " + System.lineSeparator());

      List<PhotoFile> photoFiles = photoBoard.getFiles();
      for (PhotoFile photoFile : photoFiles) {
        out.write(String.format(">> %s%s", photoFile.getFilepath(), System.lineSeparator()));
      }

    } else {
      out.write("Read failed : invalid number" + System.lineSeparator());
    }
    out.flush();
  }
}
