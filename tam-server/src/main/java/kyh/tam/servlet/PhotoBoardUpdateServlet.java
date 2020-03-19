package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import kyh.tam.dao.PhotoBoardDao;
import kyh.tam.dao.PhotoFileDao;
import kyh.tam.domain.PhotoBoard;
import kyh.tam.domain.PhotoFile;
import kyh.tam.util.ConnectionFactory;
import kyh.tam.util.Prompt;

public class PhotoBoardUpdateServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  ConnectionFactory connectionFactory;

  public PhotoBoardUpdateServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao,
      ConnectionFactory connectionFactory) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.connectionFactory = connectionFactory;
  }

  @Override
  public void service(BufferedReader in, BufferedWriter out) throws Exception {
    int number = Prompt.getInt(in, out, "사진 게시글 번호 : ");
    PhotoBoard oldPhotoBoard = photoBoardDao.findByNo(number);
    if (oldPhotoBoard == null) {
      out.write("Update failed : invalid number" + System.lineSeparator());
      out.flush();
      return;
    }

    PhotoBoard newPhotoBoard = new PhotoBoard();
    newPhotoBoard.setNumber(number);
    newPhotoBoard.setTitle(Prompt.getString(in, out,
        String.format("사진 게시글 제목(%s) : ", oldPhotoBoard.getTitle()), oldPhotoBoard.getTitle()));

    Connection con = connectionFactory.getConnection();
    con.setAutoCommit(false);
    try {
      if (photoBoardDao.update(newPhotoBoard) == 0) {
        throw new Exception("[PhotoBoardUpdateServlet.java] : photoBoardDao.update() failed");
      }
      printPhotoFiles(out, number);

      out.write("사진은 일부만 변경할 수 없으며, 전체를 새로 등록해야 합니다." + System.lineSeparator());
      String response = Prompt.getString(in, out, "사진을 변경하시겠습니까?(Y/n)");
      if (response.equalsIgnoreCase("y")) {
        photoFileDao.deleteAll(number);

        List<PhotoFile> photoFiles = inputPhotoFiles(in, out);
        for (PhotoFile photoFile : photoFiles) {
          photoFile.setBoardNumber(number);
          photoFileDao.insert(photoFile);
        }
      }
      con.commit();
      out.write("Update complete" + System.lineSeparator());

    } catch (Exception e) {
      con.rollback();
      out.write("Update failed" + System.lineSeparator());
      System.out.println("[PhotoBoardUpdateServlet.java]" + e.getMessage());
      e.printStackTrace();

    } finally {
      con.setAutoCommit(true);
      out.flush();
    }
  }

  private void printPhotoFiles(BufferedWriter out, int boardNumber) throws Exception {
    out.write("사진 파일 : " + System.lineSeparator());

    List<PhotoFile> oldPhotoFiles = photoFileDao.findAll(boardNumber);
    for (PhotoFile photoFile : oldPhotoFiles) {
      out.write(String.format(">> %s%s", photoFile.getFilepath(), System.lineSeparator()));
    }
    out.write(System.lineSeparator());
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
