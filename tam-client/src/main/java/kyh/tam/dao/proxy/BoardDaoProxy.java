package kyh.tam.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import kyh.tam.dao.BoardDao;
import kyh.tam.domain.Board;

public class BoardDaoProxy implements BoardDao {

  String host;
  int port;

  public BoardDaoProxy(String host, int port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public int insert(Board board) throws Exception {
    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF("/board/add");
      out.writeObject(board);
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
  public List<Board> findAll() throws Exception {
    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF("/board/list");
      out.flush();

      String response = in.readUTF();
      if (response.equalsIgnoreCase("fail")) {
        throw new Exception(in.readUTF());
      }
      return (List<Board>) in.readObject();
    }
  }

  @Override
  public Board findByNumber(int number) throws Exception {
    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF("/board/detail");
      out.writeInt(number);
      out.flush();

      String response = in.readUTF();
      if (response.equalsIgnoreCase("fail")) {
        throw new Exception(in.readUTF());
      }
      return (Board) in.readObject();
    }
  }

  @Override
  public int update(Board board) throws Exception {
    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF("/board/update");
      out.writeObject(board);
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
      out.writeUTF("/board/delete");
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
