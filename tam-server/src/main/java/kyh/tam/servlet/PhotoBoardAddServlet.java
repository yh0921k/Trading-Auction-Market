package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import kyh.tam.dao.PhotoBoardDao;
import kyh.tam.dao.PhotoFileDao;
import kyh.tam.dao.StuffDao;
import kyh.tam.domain.PhotoBoard;
import kyh.tam.domain.PhotoFile;
import kyh.tam.domain.Stuff;

public class PhotoBoardAddServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  StuffDao stuffDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardAddServlet(PhotoBoardDao photoBoardDao, StuffDao stuffDao,
      PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.stuffDao = stuffDao;
    this.photoFileDao = photoFileDao;
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

    int stuffNumber = Integer.parseInt(in.readLine());
    Stuff stuff = stuffDao.findByNumber(stuffNumber);
    if (stuff == null) {
      out.write("Save failed : invalid number" + System.lineSeparator());
      out.flush();
      return;
    }
    photoBoard.setStuff(stuff);

    try {
      if (photoBoardDao.insert(photoBoard) > 0) {
        out.write("* 최소 한 개의 사진 파일을 등록해야함  *" + System.lineSeparator());
        out.write("* 파일명 없이 Enter를 누르면 입력 마침 *" + System.lineSeparator());

        ArrayList<PhotoFile> photoFiles = new ArrayList<>();
        while (true) {
          out.write("사진 파일 : " + System.lineSeparator());
          out.write("!{}!" + System.lineSeparator());
          out.flush();

          String filepath = in.readLine();
          if (filepath.length() == 0) {
            if (photoFiles.size() > 0) {
              out.write("입력 완료" + System.lineSeparator());
              out.flush();
              break;
            }
            out.write("최소 한 개의 사진 파일을 등록해야합니다." + System.lineSeparator());
            out.flush();
            continue;
          }
          photoFiles
              .add(new PhotoFile().setFilepath(filepath).setBoardNumber(photoBoard.getNumber()));
        }
        for (PhotoFile photoFile : photoFiles) {
          photoFileDao.insert(photoFile);
        }
        out.write("Save complete" + System.lineSeparator());
      }
    } catch (Exception e) {
      out.write("Save failed" + System.lineSeparator());
      System.out.println("[PhotoBoardAddServlet.java]" + e.getMessage());
      e.printStackTrace();
    }
    out.flush();
  }
}
