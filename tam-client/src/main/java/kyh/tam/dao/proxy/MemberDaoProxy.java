package kyh.tam.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import kyh.tam.dao.MemberDao;
import kyh.tam.domain.Member;

public class MemberDaoProxy implements MemberDao {
  String host;
  int port;

  public MemberDaoProxy(String host, int port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public int insert(Member member) throws Exception {
    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF("/member/add");
      out.writeObject(member);
      out.flush();
      String response = in.readUTF();
      if (response.equalsIgnoreCase("fail")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Member> findAll() throws Exception {
    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF("/member/list");
      out.flush();
      String response = in.readUTF();
      if (response.equalsIgnoreCase("fail")) {
        throw new Exception(in.readUTF());
      }
      return (List<Member>) in.readObject();
    }
  }

  @Override
  public Member findByNumber(int number) throws Exception {
    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF("/member/detail");
      out.writeInt(number);
      out.flush();
      String response = in.readUTF();
      if (response.equalsIgnoreCase("fail")) {
        throw new Exception(in.readUTF());
      }
      return (Member) in.readObject();
    }
  }

  @Override
  public int update(Member member) throws Exception {
    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF("/member/update");
      out.writeObject(member);
      out.flush();

      String response = in.readUTF();
      if (response.equalsIgnoreCase("fail")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    }
  }

  @Override
  public int delete(int number) throws Exception {
    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF("/member/delete");
      out.writeInt(number);
      out.flush();

      String response = in.readUTF();
      if (response.equalsIgnoreCase("fail")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    }
  }
}
