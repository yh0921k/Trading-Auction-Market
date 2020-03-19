package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import kyh.tam.dao.PhotoBoardDao;
import kyh.tam.dao.PhotoFileDao;
import kyh.tam.dao.StuffDao;
import kyh.tam.domain.PhotoBoard;
import kyh.tam.domain.PhotoFile;
import kyh.tam.domain.Stuff;
import kyh.tam.util.ConnectionFactory;
import kyh.tam.util.Prompt;

public class PhotoBoardAddServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  StuffDao stuffDao;
  PhotoFileDao photoFileDao;
  ConnectionFactory connectionFactory;

  public PhotoBoardAddServlet(PhotoBoardDao photoBoardDao, StuffDao stuffDao,
      PhotoFileDao photoFileDao, ConnectionFactory connectionFactory) {
    this.photoBoardDao = photoBoardDao;
    this.stuffDao = stuffDao;
    this.photoFileDao = photoFileDao;
    this.connectionFactory = connectionFactory;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(Prompt.getString(in, out, "사진 게시글 제목 : "));

    int stuffNumber = Prompt.getInt(in, out, "물품 번호 : ");
    Stuff stuff = stuffDao.findByNumber(stuffNumber);
    if (stuff == null) {
      out.write("Save failed : invalid number" + System.lineSeparator());
      out.flush();
      return;
    }

    photoBoard.setStuff(stuff);
    Connection con = connectionFactory.getConnection();
    con.setAutoCommit(false);
    try {
      if (photoBoardDao.insert(photoBoard) == 0) {
        throw new Exception("[PhotoBoardAddServlet.java] : photoBoardDao.insert() failed");
      }
      List<PhotoFile> photoFiles = inputPhotoFiles(in, out);
      for (PhotoFile photoFile : photoFiles) {
        photoFile.setBoardNumber(photoBoard.getNumber());
        photoFileDao.insert(photoFile);
      }
      con.commit();
      out.write("Save complete" + System.lineSeparator());

    } catch (Exception e) {
      con.rollback();
      out.write("Save failed" + System.lineSeparator());
      System.out.println("[PhotoBoardAddServlet.java]" + e.getMessage());
      e.printStackTrace();

    } finally {
      con.setAutoCommit(true);
      out.flush();
    }
  }

  private List<PhotoFile> inputPhotoFiles(BufferedReader in, BufferedWriter out)
      throws IOException {
    out.write("------------------------------------------" + System.lineSeparator());
    out.write("|* 최소 한 개의 사진 파일을 등록해야함  *|" + System.lineSeparator());
    out.write("|* 파일명 없이 Enter를 누르면 입력 마침 *|" + System.lineSeparator());
    out.write("------------------------------------------" + System.lineSeparator());

    ArrayList<PhotoFile> photoFiles = new ArrayList<>();
    while (true) {
      String filepath = Prompt.getString(in, out, "사진 파일 : ");
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
      photoFiles.add(new PhotoFile().setFilepath(filepath));
    }
    return photoFiles;
  }
}
