package kyh.tam;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kyh.tam.context.ApplicationContextListener;
import kyh.tam.domain.Board;
import kyh.tam.domain.Member;
import kyh.tam.domain.Stuff;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new LinkedHashSet<>();
  Map<String, Object> context = new LinkedHashMap<>();

  List<Board> boards;
  List<Member> members;
  List<Stuff> stuffs;

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  private void notifyApplicationInitialized() throws Exception {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
  }

  private void notifyApplicationDestroyed() throws Exception {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
  }

  @SuppressWarnings("unchecked")
  public void service() throws Exception {
    notifyApplicationInitialized();

    boards = (List<Board>) context.get("boardList");
    members = (List<Member>) context.get("memberList");
    stuffs = (List<Stuff>) context.get("stuffList");

    try (ServerSocket serverSocket = new ServerSocket(12345)) {
      while (true) {
        System.out.println("waiting for client to connect...");
        Socket connectedSocket = serverSocket.accept();
        if (processRequest(connectedSocket) == 1) {
          break;
        }
        System.out.println("--------------------------------------------------");
      }
    } catch (Exception e) {
      System.out.println("[service()] : serverSocket error");
      e.printStackTrace();
    }
    notifyApplicationDestroyed();
    System.out.println("Bye");
  }

  private int processRequest(Socket connectedSocket) {
    try (Socket clientSocket = connectedSocket;
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

      System.out.println("Data I/O stream ready");
      while (true) {
        String request = in.readUTF().toLowerCase();
        System.out.println("Receive complete");
        System.out.printf("Client Message : [\"%s\"]\n", request);

        switch (request) {
          case "quit":
            quit(out);
            return 0;
          case "shutdown":
            quit(out);
            return 1;
          case "/board/list":
            listBoard(out, boards);
            break;
          case "/board/add":
            addBoard(in, out, boards);
            break;
          case "/board/detail":
            detailBoard(in, out, boards);
            break;
          case "/board/update":
            updateBoard(in, out, boards);
            break;
          case "/board/delete":
            deleteBoard(in, out, boards);
            break;
          case "/stuff/list":
            listStuff(out, stuffs);
            break;
          case "/stuff/add":
            addStuff(in, out, stuffs);
            break;
          case "/stuff/detail":
            detailStuff(in, out, stuffs);
            break;
          case "/stuff/update":
            updateStuff(in, out, stuffs);
            break;
          case "/stuff/delete":
            deleteStuff(in, out, stuffs);
            break;
          case "/member/list":
            listMember(out, members);
            break;
          case "/member/add":
            addMember(in, out, members);
            break;
          case "/member/detail":
            detailMember(in, out, members);
            break;
          case "/member/update":
            updateMember(in, out, members);
            break;
          case "/member/delete":
            deleteMember(in, out, members);
            break;
          default:
            notfound(out);
        }
        out.flush();
        System.out.println("Send complete");
        System.out.println("--------------------------------------------------");
      }
    } catch (Exception e) {
      System.out.printf("[processRequest()] : %s\n" + e.getMessage());
      e.printStackTrace();
      return -1;
    }
  }

  private void quit(ObjectOutputStream out) throws IOException {
    out.writeUTF("ok");
    out.flush();
  }

  private void notfound(ObjectOutputStream out) throws IOException {
    out.writeUTF("fail");
    out.writeUTF("요청한 명령을 처리할 수 없습니다.");
  }

  private void deleteMember(ObjectInputStream in, ObjectOutputStream out, List<Member> members)
      throws IOException {
    try {
      int number = in.readInt();

      int index = -1;
      for (int i = 0; i < members.size(); i++) {
        if (members.get(i).getNumber() == number) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        members.remove(index);
        out.writeUTF("ok");

      } else {
        out.writeUTF("fail");
        out.writeUTF("해당 번호의 회원이 없습니다.");
      }
    } catch (Exception e) {
      System.out.println("[/member/delete] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }

  private void updateMember(ObjectInputStream in, ObjectOutputStream out, List<Member> members)
      throws IOException {
    try {
      Member member = (Member) in.readObject();

      int index = -1;
      for (int i = 0; i < members.size(); i++) {
        if (members.get(i).getNumber() == member.getNumber()) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        members.set(index, member);
        out.writeUTF("ok");
      } else {
        out.writeUTF("fail");
        out.writeUTF("해당 번호의 회원이 없습니다.");
      }

    } catch (Exception e) {
      System.out.println("[/member/update] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }

  private void detailMember(ObjectInputStream in, ObjectOutputStream out, List<Member> members)
      throws IOException {
    try {
      int number = in.readInt();

      Member member = null;
      for (Member m : members) {
        if (m.getNumber() == number) {
          member = m;
          break;
        }
      }

      if (member != null) {
        out.writeUTF("ok");
        out.writeObject(member);

      } else {
        out.writeUTF("fail");
        out.writeUTF("해당 번호의 회원이 없습니다.");
      }

    } catch (Exception e) {
      System.out.println("[/member/detail] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }

  private void addMember(ObjectInputStream in, ObjectOutputStream out, List<Member> members)
      throws IOException {
    try {
      Member member = (Member) in.readObject();

      int i = 0;
      for (; i < members.size(); i++) {
        if (members.get(i).getNumber() == member.getNumber()) {
          break;
        }
      }

      if (i == members.size()) {
        members.add(member);
        out.writeUTF("ok");

      } else {
        out.writeUTF("fail");
        out.writeUTF("같은 번호의 회원이 있습니다.");
      }

    } catch (Exception e) {
      System.out.println("[/member/add] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }

  private void listMember(ObjectOutputStream out, List<Member> members) throws IOException {
    out.writeUTF("ok");
    out.reset();
    out.writeObject(members);
  }

  private void deleteStuff(ObjectInputStream in, ObjectOutputStream out, List<Stuff> stuffs)
      throws IOException {
    try {
      int number = in.readInt();

      int index = -1;
      for (int i = 0; i < stuffs.size(); i++) {
        if (stuffs.get(i).getNumber() == number) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        stuffs.remove(index);
        out.writeUTF("ok");

      } else {
        out.writeUTF("fail");
        out.writeUTF("해당 번호의 물품이 없습니다.");
      }
    } catch (Exception e) {
      System.out.println("[/stuff/delete] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }

  private void updateStuff(ObjectInputStream in, ObjectOutputStream out, List<Stuff> stuffs)
      throws IOException {
    try {
      Stuff stuff = (Stuff) in.readObject();

      int index = -1;
      for (int i = 0; i < stuffs.size(); i++) {
        if (stuffs.get(i).getNumber() == stuff.getNumber()) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        stuffs.set(index, stuff);
        out.writeUTF("ok");
      } else {
        out.writeUTF("fail");
        out.writeUTF("해당 번호의 물품이 없습니다.");
      }

    } catch (Exception e) {
      System.out.println("[/stuff/update] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }

  private void detailStuff(ObjectInputStream in, ObjectOutputStream out, List<Stuff> stuffs)
      throws IOException {
    try {
      int number = in.readInt();

      Stuff stuff = null;
      for (Stuff l : stuffs) {
        if (l.getNumber() == number) {
          stuff = l;
          break;
        }
      }

      if (stuff != null) {
        out.writeUTF("ok");
        out.writeObject(stuff);

      } else {
        out.writeUTF("fail");
        out.writeUTF("해당 번호의 물품이 없습니다.");
      }

    } catch (Exception e) {
      System.out.println("[/stuff/detail] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }

  private void addStuff(ObjectInputStream in, ObjectOutputStream out, List<Stuff> stuffs)
      throws IOException {
    try {
      Stuff stuff = (Stuff) in.readObject();

      int i = 0;
      for (; i < stuffs.size(); i++) {
        if (stuffs.get(i).getNumber() == stuff.getNumber()) {
          break;
        }
      }

      if (i == stuffs.size()) {
        stuffs.add(stuff);
        out.writeUTF("ok");

      } else {
        out.writeUTF("fail");
        out.writeUTF("같은 번호의 물품이 있습니다.");
      }

    } catch (Exception e) {
      System.out.println("[/stuff/add] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }

  private void listStuff(ObjectOutputStream out, List<Stuff> stuffs) throws IOException {
    out.writeUTF("ok");
    out.reset();
    out.writeObject(stuffs);
  }

  private void deleteBoard(ObjectInputStream in, ObjectOutputStream out, List<Board> boards)
      throws IOException {
    try {
      int number = in.readInt();

      int index = -1;
      for (int i = 0; i < boards.size(); i++) {
        if (boards.get(i).getNumber() == number) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        boards.remove(index);
        out.writeUTF("ok");

      } else {
        out.writeUTF("fail");
        out.writeUTF("해당 번호의 게시물이 없습니다.");
      }
    } catch (Exception e) {
      System.out.println("[/board/delete] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }

  private void updateBoard(ObjectInputStream in, ObjectOutputStream out, List<Board> boards)
      throws IOException {
    try {
      Board board = (Board) in.readObject();

      int index = -1;
      for (int i = 0; i < boards.size(); i++) {
        if (boards.get(i).getNumber() == board.getNumber()) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        boards.set(index, board);
        out.writeUTF("ok");
      } else {
        out.writeUTF("fail");
        out.writeUTF("해당 번호의 게시물이 없습니다.");
      }

    } catch (Exception e) {
      System.out.println("[/board/update] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }

  private void detailBoard(ObjectInputStream in, ObjectOutputStream out, List<Board> boards)
      throws IOException {
    try {
      int number = in.readInt();

      Board board = null;
      for (Board b : boards) {
        if (b.getNumber() == number) {
          board = b;
          break;
        }
      }

      if (board != null) {
        out.writeUTF("ok");
        out.writeObject(board);

      } else {
        out.writeUTF("fail");
        out.writeUTF("해당 번호의 게시물이 없습니다.");
      }

    } catch (Exception e) {
      System.out.println("[/board/detail] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }

  private void addBoard(ObjectInputStream in, ObjectOutputStream out, List<Board> boards)
      throws IOException {
    try {
      Board board = (Board) in.readObject();

      int i = 0;
      for (; i < boards.size(); i++) {
        if (boards.get(i).getNumber() == board.getNumber()) {
          break;
        }
      }

      if (i == boards.size()) {
        boards.add(board);
        out.writeUTF("ok");

      } else {
        out.writeUTF("fail");
        out.writeUTF("같은 번호의 게시물이 있습니다.");
      }

    } catch (Exception e) {
      System.out.println("[/board/add] : send \"fail\" to client");
      out.writeUTF("fail");
      out.writeUTF(e.getMessage());
    }
  }

  private void listBoard(ObjectOutputStream out, List<Board> boards) throws IOException {
    out.writeUTF("ok");
    out.reset();
    out.writeObject(boards);
  }

  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    System.out.println("--------------------------------------------------");
    System.out.println("|                TAM server start                |");
    app.service();
  }
}
