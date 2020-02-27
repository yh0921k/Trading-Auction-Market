package kyh.tam.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kyh.tam.dao.MemberDao;
import kyh.tam.domain.Member;

public class MemberDaoImpl implements MemberDao {
  @Override
  public int insert(Member member) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");
    try (
        Connection con =
            DriverManager.getConnection("jdbc:mariadb://localhost:3306/tamdb", "kyh", "1111");
        Statement stmt = con.createStatement();) {
      String query = String.format(
          "insert into tam_member(name, email, pwd, addr, tel, photo) values('%s', '%s', '%s', '%s', '%s', '%s')",
          member.getName(), member.getEmail(), member.getPassword(), member.getAddress(),
          member.getTel(), member.getPhoto());
      return stmt.executeUpdate(query);
    }
  }

  @Override
  public List<Member> findAll() throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");
    try (
        Connection con =
            DriverManager.getConnection("jdbc:mariadb://localhost:3306/tamdb", "kyh", "1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select member_id, name, email, pwd, addr, tel, photo from tam_member");) {

      ArrayList<Member> list = new ArrayList<>();
      while (rs.next()) {
        Member member = new Member();
        member.setNumber(rs.getInt("member_id"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("pwd"));
        member.setAddress(rs.getString("addr"));
        member.setTel(rs.getString("tel"));
        member.setPhoto(rs.getString("photo"));
        list.add(member);
      }
      return list;
    }
  }

  @Override
  public Member findByNumber(int number) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");
    try (
        Connection con =
            DriverManager.getConnection("jdbc:mariadb://localhost:3306/tamdb", "kyh", "1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select member_id, name, email, pwd, addr, tel, photo, cdt from tam_member where member_id="
                + number);) {

      if (rs.next()) {
        Member member = new Member();
        member.setNumber(rs.getInt("member_id"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("pwd"));
        member.setAddress(rs.getString("addr"));
        member.setTel(rs.getString("tel"));
        member.setPhoto(rs.getString("photo"));
        member.setRegisteredDate(rs.getDate("cdt"));
        return member;
      }
      return null;
    }
  }

  @Override
  public int update(Member member) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");
    try (
        Connection con =
            DriverManager.getConnection("jdbc:mariadb://localhost:3306/tamdb", "kyh", "1111");
        Statement stmt = con.createStatement();) {
      String query = String.format(
          "update tam_member set name='%s', email='%s', pwd='%s', addr='%s', tel='%s', photo='%s' where member_id=%d",
          member.getName(), member.getEmail(), member.getPassword(), member.getAddress(),
          member.getTel(), member.getPhoto(), member.getNumber());
      return stmt.executeUpdate(query);
    }
  }

  @Override
  public int delete(int number) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");
    try (
        Connection con =
            DriverManager.getConnection("jdbc:mariadb://localhost:3306/tamdb", "kyh", "1111");
        Statement stmt = con.createStatement();) {
      return stmt.executeUpdate("delete from tam_member where member_id=" + number);
    }
  }
}
