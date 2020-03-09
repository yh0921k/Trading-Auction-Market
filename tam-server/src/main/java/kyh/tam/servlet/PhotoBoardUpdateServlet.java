package kyh.tam.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;
import kyh.tam.dao.PhotoBoardDao;
import kyh.tam.dao.PhotoFileDao;
import kyh.tam.domain.PhotoBoard;
import kyh.tam.domain.PhotoFile;

public class PhotoBoardUpdateServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardUpdateServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
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
      out.write("사진 파일 : " + System.lineSeparator());

      List<PhotoFile> oldPhotoFiles = photoFileDao.findAll(number);
      for (PhotoFile photoFile : oldPhotoFiles) {
        out.write(String.format(">> %s%s", photoFile.getFilepath(), System.lineSeparator()));
      }

      out.write(System.lineSeparator());
      out.write("사진은 일부만 변경할 수 없으며, 전체를 새로 등록해야 합니다." + System.lineSeparator());
      out.write("사진을 변경하시겠습니까?(Y/n)" + System.lineSeparator());
      out.write("!{}!" + System.lineSeparator());
      out.flush();

      String response = in.readLine();
      if (response.equalsIgnoreCase("y")) {
        photoFileDao.deleteAll(number);

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
              .add(new PhotoFile().setFilepath(filepath).setBoardNumber(newPhotoBoard.getNumber()));
        }
        for (PhotoFile photoFile : photoFiles) {
          photoFileDao.insert(photoFile);
        }
      }

      out.write("Update complete" + System.lineSeparator());
    } else {
      out.write("Update failed" + System.lineSeparator());
    }
    out.flush();
  }
}
