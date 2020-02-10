package kyh.tam;

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

  public void service() throws Exception {
    notifyApplicationInitialized();

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

  @SuppressWarnings("unchecked")
  private int processRequest(Socket connectedSocket) {


    try (Socket clientSocket = connectedSocket;
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

      System.out.println("Data I/O stream ready");
      while (true) {
        String request = in.readUTF();
        System.out.println("Receive complete");

        if (request.equalsIgnoreCase("quit")) {
          out.writeUTF("ok");
          out.flush();
          break;
        }
        if (request.equals("shutdown")) {
          out.writeUTF("ok");
          out.flush();
          return 1;
        }

        List<Board> boards = (List<Board>) context.get("boardList");
        List<Member> members = (List<Member>) context.get("memberList");
        List<Stuff> stuffs = (List<Stuff>) context.get("stuffList");

        if (request.equals("/board/list")) {
          out.writeUTF("ok");
          out.reset();
          out.writeObject(boards);

        } else if (request.equals("/board/add")) {
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
        } else if (request.equals("/board/detail")) {
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
        } else if (request.equals("/board/update")) {
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
        } else if (request.equals("/board/delete")) {
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

        } else if (request.equals("/stuff/list")) {
          out.writeUTF("ok");
          out.reset();
          out.writeObject(stuffs);

        } else if (request.equals("/stuff/add")) {
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
        } else if (request.equals("/stuff/detail")) {
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
        } else if (request.equals("/stuff/update")) {
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
        } else if (request.equals("/stuff/delete")) {
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

        } else if (request.equals("/member/list")) {
          out.writeUTF("ok");
          out.reset();
          out.writeObject(members);

        } else if (request.equals("/member/add")) {
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
        } else if (request.equals("/member/detail")) {
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
        } else if (request.equals("/member/update")) {
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
        } else if (request.equals("/member/delete")) {
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

        } else {
          out.writeUTF("fail");
          out.writeUTF("요청한 명령을 처리할 수 없습니다.");
        }
        out.flush();
        System.out.println("Send complete");
        System.out.println("--------------------------------------------------");
      }
      System.out.println("Send complete");
      return 0;

    } catch (Exception e) {
      System.out.printf("[processRequest()] : %s\n" + e.getMessage());
      e.printStackTrace();
      return -1;
    }
  }

  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    System.out.println("--------------------------------------------------");
    System.out.println("|                TAM server start                |");
    app.service();
  }
}
