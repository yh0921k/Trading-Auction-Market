package kyh.tam.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kyh.tam.dao.StuffDao;
import kyh.tam.domain.Stuff;

public class StuffDaoProxy implements StuffDao {

  ObjectInputStream in;
  ObjectOutputStream out;

  public StuffDaoProxy(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public int insert(Stuff stuff) throws Exception {
    out.writeUTF("/stuff/add");
    out.writeObject(stuff);
    out.flush();

    String response = in.readUTF();
    if (response.equalsIgnoreCase("fail")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Stuff> findAll() throws Exception {
    out.writeUTF("/stuff/list");
    out.flush();

    String response = in.readUTF();
    if (response.equalsIgnoreCase("fail")) {
      throw new Exception(in.readUTF());
    }
    return (List<Stuff>) in.readObject();
  }

  @Override
  public Stuff findByNumber(int number) throws Exception {
    out.writeUTF("/stuff/detail");
    out.writeInt(number);
    out.flush();
    String response = in.readUTF();
    if (response.equalsIgnoreCase("fail")) {
      throw new Exception(in.readUTF());
    }
    return (Stuff) in.readObject();
  }

  @Override
  public int update(Stuff stuff) throws Exception {
    out.writeUTF("/stuff/update");
    out.writeObject(stuff);
    out.flush();

    String response = in.readUTF();
    if (response.equalsIgnoreCase("fail")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

  @Override
  public int delete(int number) throws Exception {
    out.writeUTF("/stuff/delete");
    out.writeInt(number);
    out.flush();

    String response = in.readUTF();
    if (response.equals("fail")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

}
