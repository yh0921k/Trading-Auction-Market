package kyh.tam.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kyh.tam.dao.BoardDao;
import kyh.tam.domain.Board;

public class BoardDaoImpl implements BoardDao {

  String jdbcUrl;
  String username;
  String password;

  public BoardDaoImpl(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  @Override
  public int insert(Board board) throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement();) {
      String query = String.format("insert into tam_board(conts) values('%s')", board.getTitle());
      return stmt.executeUpdate(query);
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select board_id, conts, cdt, vw_cnt from tam_board");) {

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
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select board_id, conts, cdt, vw_cnt from tam_board where board_id=" + number);) {

      if (rs.next()) {
        Board board = new Board();
        board.setNumber(rs.getInt("board_id"));
        board.setTitle(rs.getString("conts"));
        board.setWriteDate(rs.getDate("cdt"));
        board.setViewCount(rs.getInt("vw_cnt"));
        return board;
      }
      return null;
    }
  }

  @Override
  public int update(Board board) throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement();) {
      String query = String.format("update tam_board set conts='%s' where board_id=%d",
          board.getTitle(), board.getNumber());
      return stmt.executeUpdate(query);
    }
  }

  @Override
  public int delete(int number) throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement();) {
      return stmt.executeUpdate("delete from tam_board where board_id=" + number);
    }
  }
}
