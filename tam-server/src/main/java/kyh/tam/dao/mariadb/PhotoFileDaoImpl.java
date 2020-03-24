package kyh.tam.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kyh.tam.dao.PhotoFileDao;
import kyh.tam.domain.PhotoFile;
import kyh.tam.util.DataSource;

public class PhotoFileDaoImpl implements PhotoFileDao {

  DataSource dataSource;

  public PhotoFileDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(PhotoFile photoFile) throws Exception {
    try (Connection con = dataSource.getConnection(); Statement stmt = con.createStatement();) {
      String query =
          String.format("insert into tam_photo_file(photo_id, file_path) values(%d, '%s')",
              photoFile.getBoardNumber(), photoFile.getFilepath());
      return stmt.executeUpdate(query);
    }
  }

  @Override
  public List<PhotoFile> findAll(int boardNumber) throws Exception {
    try (Connection con = dataSource.getConnection(); Statement stmt = con.createStatement();) {

      String query = String.format(
          "select photo_file_id, photo_id, file_path from tam_photo_file where photo_id=%d order by photo_file_id asc",
          boardNumber);
      ResultSet rs = stmt.executeQuery(query);

      ArrayList<PhotoFile> list = new ArrayList<>();
      while (rs.next()) {
        list.add(new PhotoFile().setNumber(rs.getInt("photo_file_id"))
            .setFilepath(rs.getString("file_path")).setBoardNumber(rs.getInt("photo_id")));
      }
      return list;
    }
  }

  @Override
  public int deleteAll(int boardNumber) throws Exception {
    try (Connection con = dataSource.getConnection(); Statement stmt = con.createStatement();) {
      return stmt.executeUpdate("delete from tam_photo_file where photo_id=" + boardNumber);
    }
  }
}
