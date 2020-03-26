package kyh.tam.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import kyh.tam.dao.PhotoBoardDao;
import kyh.tam.domain.PhotoBoard;
import kyh.tam.domain.Stuff;
import kyh.tam.util.DataSource;

public class PhotoBoardDaoImpl implements PhotoBoardDao {

  DataSource dataSource;

  public PhotoBoardDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("insert into tam_photo(titl, stuff_id) values(?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS);) {
      stmt.setString(1, photoBoard.getTitle());
      stmt.setInt(2, photoBoard.getStuff().getNumber());
      int result = stmt.executeUpdate();

      try (ResultSet generatedKeySet = stmt.getGeneratedKeys();) {
        generatedKeySet.next();
        photoBoard.setNumber(generatedKeySet.getInt(1));
      }
      return result;
    }
  }

  @Override
  public List<PhotoBoard> findAllByStuffNumber(int stuffNumber) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("select photo_id, titl, cdt, stuff_id, vw_cnt "
                + "from tam_photo where stuff_id=? order by photo_id asc");) {
      stmt.setInt(1, stuffNumber);
      try (ResultSet rs = stmt.executeQuery();) {
        ArrayList<PhotoBoard> list = new ArrayList<>();
        while (rs.next()) {
          PhotoBoard photoBoard = new PhotoBoard();
          photoBoard.setNumber(rs.getInt("photo_id"));
          photoBoard.setTitle(rs.getString("titl"));
          photoBoard.setRegisteredDate(rs.getDate("cdt"));
          photoBoard.setViewCount(rs.getInt("vw_cnt"));
          list.add(photoBoard);
        }
        return list;
      }
    }
  }

  @Override
  public PhotoBoard findByNo(int number) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select p.photo_id, p.titl, p.cdt, p.vw_cnt, s.stuff_id, s.name stuff_name "
                + "from tam_photo p inner join tam_stuff s on p.stuff_id=s.stuff_id where photo_id=?");) {
      stmt.setInt(1, number);
      try (ResultSet rs = stmt.executeQuery();) {
        if (rs.next()) {
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
        return null;
      }
    }
  }

  @Override
  public int update(PhotoBoard photoBoard) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("update tam_photo set titl=? where photo_id=?");) {
      stmt.setString(1, photoBoard.getTitle());
      stmt.setInt(2, photoBoard.getNumber());
      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int number) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement("delete from tam_photo where photo_id=?");) {
      stmt.setInt(1, number);
      return stmt.executeUpdate();
    }
  }
}
