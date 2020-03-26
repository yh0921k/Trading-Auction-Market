package kyh.tam.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import kyh.tam.dao.BoardDao;
import kyh.tam.domain.Board;
import kyh.tam.util.DataSource;

public class BoardDaoImpl implements BoardDao {

  DataSource dataSource;

  public BoardDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(Board board) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement("insert into tam_board(conts) values(?)");) {
      stmt.setString(1, board.getTitle());
      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("select board_id, conts, cdt, vw_cnt from tam_board");
        ResultSet rs = stmt.executeQuery();) {

      ArrayList<Board> list = new ArrayList<>();
      while (rs.next()) {
        Board board = new Board();
        board.setNumber(rs.getInt("board_id"));
        board.setTitle(rs.getString("conts"));
        board.setWriteDate(rs.getDate("cdt"));
        board.setViewCount(rs.getInt("vw_cnt"));
        list.add(board);
      }
      return list;
    }
  }

  @Override
  public Board findByNumber(int number) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select board_id, conts, cdt, vw_cnt from tam_board where board_id=?");) {
      stmt.setInt(1, number);
      try (ResultSet rs = stmt.executeQuery();) {
        if (rs.next()) {
          Board board = new Board();
          board.setNumber(rs.getInt("board_id"));
          board.setTitle(rs.getString("conts"));
          board.setWriteDate(rs.getDate("cdt"));
          board.setViewCount(rs.getInt("vw_cnt"));
          return board;
        }
      }
      return null;
    }
  }

  @Override
  public int update(Board board) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("update tam_board set conts=? where board_id=?");) {
      stmt.setString(1, board.getTitle());
      stmt.setInt(2, board.getNumber());
      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int number) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement("delete from tam_board where board_id=?");) {
      stmt.setInt(1, number);
      return stmt.executeUpdate();
    }
  }
}
