package kyh.tam.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kyh.tam.dao.PhotoBoardDao;
import kyh.tam.domain.PhotoBoard;
import kyh.tam.domain.Stuff;

public class PhotoBoardDaoImpl implements PhotoBoardDao {

  Connection con;

  public PhotoBoardDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {
    try (Statement stmt = con.createStatement();) {
      String query = String.format("insert into tam_photo(titl, stuff_id) values('%s', %d)",
          photoBoard.getTitle(), photoBoard.getStuff().getNumber());
      int result = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

      try (ResultSet generatedKeySet = stmt.getGeneratedKeys();) {
        generatedKeySet.next();
        photoBoard.setNumber(generatedKeySet.getInt(1));
      }

      return result;
    }
  }

  @Override
  public List<PhotoBoard> findAllByStuffNumber(int stuffNumber) throws Exception {
    try (Statement stmt = con.createStatement();) {
      String query = String.format("select photo_id, titl, cdt, stuff_id, vw_cnt "
          + "from tam_photo where stuff_id=%d order by photo_id asc", stuffNumber);
      ResultSet rs = stmt.executeQuery(query);

      ArrayList<PhotoBoard> list = new ArrayList<>();
      while (rs.next()) {
        PhotoBoard photoBoard = new PhotoBoard();
        photoBoard.setNumber(rs.getInt("photo_id"));
        photoBoard.setTitle(rs.getString("titl"));
        photoBoard.setRegisteredDate(rs.getDate("cdt"));
        photoBoard.setViewCount(rs.getInt("vw_cnt"));
        list.add(photoBoard);
      }
      rs.close();
      return list;
    }
  }

  @Override
  public PhotoBoard findByNo(int number) throws Exception {
    try (Statement stmt = con.createStatement();) {
      String query = String.format(
          "select p.photo_id, p.titl, p.cdt, p.vw_cnt, s.stuff_id, s.name stuff_name "
              + "from tam_photo p inner join tam_stuff s on p.stuff_id=s.stuff_id where photo_id=%d",
          number);
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        PhotoBoard photoBoard = new PhotoBoard();
        photoBoard.setNumber(rs.getInt("photo_id"));
        photoBoard.setTitle(rs.getString("titl"));
        photoBoard.setRegisteredDate(rs.getDate("cdt"));
        photoBoard.setViewCount(rs.getInt("vw_cnt"));

        Stuff stuff = new Stuff();
        stuff.setNumber(rs.getInt("stuff_id"));
        stuff.setName(rs.getString("stuff_name"));

        photoBoard.setStuff(stuff);
        return photoBoard;
      }
      rs.close();
      return null;
    }
  }

  @Override
  public int update(PhotoBoard photoBoard) throws Exception {
    try (Statement stmt = con.createStatement();) {
      String query = String.format("update tam_photo set titl='%s' where photo_id=%d",
          photoBoard.getTitle(), photoBoard.getNumber());
      return stmt.executeUpdate(query);
    }
  }

  @Override
  public int delete(int number) throws Exception {
    try (Statement stmt = con.createStatement();) {
      String query = String.format("delete from tam_photo where photo_id=%d", number);
      return stmt.executeUpdate(query);
    }
  }
}
