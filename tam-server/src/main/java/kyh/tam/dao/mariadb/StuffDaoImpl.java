package kyh.tam.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import kyh.tam.dao.StuffDao;
import kyh.tam.domain.Stuff;
import kyh.tam.util.DataSource;

public class StuffDaoImpl implements StuffDao {

  DataSource dataSource;

  public StuffDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(Stuff stuff) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "insert into tam_stuff(name, state, seller, category, price) values(?, ?, ?, ?, ?)");) {
      stmt.setString(1, stuff.getName());
      stmt.setString(2, stuff.getState());
      stmt.setString(3, stuff.getSeller());
      stmt.setString(4, stuff.getCategory());
      stmt.setInt(5, stuff.getPrice());
      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Stuff> findAll() throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select stuff_id, name, state, seller, category, price from tam_stuff");
        ResultSet rs = stmt.executeQuery();) {

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
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select stuff_id, name, state, seller, category, price from tam_stuff where stuff_id=?");) {
      stmt.setInt(1, number);
      try (ResultSet rs = stmt.executeQuery();) {
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
      }
      return null;
    }
  }

  @Override
  public int update(Stuff stuff) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "update tam_stuff set name=?, state=?, seller=?, category=?, price=? where stuff_id=?");) {
      stmt.setString(1, stuff.getName());
      stmt.setString(2, stuff.getState());
      stmt.setString(3, stuff.getSeller());
      stmt.setString(4, stuff.getCategory());
      stmt.setInt(5, stuff.getPrice());
      stmt.setInt(6, stuff.getNumber());
      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int number) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement("delete from tam_stuff where stuff_id=?");) {
      stmt.setInt(1, number);
      return stmt.executeUpdate();
    }
  }
}
