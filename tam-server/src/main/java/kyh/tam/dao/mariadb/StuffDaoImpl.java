package kyh.tam.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kyh.tam.dao.StuffDao;
import kyh.tam.domain.Stuff;

public class StuffDaoImpl implements StuffDao {

  String jdbcUrl;
  String username;
  String password;

  public StuffDaoImpl(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  @Override
  public int insert(Stuff stuff) throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement();) {
      String query = String.format(
          "insert into tam_stuff(name, state, seller, category, price) values('%s', '%s', '%s', '%s', %d)",
          stuff.getName(), stuff.getState(), stuff.getSeller(), stuff.getCategory(),
          stuff.getPrice());
      return stmt.executeUpdate(query);
    }
  }

  @Override
  public List<Stuff> findAll() throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select stuff_id, name, state, seller, category, price from tam_stuff");) {

      ArrayList<Stuff> list = new ArrayList<>();
      while (rs.next()) {
        Stuff stuff = new Stuff();
        stuff.setNumber(rs.getInt("stuff_id"));
        stuff.setName(rs.getString("name"));
        stuff.setState(rs.getString("state"));
        stuff.setSeller(rs.getString("seller"));
        stuff.setCategory(rs.getString("category"));
        stuff.setPrice(rs.getInt("price"));
        list.add(stuff);
      }
      return list;
    }
  }

  @Override
  public Stuff findByNumber(int number) throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select stuff_id, name, state, seller, category, price from tam_stuff where stuff_id="
                + number);) {

      if (rs.next()) {
        Stuff stuff = new Stuff();
        stuff.setNumber(rs.getInt("stuff_id"));
        stuff.setName(rs.getString("name"));
        stuff.setState(rs.getString("state"));
        stuff.setSeller(rs.getString("seller"));
        stuff.setCategory(rs.getString("category"));
        stuff.setPrice(rs.getInt("price"));
        return stuff;
      }
      return null;
    }
  }

  @Override
  public int update(Stuff stuff) throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement();) {
      String query = String.format(
          "update tam_stuff set name='%s', state='%s', seller='%s', category='%s', price=%d where stuff_id=%d",
          stuff.getName(), stuff.getState(), stuff.getSeller(), stuff.getCategory(),
          stuff.getPrice(), stuff.getNumber());
      return stmt.executeUpdate(query);
    }
  }

  @Override
  public int delete(int number) throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement();) {
      return stmt.executeUpdate("delete from tam_stuff where stuff_id=" + number);
    }
  }
}
