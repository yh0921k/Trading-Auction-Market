package kyh.tam.dao.proxy;

import java.util.List;
import kyh.tam.dao.StuffDao;
import kyh.tam.domain.Stuff;

public class StuffDaoProxy implements StuffDao {

  DaoProxyHelper daoProxyHelper;

  public StuffDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public int insert(Stuff stuff) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/stuff/add");
      out.writeObject(stuff);
      out.flush();

      String response = in.readUTF();
      if (response.equalsIgnoreCase("fail")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    });
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Stuff> findAll() throws Exception {
    return (List<Stuff>) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/stuff/list");
      out.flush();

      String response = in.readUTF();
      if (response.equalsIgnoreCase("fail")) {
        throw new Exception(in.readUTF());
      }
      return in.readObject();
    });
  }

  @Override
  public Stuff findByNumber(int number) throws Exception {
    return (Stuff) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/stuff/detail");
      out.writeInt(number);
      out.flush();
      String response = in.readUTF();
      if (response.equalsIgnoreCase("fail")) {
        throw new Exception(in.readUTF());
      }
      return in.readObject();
    });
  }

  @Override
  public int update(Stuff stuff) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/stuff/update");
      out.writeObject(stuff);
      out.flush();

      String response = in.readUTF();
      if (response.equalsIgnoreCase("fail")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    });
  }

  @Override
  public int delete(int number) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/stuff/delete");
      out.writeInt(number);
      out.flush();

      String response = in.readUTF();
      if (response.equals("fail")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    });
  }
}
