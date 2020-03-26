package kyh.tam.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import kyh.tam.dao.MemberDao;
import kyh.tam.domain.Member;
import kyh.tam.util.DataSource;

public class MemberDaoImpl implements MemberDao {

  DataSource dataSource;

  public MemberDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(Member member) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "insert into tam_member(name, email, pwd, addr, tel, photo) values(?, ?, password(?), ?, ?, ?)");) {
      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setString(4, member.getAddress());
      stmt.setString(5, member.getTel());
      stmt.setString(6, member.getPhoto());
      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Member> findAll() throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select member_id, name, email, pwd, addr, tel, photo from tam_member");
        ResultSet rs = stmt.executeQuery();) {

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
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select member_id, name, email, pwd, addr, tel, photo, cdt from tam_member where member_id=?");) {
      stmt.setInt(1, number);
      try (ResultSet rs = stmt.executeQuery();) {
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
      }
      return null;
    }
  }

  @Override
  public int update(Member member) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "update tam_member set name=?, email=?, pwd=password(?), addr=?, tel=?, photo=? where member_id=?");) {
      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setString(4, member.getAddress());
      stmt.setString(5, member.getTel());
      stmt.setString(6, member.getPhoto());
      stmt.setInt(7, member.getNumber());
      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int number) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("delete from tam_member where member_id=?");) {
      stmt.setInt(1, number);
      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Member> findByKeyword(String keyword) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("select member_id, name, email, addr, pwd, tel, photo "
                + "from tam_member " + "where name like ? or email like ? or tel like ?");) {
      String value = "%" + keyword + "%";
      stmt.setString(1, value);
      stmt.setString(2, value);
      stmt.setString(3, value);
      try (ResultSet rs = stmt.executeQuery();) {
        ArrayList<Member> list = new ArrayList<>();
        while (rs.next()) {
          Member member = new Member();
          member.setNumber(rs.getInt("member_id"));
          member.setName(rs.getString("name"));
          member.setEmail(rs.getString("email"));
          member.setAddress(rs.getString("addr"));
          member.setPassword(rs.getString("pwd"));
          member.setTel(rs.getString("tel"));
          member.setPhoto(rs.getString("photo"));
          list.add(member);
        }
        return list;
      }
    }
  }

  @Override
  public Member findByEmailAndPassword(String email, String password) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("select member_id, name, email, pwd, tel, photo from tam_member"
                + " where email like ? and pwd like password(?)");) {
      stmt.setString(1, email);
      stmt.setString(2, password);
      try (ResultSet rs = stmt.executeQuery();) {
        if (rs.next()) {
          Member member = new Member();
          member.setNumber(rs.getInt("member_id"));
          member.setName(rs.getString("name"));
          member.setEmail(rs.getString("email"));
          member.setPassword(rs.getString("pwd"));
          member.setTel(rs.getString("tel"));
          member.setPhoto(rs.getString("photo"));
          return member;
        }
      }
      return null;
    }
  }
}
