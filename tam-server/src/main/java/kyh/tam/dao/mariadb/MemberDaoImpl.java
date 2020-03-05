package kyh.tam.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kyh.tam.dao.MemberDao;
import kyh.tam.domain.Member;

public class MemberDaoImpl implements MemberDao {

  Connection con;

  public MemberDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(Member member) throws Exception {
    try (Statement stmt = con.createStatement();) {
      String query = String.format(
          "insert into tam_member(name, email, pwd, addr, tel, photo) values('%s', '%s', '%s', '%s', '%s', '%s')",
          member.getName(), member.getEmail(), member.getPassword(), member.getAddress(),
          member.getTel(), member.getPhoto());
      return stmt.executeUpdate(query);
    }
  }

  @Override
  public List<Member> findAll() throws Exception {
    try (Statement stmt = con.createStatement();
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
    try (Statement stmt = con.createStatement();
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
    try (Statement stmt = con.createStatement();) {
      String query = String.format(
          "update tam_member set name='%s', email='%s', pwd='%s', addr='%s', tel='%s', photo='%s' where member_id=%d",
          member.getName(), member.getEmail(), member.getPassword(), member.getAddress(),
          member.getTel(), member.getPhoto(), member.getNumber());
      return stmt.executeUpdate(query);
    }
  }

  @Override
  public int delete(int number) throws Exception {
    try (Statement stmt = con.createStatement();) {
      return stmt.executeUpdate("delete from tam_member where member_id=" + number);
    }
  }

  @Override
  public List<Member> findByKeyword(String keyword) throws Exception {
    try (Statement stmt = con.createStatement();) {
      String query = String.format(
          "select member_id, name, email, addr, pwd, tel, photo " + "from tam_member "
              + "where name like '%%%s%%' or email like '%%%s%%' or tel like '%%%s%%'",
          keyword, keyword, keyword);
      ResultSet rs = stmt.executeQuery(query);
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
      rs.close();
      return list;
    }
  }
}
