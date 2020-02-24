package kyh.tam.dao.proxy;

import java.util.List;
import kyh.tam.dao.MemberDao;
import kyh.tam.domain.Member;

public class MemberDaoProxy implements MemberDao {

  DaoProxyHelper daoProxyHelper;

  public MemberDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public int insert(Member member) throws Exception {
    return (int) daoProxyHelper.request(new MemberInsertWorker(member));
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Member> findAll() throws Exception {
    return (List<Member>) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/member/list");
      out.flush();
      String response = in.readUTF();
      if (response.equalsIgnoreCase("fail"))
        throw new Exception(in.readUTF());
      return (List<Member>) in.readObject();
    });
  }

  @Override
  public Member findByNumber(int number) throws Exception {
    return (Member) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/member/detail");
      out.writeInt(number);
      out.flush();
      String response = in.readUTF();
      if (response.equalsIgnoreCase("fail"))
        throw new Exception(in.readUTF());
      return (Member) in.readObject();
    });
  }

  @Override
  public int update(Member member) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/member/update");
      out.writeObject(member);
      out.flush();

      String response = in.readUTF();
      if (response.equalsIgnoreCase("fail"))
        throw new Exception(in.readUTF());
      return 1;
    });
  }

  @Override
  public int delete(int number) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/member/delete");
      out.writeInt(number);
      out.flush();

      String response = in.readUTF();
      if (response.equalsIgnoreCase("fail"))
        throw new Exception(in.readUTF());
      return 1;
    });
  }
}
